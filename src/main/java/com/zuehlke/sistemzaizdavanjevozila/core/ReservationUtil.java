package com.zuehlke.sistemzaizdavanjevozila.core;

import com.zuehlke.sistemzaizdavanjevozila.form.AddReservationEntryForm;
import com.zuehlke.sistemzaizdavanjevozila.model.StavkaRezervacije;
import com.zuehlke.sistemzaizdavanjevozila.model.Vozilo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReservationUtil {

    public static Set<Long> reservedItemTypeIdList(List<AddReservationEntryForm> addReservationEntryForms) {
        Set<Long> reservedItemTypeIdSet = new HashSet<Long>();
        for (AddReservationEntryForm addReservationEntryForm : addReservationEntryForms) {
            reservedItemTypeIdSet.add(addReservationEntryForm.getItemTypeId());
        }
        return reservedItemTypeIdSet;
    }

    public static double countReservationPrice(List<StavkaRezervacije> reservationEntries) {
        double totalPrice = 0;
        for (StavkaRezervacije stavkaRezervacije : reservationEntries) {
            totalPrice += stavkaRezervacije.getVozilo().getTipVozila().getCena() * ((stavkaRezervacije.getReservationEndDate().getTime() - stavkaRezervacije.getReservationStartDate().getTime()) / (1000*60*60*24));
        }
        return totalPrice;
    }

    public static StavkaRezervacije createReservationEntry(Vozilo vozilo, AddReservationEntryForm addReservationEntryForm) {
        StavkaRezervacije stavkaRezervacije = new StavkaRezervacije();
        stavkaRezervacije.setVozilo(vozilo);
        stavkaRezervacije.setReservationStartDate(addReservationEntryForm.getReservationStartDate());
        stavkaRezervacije.setReservationEndDate(addReservationEntryForm.getReservationEndDate());
        return stavkaRezervacije;
    }
}
