package com.example.hibernate.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tbl_user")
public class UserModel implements Serializable {
    @Id
    @NotNull
    @Column(name = "id_user",length = 16)
    private Long id;

    @Column(name = "first_name", length = 50)
    @Size(max = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    @Size(max = 50)
    private String lastName;

    @Column(length = 30, unique = true)
    @Email
    @Size(max = 30)
    private String email;

    @Column(length = 16)
    @Size(max = 16)
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private UserProfileModel profile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public UserProfileModel getProfile() {
        return profile;
    }

    public void setProfile(UserProfileModel profile) {
        this.profile = profile;
    }
}
