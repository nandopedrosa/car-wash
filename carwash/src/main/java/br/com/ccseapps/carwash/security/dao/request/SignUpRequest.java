package br.com.ccseapps.carwash.security.dao.request;

public class SignUpRequest {
    
    private String name;
    private String email;
    private String password;
    private String roles;

    public String getRoles() {
        return roles;
    }
    public void setRole(String roles) {
        this.roles = roles;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }    
}