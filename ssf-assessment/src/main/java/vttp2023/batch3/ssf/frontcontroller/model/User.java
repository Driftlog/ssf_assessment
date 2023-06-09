package vttp2023.batch3.ssf.frontcontroller.model;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {
    
    @Size(min=2)
    @NotBlank
    private String username;
    
    @Size(min=2)
    @NotBlank
    private String password;

    @Min(0)
    @Max(3)
    private int loginAttempts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public void addAttempt() {
        this.loginAttempts++;
    }
    

    public JsonObject toJson() {
        JsonObject user = Json.createObjectBuilder()
                    .add("username", this.username)
                    .add("password", this.password)
                    .build();
        
        return user;
    }

    

    
}
