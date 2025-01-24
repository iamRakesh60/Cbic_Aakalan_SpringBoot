package com.Cbic_Aakalan_Project.Service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationService {
    static  final Map<String, String> emailOtpMapping = new HashMap<>();

    public Map<String, String> verifyOtp(String email, String otp) {
        String storedOtp = emailOtpMapping.get(email);
        return null;
    }
}
