package com.example.hibernate.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_user_profile")
public class UserProfileModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "phone_number", length = 15)
    @Size(max = 15)
    private String phoneNumber;

    @Column(length = 10)
    private String gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dateOfBirth;

    @Column(length = 100)
    @Size(max = 100)
    private String address1;

    @Column(length = 100)
    @Size(max = 100)
    private String address2;

    @Column(length = 100)
    @Size(max = 100)
    private String street;

    @Column(length = 100)
    @Size(max = 100)
    private String city;

    @Column(length = 100)
    @Size(max = 100)
    private String state;

    @Column(length = 100)
    @Size(max = 100)
    private String country;

    @Column(name = "zip_code", length = 32)
    @Size(max = 32)
    private String zipCode;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user")
    private UserModel user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
