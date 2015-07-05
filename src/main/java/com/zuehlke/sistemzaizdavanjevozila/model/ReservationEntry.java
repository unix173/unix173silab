package com.zuehlke.sistemzaizdavanjevozila.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ReservationEntry {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Reservation reservation;

    @OneToOne
    private Item item;

    private Date reservationStartDate;

    private Date reservationEndDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
