package ru.albert.spring.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "Name should not is empty")
    @Size(min = 2, max = 15, message = "Name should be between 2 and 15 characters")
    private String name;

    @Min(value = 0, message = "Age > 0")
    private int age;

    @NotEmpty(message = "Email should not is empty")
    @Email(message = "Email is not valid")
    private String email;

    public Person() {

    }

    public Person(String name, int id, int age, String email) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
