package dto.response;

public class JwtResponse {
    private String token;
    private String tipo = "Bearer";

    public JwtResponse() {}
    public JwtResponse(String token) { this.token = token; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
