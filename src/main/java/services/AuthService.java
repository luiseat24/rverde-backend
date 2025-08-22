package services;

import dto.response.JwtResponse;
import dto.request.LoginRequest;
import dto.request.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    JwtResponse login(LoginRequest request);
}
