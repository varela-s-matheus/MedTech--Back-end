package com.br.medtech.controller;

import com.br.medtech.model.UserAcess;
import com.br.medtech.service.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UserAcessController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserAcess userAcess) {
        var token = new UsernamePasswordAuthenticationToken(userAcess.getEmail(), userAcess.getPassword());
        // Ensure that manager.authenticate is not causing recursive calls
        var authentication = manager.authenticate(token);
        
        return ResponseEntity.ok(tokenService.generateToken((UserAcess) authentication.getPrincipal()));
    }


}
