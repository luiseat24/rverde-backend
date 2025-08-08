package controller;

import dto.response.UserResponse;
import mapper.UserMapper;
import model.User;
import repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserController(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    // Obtener perfil del usuario autenticado (usa el username del token = correo)
    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(Authentication authentication) {
        String correo = (String) authentication.getPrincipal();
        User u = userRepository.findByCorreo(correo).orElseThrow(() -> new RuntimeException("No encontrado"));
        return ResponseEntity.ok(mapper.toResponse(u));
    }

    // Listar usuarios (protegido)
    @GetMapping
    public ResponseEntity<List<UserResponse>> list() {
        List<UserResponse> list = userRepository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
