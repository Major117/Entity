package fr.laposte.entity.dto;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    private String log;

    @NotBlank
    private String password;

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
