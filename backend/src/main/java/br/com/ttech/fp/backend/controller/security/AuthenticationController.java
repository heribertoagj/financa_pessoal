package br.com.ttech.fp.backend.controller.security;

import br.com.ttech.fp.backend.common.entity.User;
import br.com.ttech.fp.backend.common.records.security.Authentication;
import br.com.ttech.fp.backend.common.records.security.Resgister;
import br.com.ttech.fp.backend.common.records.Response;
import br.com.ttech.fp.backend.configs.security.TokenService;
import br.com.ttech.fp.backend.service.security.AuthorizationService;
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
    public ResponseEntity<Response> login(@RequestBody @Valid Authentication data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generate((User) auth.getPrincipal());
        Response response = service.userValidate(data, token);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value="/register")
    public ResponseEntity<Response> register(@RequestBody @Valid Resgister register){
        Response response = service.register(register);
        return ResponseEntity.ok(response);
    }
}
