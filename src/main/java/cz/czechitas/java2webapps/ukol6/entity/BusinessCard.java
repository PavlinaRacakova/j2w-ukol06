package cz.czechitas.java2webapps.ukol6.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class BusinessCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String company;

    @NotBlank
    private String street;

    @NotBlank
    private String town;

    @Pattern(regexp = "\\d{5}", message="Zip code must be exactly 5 characters long")
    private String zipCode;

    @Email
    private String email;

    private String phoneNumber;

    @Pattern(regexp = "(https://www\\.|http://www\\.|https://|http://)?[a-zA-Z0-9]{2,}(\\.[a-zA-Z0-9]{2,})(\\.[a-zA-Z0-9]{2,})?", message = "Insert valid URL")
    private String web;

    public String getEntireAddress() {
        return String.format("%s %s %s", street, town, zipCode);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
