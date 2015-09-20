package com.zuehlke.sistemzaizdavanjevozila.service.impl;

import com.zuehlke.sistemzaizdavanjevozila.core.ReservationUtil;
import com.zuehlke.sistemzaizdavanjevozila.dao.ReservationDao;
import com.zuehlke.sistemzaizdavanjevozila.form.AddReservationEntryForm;
import com.zuehlke.sistemzaizdavanjevozila.model.*;
import com.zuehlke.sistemzaizdavanjevozila.service.VoziloService;
import com.zuehlke.sistemzaizdavanjevozila.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private VoziloService voziloService;

    @Override
    public List<Reservation> getReservations() {
        return reservationDao.getReservations();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return (reservationDao.getReservationById(id));
    }

    @Override
    public void addReservation(Reservation reservation) {
        reservationDao.addReservation(reservation);
    }

    @Override
    public void setReservation(Reservation reservation) {
        reservationDao.setReservation(reservation);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        reservationDao.deleteReservation(reservation);
    }

    @Override
    public List<Reservation> getReservationsByUserId(Long id) {
        return reservationDao.getReservationsByUserId(id);
    }

    @Override
    public Reservation createReservation(List<AddReservationEntryForm> addReservationEntryForms, Korisnik korisnik) {
        List<ReservationEntry> reservationEntries = getReservationEntries(addReservationEntryForms);
        Reservation reservation = new Reservation();
        reservation.setCreationDate(new Date());
        reservation.setPrice(ReservationUtil.countReservationPrice(reservationEntries));
        reservation.setKorisnik(korisnik);
        reservation.setReservationStatus(ReservationStatus.CREATED);
        for (ReservationEntry reservationEntry : reservationEntries) {
            reservation.addReservationEntry(reservationEntry);
        }
        reservation.setReservationEntries(new HashSet<ReservationEntry>(reservationEntries));
        return reservation;
    }

    private List<ReservationEntry> getReservationEntries(List<AddReservationEntryForm> addReservationEntryForms) {
        List<ReservationEntry> reservationEntries = new ArrayList<ReservationEntry>();
        for (AddReservationEntryForm addReservationEntryForm : addReservationEntryForms) {
            List<Vozilo> vozilos = voziloService.getBestItemsForReservationFromItemTypeId(addReservationEntryForm.getItemTypeId(), addReservationEntryForm.getDesiredQuantity(), addReservationEntryForm.getReservationStartDate(), addReservationEntryForm.getReservationEndDate());
            for (Vozilo vozilo : vozilos) {
                reservationEntries.add(ReservationUtil.createReservationEntry(vozilo, addReservationEntryForm));
            }
        }
        return reservationEntries;
    }
}
