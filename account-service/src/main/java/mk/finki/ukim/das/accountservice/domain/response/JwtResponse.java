package mk.finki.ukim.das.accountservice.domain.response;

import mk.finki.ukim.das.accountservice.domain.model.UserId;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private UserId id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, UserId id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public UserId getId() {
        return id;
    }

    public void setId(UserId id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
}