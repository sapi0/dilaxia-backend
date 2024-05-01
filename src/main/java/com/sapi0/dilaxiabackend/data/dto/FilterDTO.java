package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.sql.Date;
import java.sql.Timestamp;

public class FilterDTO {


    @JsonProperty("query")
    public String query;
    @JsonProperty("showPast")
    public Boolean showPast;
    @JsonProperty("date")
    public DateTime date;

    public FilterDTO(String query, Boolean showPast, DateTime date) {
        this.query = query;
        this.showPast = showPast;
        this.date = date;
    }


    public FilterDTO() {

    }
}
