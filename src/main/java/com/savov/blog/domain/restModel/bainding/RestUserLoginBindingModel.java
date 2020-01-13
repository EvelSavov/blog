package com.savov.blog.domain.restModel.bainding;

public class RestUserLoginBindingModel {

    private String username;

    private String password;


    public RestUserLoginBindingModel() {
    }

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
}
