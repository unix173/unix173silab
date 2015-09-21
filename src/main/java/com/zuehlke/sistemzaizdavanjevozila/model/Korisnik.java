package com.zuehlke.sistemzaizdavanjevozila.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@NamedQueries(
        @NamedQuery(name = "getUsersByUsername",
                query = "from Korisnik u where (u.ime like :name and u.isAdmin = 0)")
)
public class Korisnik {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String ime;

    private String prezime;

    @Column(nullable = false)
    private Boolean isAdmin;

    private Boolean enabled;

    private String confirmationId;

    @OneToMany(mappedBy = "korisnik", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Rezervacija> rezervacijas;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }


    public Set<Rezervacija> getRezervacijas() {
        return rezervacijas;
    }

    public void setRezervacijas(Set<Rezervacija> rezervacijas) {
        this.rezervacijas = rezervacijas;
    }

    public Boolean IsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(String confirmationId) {
        this.confirmationId = confirmationId;
    }
}
