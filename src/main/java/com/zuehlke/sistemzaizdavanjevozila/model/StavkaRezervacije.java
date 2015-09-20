package com.zuehlke.sistemzaizdavanjevozila.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class StavkaRezervacije {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Rezervacija rezervacija;

    @OneToOne
    private Vozilo vozilo;

    private Date reservationStartDate;

    private Date reservationEndDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    public Date getReservationStartDate() {
        return reservationStartDate;
    }

    public void setReservationStartDate(Date reservationStartDate) {
        this.reservationStartDate = reservationStartDate;
    }

    public Date getReservationEndDate() {
        return reservationEndDate;
    }

    public void setReservationEndDate(Date reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }

}
