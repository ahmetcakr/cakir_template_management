package com.cakir.templateManagement.auth.controller;

import com.cakir.templateManagement.auth.dto.AuthenticationRequestDTO;
import com.cakir.templateManagement.auth.dto.AuthenticationResponseDTO;
import com.cakir.templateManagement.auth.dto.RegisterRequestDTO;
import com.cakir.templateManagement.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterRequestDTO request){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @RequestBody AuthenticationRequestDTO request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }



}
