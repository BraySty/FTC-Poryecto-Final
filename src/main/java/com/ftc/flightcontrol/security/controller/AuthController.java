package com.ftc.flightcontrol.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftc.flightcontrol.security.auth.AuthService;
import com.ftc.flightcontrol.security.entitys.AuthResponse;
import com.ftc.flightcontrol.security.entitys.LoginRequest;
import com.ftc.flightcontrol.security.entitys.RegisterRequest;

import lombok.RequiredArgsConstructor;

/**
 * Controlador con los endpoinds para el registro y el login seguro
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}
	
	@PostMapping("/auth/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authService.register(request));
    }
    
}
