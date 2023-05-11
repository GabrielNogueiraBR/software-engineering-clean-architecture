package com.project.cleanarchitecture.domain.model;

import javax.persistence.*;

import com.project.cleanarchitecture.domain.vo.CPF;
import com.project.cleanarchitecture.domain.vo.Email;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private Email email;
    
    @Column(nullable = false, unique = true)
    private CPF cpf;

    @Column(nullable = false)
    private String password;


    public User() {}

    public User(String name, Email email, CPF cpf, String password) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
    }


    public Long getId() {
        return id;
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

    public Email getEmail() {
        return email;
    }

    public CPF getCPF() {
    	return cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
