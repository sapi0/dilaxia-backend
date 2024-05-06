package com.sapi0.dilaxiabackend.api.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.exception.BodyParseException;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import com.sapi0.dilaxiabackend.exception.IllegalParamException;
import com.sapi0.dilaxiabackend.service.EventService;
import com.sapi0.dilaxiabackend.service.ServiceFactory;
import com.sapi0.dilaxiabackend.utils.Params;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/events/today")
public class EventsTodayEndpoint extends BasicJsonEndpoint {

    private EventService service;

    @Override
    public void init() {
        service = ServiceFactory.instance.getEventService();
    }

    @Override
    public void destroy() {

    }

    @Override
    protected JSONObject get(HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        requireLoggedIn(session);

        DateTime date = Params.queryToDate(queryParams, "date", null);
        int pageSize = Params.queryToInt(queryParams, "pageSize", 50);
        int page = Params.queryToInt(queryParams, "page", 1);

        try {
            return Mapper.asJSON(service.research(date, pageSize, page));
        } catch(JsonProcessingException e) {
            throw new BodyParseException(599, "Invalid response object");
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO throw
            throw new EndpointException(599, "internal error");
        }
    }

}
