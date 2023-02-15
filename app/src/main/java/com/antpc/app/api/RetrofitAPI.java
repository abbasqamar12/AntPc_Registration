package com.antpc.app.api;

import com.antpc.app.models.BulkDataRequest;
import com.antpc.app.models.OtpRequest;
import com.antpc.app.models.OtpResponse;
import com.antpc.app.models.QuizResultRequest;
import com.antpc.app.models.QuizResultResponse;
import com.antpc.app.models.SignUpResponse;
import com.antpc.app.models.SignUpResponseAntplay;
import com.antpc.app.models.SignupRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
   /* @POST("api-register/")
    Call<SignUpResponse> signupAPIRequest(@Body SignupRequest signupRequest);*/

    @POST("antplayApi.php/")
    Call<SignUpResponseAntplay> signupAPIRequest(@Body SignupRequest signupRequest);

    @POST("api-register_bulk/")
    Call<SignUpResponse> bulkSignupAPIRequest(@Body BulkDataRequest bulkDataRequest);

    @POST("send/")
    Call<OtpResponse> otpAPIRequest(@Body OtpRequest otpRequest);

    @POST("antplayquiz.php/")
    Call<QuizResultResponse> quizAPIRequest(@Body QuizResultRequest quizResultRequest);

}
