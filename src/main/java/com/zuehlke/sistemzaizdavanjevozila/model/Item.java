package com.zuehlke.sistemzaizdavanjevozila.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Item {

    @Id
    private String id;

    @ManyToOne
    @NotNull
    private TipVozila tipVozila;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipVozila getTipVozila() {
        return tipVozila;
    }

    public void setTipVozila(TipVozila tipVozila) {
        this.tipVozila = tipVozila;
    }

    @Override
    public String toString() {
        return " Item ID: " + id;
    }
}
