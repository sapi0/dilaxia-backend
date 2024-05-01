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
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/events/search")
public class EventsSearchEndpoint extends BasicJsonEndpoint {

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

        if(!headers.containsKey("query"))
            throw new IllegalParamException(499, "No research query is specified in the headers");
        String query = headers.get("query");

        int pageSize = Params.queryToInt(queryParams, "pageSize", 50);
        int page = Params.queryToInt(queryParams, "page", 1);
        boolean showPast = Params.queryToBool(queryParams, "showPast", false);

        try {
            return Mapper.asJSON(service.research(query, showPast, pageSize, page));
        } catch(JsonProcessingException e) {
            throw new BodyParseException(599, "Invalid response object");
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO throw
            throw new EndpointException(599, "internal error");
        }
    }

}
