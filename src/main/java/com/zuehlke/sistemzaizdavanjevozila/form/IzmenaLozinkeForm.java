package com.zuehlke.sistemzaizdavanjevozila.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class IzmenaLozinkeForm {

    private Long id;

    private String oldPassword;

    @NotNull
    @Size(min = 5, max = 16)
    private String newPassword;
    private String matchingNewPassword;

    private String username;

    private String email;

    private String name;

    private String lastName;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getMatchingNewPassword() {
        return matchingNewPassword;
    }

    public void setMatchingNewPassword(String matchingNewPassword) {
        this.matchingNewPassword = matchingNewPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @AssertTrue(message="Sistem ne može da izmeni korisnika!")
    private boolean isValid() {
        return this.newPassword.equals(this.matchingNewPassword);
    }
}
