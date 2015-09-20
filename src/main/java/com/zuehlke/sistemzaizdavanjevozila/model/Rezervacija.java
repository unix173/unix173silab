package com.zuehlke.sistemzaizdavanjevozila.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries(
        @NamedQuery(name = "getReservationsByUserId", query = "from Rezervacija r where r.korisnik.id = :userid")
)
public class Rezervacija {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Korisnik korisnik;

    private Date creationDate;

    private Double price;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "rezervacija")
    private Set<StavkaRezervacije> reservationEntries = new HashSet<StavkaRezervacije>();

    private StatusRezervacije statusRezervacije;

    public StatusRezervacije getStatusRezervacije() {
        return statusRezervacije;
    }

    public void setStatusRezervacije(StatusRezervacije statusRezervacije) {
        this.statusRezervacije = statusRezervacije;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<StavkaRezervacije> getReservationEntries() {
        return reservationEntries;
    }

    public void setReservationEntries(Set<StavkaRezervacije> reservationEntries) {
        this.reservationEntries = reservationEntries;
    }

    public void addReservationEntry(StavkaRezervacije stavkaRezervacije) {
        stavkaRezervacije.setRezervacija(this);
        reservationEntries.add(stavkaRezervacije);
    }
}