package com.gg.occurrence_manager.controller;

import com.gg.occurrence_manager.infra.security.TokenService;
import com.gg.occurrence_manager.model.Usuario;
import com.gg.occurrence_manager.model.dto.AuthDTO;
import com.gg.occurrence_manager.model.dto.LoginResponseDTO;
import com.gg.occurrence_manager.model.dto.RegisterDTO;
import com.gg.occurrence_manager.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO authDTO) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authDTO.login(), authDTO.password());
        var auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (this.usuarioRepository.findByLogin(registerDTO.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        Usuario novoUsuario = new Usuario(registerDTO.login(), encodedPassword);

        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
