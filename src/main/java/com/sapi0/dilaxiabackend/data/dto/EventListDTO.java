package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class EventListDTO {


    @JsonProperty("totalSize")
    public int totalSize;
    @JsonProperty("pageCount")
    public int pageCount;
    @JsonProperty("page")
    public int page;
    @JsonProperty("filters")
    public FilterDTO filters;
    @JsonProperty("data")
    public List<EventDTO> data = new ArrayList<>();



    public EventListDTO(int totalSize, int pageCount, int page, FilterDTO filters, List<EventDTO> data) {
        this.totalSize = totalSize;
        this.pageCount = pageCount;
        this.page = page;
        this.filters = filters;
        this.data = data;
    }

    public EventListDTO() {
    }
}
