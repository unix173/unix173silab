package com.zuehlke.sistemzaizdavanjevozila.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@NamedQueries(
        @NamedQuery(name="getItemTypeByName",
                query="from TipVozila i where i.ime like :name")
        )
public class TipVozila {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String ime;

    private Double cena;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tipVozila")
    private Set<Item> items;

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

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + ime + ", Price: " + cena;
    }
}
