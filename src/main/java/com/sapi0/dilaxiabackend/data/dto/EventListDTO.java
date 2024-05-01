package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class EventListDTO {


    @JsonProperty("totalSize")
    public int totalSize;
    @JsonProperty("pageSize")
    public int pageSize;
    @JsonProperty("page")
    public int page;
    @JsonProperty("filters")
    public FilterDTO filters;
    @JsonProperty("data")
    public List<EventDTO> data = new ArrayList<>();



    public EventListDTO(int totalSize, int pageSize, int page, FilterDTO filters, List<EventDTO> data) {
        this.totalSize = totalSize;
        this.pageSize = pageSize;
        this.page = page;
        this.filters = filters;
        this.data = data;
    }

    public EventListDTO() {
    }
}
