package mapper;

import dto.request.RegisterRequest;
import dto.response.UserResponse;
import model.User;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class UserMapper {

    // Request DTO -> Entity
    public User toEntity(RegisterRequest dto) {
        if (dto == null) return null;
        User u = new User();
        u.setNombre(dto.getNombre());
        u.setCorreo(dto.getCorreo());
        u.setContrasena(dto.getContrasena()); // raw; service must hash
        u.setDireccion(dto.getDireccion());
        u.setTelefono(dto.getTelefono());
        u.setFechaRegistro(LocalDateTime.now());
        return u;
    }

    // Entity -> Response DTO (no password)
    public UserResponse toResponse(User u) {
        if (u == null) return null;
        UserResponse r = new UserResponse();
        r.setId(u.getId());
        r.setNombre(u.getNombre());
        r.setCorreo(u.getCorreo());
        r.setDireccion(u.getDireccion());
        r.setTelefono(u.getTelefono());
        r.setFechaRegistro(u.getFechaRegistro());
        return r;
    }
}
