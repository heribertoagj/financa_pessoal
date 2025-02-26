package br.com.ttech.fp.backend.controller;

import br.com.ttech.fp.backend.common.dto.*;
import br.com.ttech.fp.backend.common.entity.User;
import br.com.ttech.fp.backend.configs.security.TokenService;
import br.com.ttech.fp.backend.service.impl.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthorizationService service;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value="/login")
    public ResponseEntity<ResponseDto> login(@RequestBody @Valid AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generate((User) auth.getPrincipal());
        ResponseDto response = service.userValidate(data, token);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value="/register")
    public ResponseEntity<ResponseDto> register(@RequestBody @Valid  ResgisterDto register){
        ResponseDto response = service.register(register);
        return ResponseEntity.ok(response);
    }

}
