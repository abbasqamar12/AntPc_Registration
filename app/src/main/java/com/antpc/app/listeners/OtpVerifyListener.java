package com.antpc.app.listeners;

public interface OtpVerifyListener {
    void otpVerified(String otp, boolean isVerified);
}
