package com.antpc.app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("num_parts")
    @Expose
    private Integer numParts;
    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("content")
    @Expose
    private String content;
}
