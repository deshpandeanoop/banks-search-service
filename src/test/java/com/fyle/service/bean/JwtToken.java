package com.fyle.service.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtToken {
    @JsonProperty("jwt")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
