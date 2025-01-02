package com.tms.time_management_system.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String password;
    private String role;

    @ManyToMany(mappedBy = "users")
    private List<Session> sessions;

    public User() {
    }

    public User(Integer id, String firstName, String lastName, LocalDate dob, String email, String password, String role, List<Session> sessions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.role = role;
        this.sessions = sessions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Session> getMeasurements() {
        return sessions;
    }

    public void setMeasurements(List<Session> sessions) {
        this.sessions = sessions;
    }
}
