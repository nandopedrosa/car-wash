package br.com.ccseapps.carwash.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ccseapps.carwash.security.dao.request.SignInRequest;
import br.com.ccseapps.carwash.security.dao.request.SignUpRequest;
import br.com.ccseapps.carwash.security.dao.response.JwtAuthenticationResponse;
import br.com.ccseapps.carwash.security.service.AuthenticationService;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    
    @PostMapping("/auth/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}