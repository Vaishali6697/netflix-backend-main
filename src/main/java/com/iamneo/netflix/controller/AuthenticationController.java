package com.iamneo.netflix.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.iamneo.netflix.dto.request.ForgotPasswordRequest;
import com.iamneo.netflix.dto.request.LoginRequest;
import com.iamneo.netflix.dto.request.RegisterRequest;
import com.iamneo.netflix.dto.response.BasicResponse;
import com.iamneo.netflix.dto.response.LoginResponse;
import com.iamneo.netflix.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/netflix/api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        BasicResponse<String> response =  new BasicResponse<>();
        try {
            response = authenticationService.register(registerRequest);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Something went wrong.");
            response.setData("");
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        BasicResponse<LoginResponse> response = new BasicResponse<>();
        try {
            response = authenticationService.login(loginRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Something went wrong.");
            response.setData(LoginResponse.builder().accessToken("").build());
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PatchExchange("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest){
        BasicResponse<String> response = new BasicResponse<>();
        try {
            response = authenticationService.forgotPassword(forgotPasswordRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Something went wrong.");
            response.setData("");
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
