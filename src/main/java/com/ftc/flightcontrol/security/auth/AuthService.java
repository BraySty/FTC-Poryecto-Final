package com.ftc.flightcontrol.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftc.flightcontrol.entitys.Role;
import com.ftc.flightcontrol.entitys.usuarios.Usuario;
import com.ftc.flightcontrol.repository.UsuarioRepository;
import com.ftc.flightcontrol.security.service.JwtService;
import com.ftc.flightcontrol.security.entitys.AuthResponse;
import com.ftc.flightcontrol.security.entitys.LoginRequest;
import com.ftc.flightcontrol.security.entitys.RegisterRequest;

import lombok.RequiredArgsConstructor;

/**
 * Clase de Servicios para seguridad usando JWT
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        Usuario user = repo.findByNombre(request.getUserName()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .result(true)
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Usuario user = Usuario.builder()
            .dni(request.getDni())
            .nombre(request.getUserName())
            .apellido(request.getApellido())
            .correo(request.getCorreo())
            .password(passwordEncoder.encode( request.getPassword()))
            .role(new Role(1, "Cliente", null))
            .build();
            repo.save(user);

        return AuthResponse.builder()
        .token(jwtService.getToken(user))
        .result(true)
        .build();
    }

}
