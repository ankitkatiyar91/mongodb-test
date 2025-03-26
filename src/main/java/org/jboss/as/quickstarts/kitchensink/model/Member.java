package org.jboss.as.quickstarts.kitchensink.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Member implements Serializable {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String name;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Size(min = 10, max = 12)
    @Digits(fraction = 0, integer = 12)
    @Column(name = "phone_number")
    private String phoneNumber;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return this.id;
    }

    public @NotNull @Size(min = 1, max = 25) @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers") String getName() {
        return this.name;
    }

    public @NotNull @NotEmpty @Email String getEmail() {
        return this.email;
    }

    public @NotNull @Size(min = 10, max = 12) @Digits(fraction = 0, integer = 12) String getPhoneNumber() {
        return this.phoneNumber;
    }
}