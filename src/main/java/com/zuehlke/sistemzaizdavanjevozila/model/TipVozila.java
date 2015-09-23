package com.zuehlke.sistemzaizdavanjevozila.model;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@NamedQueries(
        @NamedQuery(name="pretraziTipoveVozilaIme",
                query="from TipVozila i where i.ime like :name")
        )
public class TipVozila {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String ime;

    @NumberFormat
    private Double cena;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tipVozila")
    private Set<Vozilo> vozila;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Set<Vozilo> getVozila() {
        return vozila;
    }

    public void setVozila(Set<Vozilo> vozila) {
        this.vozila = vozila;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + ime + ", Price: " + cena;
    }
}
