package com.project.cleanarchitecture.application.dto;

public class UserCreateDto {
    
    private String name;
    
    private String email;
    
    private String cpf;
    
    private String password;
    
    public UserCreateDto() {}
    
    public UserCreateDto(String name, String email, String cpf, String password) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
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
    
    public String getCPF() {
    	return this.cpf;
    }
    
    public void setCPF(String cpf) {
    	this.cpf = cpf;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
}
