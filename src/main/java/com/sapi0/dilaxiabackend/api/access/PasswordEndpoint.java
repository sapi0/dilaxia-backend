package com.sapi0.dilaxiabackend.api.access;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.data.dto.PasswordChangeDTO;
import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;
import com.sapi0.dilaxiabackend.exception.AccessException;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import com.sapi0.dilaxiabackend.exception.IllegalParamException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;

import static com.sapi0.dilaxiabackend.utils.Global.*;

@WebServlet("/password")
public class PasswordEndpoint extends BasicJsonEndpoint {

    private UserDaoImpl dao;

    @Override
    public void init() {
        dao = DaoFactory.instance.getUserDao();
    }

    @Override
    public void destroy() {

    }

    @Override
    protected JSONObject post(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        requireLoggedIn(session);

        try {
            PasswordChangeDTO dto = Mapper.asObject(bodyObject, PasswordChangeDTO.class);

            User user = dao.get(dto.email);

            if(user == null) {
                throw new AccessException(499, "Wrong credentials");
            }

            if(user.getId() != ((int)session.getAttribute("id"))) {
                throw new AccessException(499, "Trying to change password of a different account!");
            }

            BCrypt.Result result = BCrypt.verifyer().verify(dto.currentPassword.toCharArray(), user.getHash());

            if(!result.verified) {
                throw new AccessException(499, "Wrong credentials");
            }

            assertParam(dto.newPassword, 8, 50, 499);
            if(!REGEX_PASSWORD.matcher(dto.newPassword).matches()) throw new IllegalParamException(499, "Password invalid or too weak");

            String bcryptHashString = BCrypt.withDefaults().hashToString(BCRYPT_COST, dto.newPassword.toCharArray());
            user.setHash(bcryptHashString);

            dao.update(user.getId(), user);

            return DEFAULT_SUCCESS_JSON;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
