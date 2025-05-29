package com.springdemo.helloworld;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class AppUser {

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 0, message = "Age Minimum 0")
    @Max(value = 120, message = "Max Age Reached")
    private int age;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    public AppUser(){}
    public AppUser(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
