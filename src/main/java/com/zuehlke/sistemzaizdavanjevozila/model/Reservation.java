package com.zuehlke.sistemzaizdavanjevozila.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries(
        @NamedQuery(name = "getReservationsByUserId", query = "from Reservation r where r.user.id = :userid")
)
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    private Date creationDate;

    private Double price;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "reservation")
    private Set<ReservationEntry> reservationEntries = new HashSet<ReservationEntry>();

    private ReservationStatus reservationStatus;

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Set<ReservationEntry> getReservationEntries() {
        return reservationEntries;
    }

    public void setReservationEntries(Set<ReservationEntry> reservationEntries) {
        this.reservationEntries = reservationEntries;
    }

    public void addReservationEntry(ReservationEntry reservationEntry) {
        reservationEntry.setReservation(this);
        reservationEntries.add(reservationEntry);
    }
}
