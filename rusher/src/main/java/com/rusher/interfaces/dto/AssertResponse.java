package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: Liam
 * Date: 2017/8/23
 */
public class AssertResponse {
    @JsonProperty("total")
    private Total total;
    @JsonProperty("detail")
    private Detail detail;

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
