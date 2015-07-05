package com.zuehlke.sistemzaizdavanjevozila.service.impl;

import com.zuehlke.sistemzaizdavanjevozila.core.ReservationUtil;
import com.zuehlke.sistemzaizdavanjevozila.dao.ReservationDao;
import com.zuehlke.sistemzaizdavanjevozila.form.AddReservationEntryForm;
import com.zuehlke.sistemzaizdavanjevozila.model.*;
import com.zuehlke.sistemzaizdavanjevozila.service.ItemService;
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
    private ItemService itemService;

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
    public Reservation createReservation(List<AddReservationEntryForm> addReservationEntryForms, User user) {
        List<ReservationEntry> reservationEntries = getReservationEntries(addReservationEntryForms);
        Reservation reservation = new Reservation();
        reservation.setCreationDate(new Date());
        reservation.setPrice(ReservationUtil.countReservationPrice(reservationEntries));
        reservation.setUser(user);
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
            List<Item> items = itemService.getBestItemsForReservationFromItemTypeId(addReservationEntryForm.getItemTypeId(), addReservationEntryForm.getDesiredQuantity(), addReservationEntryForm.getReservationStartDate(), addReservationEntryForm.getReservationEndDate());
            for (Item item : items) {
                reservationEntries.add(ReservationUtil.createReservationEntry(item, addReservationEntryForm));
            }
        }
        return reservationEntries;
    }
}
