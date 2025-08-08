package services;


import dto.*;
import model.User;
import repository.UserRepository;
import services.AuthService;
import security.JwtService;
import exception.DuplicateResourceException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsByCorreo(request.getCorreo())) {
            throw new DuplicateResourceException("Correo ya registrado");
        }
        User u = new User();
        u.setNombre(request.getNombre());
        u.setCorreo(request.getCorreo());
        u.setContrasena(passwordEncoder.encode(request.getContrasena()));
        u.setDireccion(request.getDireccion());
        u.setTelefono(request.getTelefono());
        u.setFechaRegistro(java.time.LocalDateTime.now());
        userRepository.save(u);
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena()));
        // si no lanza exception, auth ok
        String token = jwtService.generateToken(request.getCorreo());
        return new JwtResponse(token);
    }
}
