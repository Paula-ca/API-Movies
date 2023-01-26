package com.example.challengeBackEnd.controller;

import com.example.challengeBackEnd.model.Rol;
import com.example.challengeBackEnd.model.TypeRol;
import com.example.challengeBackEnd.model.User;
import com.example.challengeBackEnd.security.Jwt.JwtProvider;
import com.example.challengeBackEnd.security.Payload.LoginRequest;
import com.example.challengeBackEnd.security.Payload.LoginResponse;
import com.example.challengeBackEnd.security.Payload.RegisterRequest;
import com.example.challengeBackEnd.service.impl.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtProvider jwtProvider;

    public static final Logger logger = Logger.getLogger(AuthController.class);

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest, BindingResult bindingResult) {

        logger.debug("Registrando usuario...");

        if (bindingResult.hasErrors()) {
            logger.error("Datos invalidos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos invalidos");
        }
        if (userService.existsUserByEmail(registerRequest.getEmail())) {
            logger.error("Correo ya registrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este correo ya se encuentra registrado");
        }

        User user = new User(registerRequest.getNombre(),
                registerRequest.getApellido(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getContrasenia()),
                "");

        // rol por defecto: USER
        Rol rol = new Rol();
        rol.setNombre(TypeRol.ROLE_ADMIN);
        user.setRol(rol);
        userService.add(user);

        logger.info("Usuario registrado: " + user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        logger.debug("Logeando usuario...");

        if (bindingResult.hasErrors()) {
            logger.error("Error al logear");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getContrasenia()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        LoginResponse token = new LoginResponse(jwt);

        logger.info("Usuario logeado");
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
