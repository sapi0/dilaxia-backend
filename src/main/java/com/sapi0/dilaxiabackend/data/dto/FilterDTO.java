package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Timestamp;

public class FilterDTO {


    @JsonProperty("query")
    public String query;
    @JsonProperty("showPast")
    public String showPast;
    @JsonProperty("date")
    public Timestamp date;


    public FilterDTO(String query, String showPast, Timestamp date) {
        this.query = query;
        this.showPast = showPast;
        this.date = date;
    }


    public FilterDTO() {

    }
}
