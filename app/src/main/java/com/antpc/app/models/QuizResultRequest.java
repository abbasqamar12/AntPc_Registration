package com.antpc.app.models;


import com.antpc.app.utils.Const;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizResultRequest {
    @SerializedName("id")
    @Expose
    private String userId;
    @SerializedName("score")
    @Expose
    private String score;


    public QuizResultRequest(String userId, String score) {
        this.userId = userId;
        this.score = score;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
