package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpotifyToken {
    private String token;
    private String type;
    private int expires;

    public SpotifyToken() {
    }

    @JsonProperty("access_token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("token_type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("expires_in")
    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }
}
