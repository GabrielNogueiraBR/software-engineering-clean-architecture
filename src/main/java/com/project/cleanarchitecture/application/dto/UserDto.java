package com.project.cleanarchitecture.application.dto;

public class UserDto {
	
	private Long id;
    
    private String name;
    
    private String email;
    
    private String cpf;
    
    public UserDto() {}
    
    public UserDto(String name, String email, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }
    
    public Long getId() {
    	return this.id;
    }
    
    public void setId(Long id) {
    	this.id = id;
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
    
}
