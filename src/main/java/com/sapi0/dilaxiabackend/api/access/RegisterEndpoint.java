package com.sapi0.dilaxiabackend.api.access;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.data.dto.RegisterDTO;
import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;
import com.sapi0.dilaxiabackend.exception.BodyParseException;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import com.sapi0.dilaxiabackend.exception.IllegalParamException;
import com.sapi0.dilaxiabackend.utils.Global;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.regex.Pattern;

import static com.sapi0.dilaxiabackend.utils.Global.*;

@WebServlet("/register")
public class RegisterEndpoint extends BasicJsonEndpoint {

    private UserDaoImpl dao;

    @Override
    public void init() {
        dao = DaoFactory.instance.getUserDao();
    }

    @Override
    public void destroy() {

    }

    public int findAccountType(String email) {
        int type = USER_TYPE_EXTERNAL;

        if(email.endsWith(STUDENT_EMAIL_SUFFIX)) type = USER_TYPE_STUDENT;
        if(email.endsWith(PROFESSOR_EMAIL_SUFFIX)) type = USER_TYPE_PROFESSOR;

        return type;
    }

    @Override
    protected JSONObject post(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        requireLoggedOut(session);

        try {
            RegisterDTO registerData = Mapper.asObject(bodyObject, RegisterDTO.class);

            assertParam(registerData.name, 1, 50, 496);
            if(!REGEX_LETTERS_SPACES.matcher(registerData.name).matches()) throw new IllegalParamException(496, "Name doesn't matches");

            assertParam(registerData.surname, 1, 50, 497);
            if(!REGEX_LETTERS_SPACES.matcher(registerData.surname).matches()) throw new IllegalParamException(497, "Surname doesn't matches");

            assertParam(registerData.email, 5, 100, 498);
            if(!REGEX_EMAIL.matcher(registerData.email).matches()) throw new IllegalParamException(498, "Email doesn't matches");

            assertParam(registerData.password, 8, 50, 499);
            if(!REGEX_PASSWORD.matcher(registerData.password).matches()) throw new IllegalParamException(499, "Password invalid or too weak");

            String bcryptHashString = BCrypt.withDefaults().hashToString(BCRYPT_COST, registerData.password.toCharArray());

            int accountType = findAccountType(registerData.email);

            User user = new User(-1, registerData.name, registerData.surname, registerData.email, bcryptHashString, accountType, null);

            dao.add(user);

            return DEFAULT_SUCCESS_JSON;
        } catch(JsonProcessingException e) {
            throw new BodyParseException(499, "Invalid body object");
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO throw
        }

        throw new EndpointException(499, "Unknown error");
    }

}
