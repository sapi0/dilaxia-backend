package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserListDTO {

    @JsonProperty("totalSize")
    public int totalSize;
    @JsonProperty("pageCount")
    public int pageCount;
    @JsonProperty("page")
    public int page;
    @JsonProperty("data")
    public List<UserDTO> data;

    public UserListDTO(int totalSize, int pageCount, int page, List<UserDTO> data) {
        this.totalSize = totalSize;
        this.pageCount = pageCount;
        this.page = page;
        this.data = data;
    }


    public UserListDTO() {
    }
}
