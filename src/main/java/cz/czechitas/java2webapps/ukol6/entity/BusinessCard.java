package cz.czechitas.java2webapps.ukol6.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    @Pattern(regexp = "\\d{5}", message = "Zip code must be exactly 5 characters long")
    private String zipCode;

    @Email
    private String email;

    private String phoneNumber;

    @Pattern(regexp = "(https://www\\.|http://www\\.|https://|http://)?[a-zA-Z0-9]{2,}(\\.[a-zA-Z0-9]{2,})(\\.[a-zA-Z0-9]{2,})?", message = "Insert valid URL")
    private String web;

    public String getEntireAddress() {
        return String.format("%s %s %s", street, town, zipCode);
    }
}
