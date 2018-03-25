package com.example.alexdev.maptestapp.models.http.models.subModels;


import com.google.gson.annotations.SerializedName;

public enum StatusResponse {

    @SerializedName("OK")
    ok("OK"),
    @SerializedName("ZERO_RESULTS")
    zero("ZERO_RESULTS"),
    @SerializedName("OVER_QUERY_LIMIT")
    queryLimit("OVER_QUERY_LIMIT"),
    @SerializedName("REQUEST_DENIED")
    denied("REQUEST_DENIED"),
    @SerializedName("INVALID_REQUEST")
    invalidRequest("INVALID_REQUEST"),
    @SerializedName("UNKNOWN_ERROR")
    serverError("UNKNOWN_ERROR"),
    @SerializedName("NOT_FOUND")
    notFound("NOT_FOUND");

    private String name;

    StatusResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



    @Override
    public String toString() {
        return getName();
    }
}
