package com.cakir.templateManagement.auth.service;

import com.cakir.templateManagement.auth.dto.AuthenticationRequestDTO;
import com.cakir.templateManagement.auth.dto.AuthenticationResponseDTO;
import com.cakir.templateManagement.auth.dto.RegisterRequestDTO;
import com.cakir.templateManagement.enums.Role;
import com.cakir.templateManagement.entity.UserEntity;
import com.cakir.templateManagement.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager, UserRepository repository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticationResponseDTO register(RegisterRequestDTO request) {
        String email = request.getEmail();
        String firstname = request.getFirstname();
        String lastname = request.getLastname();
        String password = request.getPassword();
        String generatedPassword = passwordEncoder.encode(password);
        Role role = Role.USER;

        UserEntity userEntity = new UserEntity(
                request.getEmail(),
                request.getFirstname(),
                request.getLastname(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER);

        repository.save(userEntity);
        var jwtToken = jwtService.generateToken(userEntity);
        return new AuthenticationResponseDTO(jwtToken);
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.findByEmail(request.getEmail()).
                orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(jwtToken);
    }
}
