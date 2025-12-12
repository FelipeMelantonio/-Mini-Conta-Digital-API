package com.felipe.minicontadigital.controller;

import com.felipe.minicontadigital.dto.AuthLoginRequest;
import com.felipe.minicontadigital.dto.AuthLoginResponse;
import com.felipe.minicontadigital.dto.AuthRegisterRequest;
import com.felipe.minicontadigital.entity.Usuario;
import com.felipe.minicontadigital.enums.UserRole;
import com.felipe.minicontadigital.service.AuthService;
import com.felipe.minicontadigital.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthService authService;

    
    @PostMapping("/registrar")
    public ResponseEntity<Usuario> register(@RequestBody AuthRegisterRequest dto) {

        UserRole role = dto.getRole(); 

        Usuario usuario = authService.registrar(
                dto.getNome(),
                dto.getEmail(),
                dto.getSenha(),
                dto.getCpf(),
                role);

        return ResponseEntity.ok(usuario);
    }

   
    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@RequestBody AuthLoginRequest dto) {
        String token = authService.login(dto.getEmail(), dto.getSenha());
        return ResponseEntity.ok(new AuthLoginResponse(token));
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }
}
