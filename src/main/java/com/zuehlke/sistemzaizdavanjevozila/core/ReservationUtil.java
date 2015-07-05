package com.zuehlke.sistemzaizdavanjevozila.core;

import com.zuehlke.sistemzaizdavanjevozila.form.AddReservationEntryForm;
import com.zuehlke.sistemzaizdavanjevozila.model.Item;
import com.zuehlke.sistemzaizdavanjevozila.model.ReservationEntry;

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

    public static double countReservationPrice(List<ReservationEntry> reservationEntries) {
        double totalPrice = 0;
        for (ReservationEntry reservationEntry : reservationEntries) {
            totalPrice += reservationEntry.getItem().getItemType().getPrice() * ((reservationEntry.getReservationEndDate().getTime() - reservationEntry.getReservationStartDate().getTime()) / (1000*60*60*24));
        }
        return totalPrice;
    }

    public static ReservationEntry createReservationEntry(Item item, AddReservationEntryForm addReservationEntryForm) {
        ReservationEntry reservationEntry = new ReservationEntry();
        reservationEntry.setItem(item);
        reservationEntry.setReservationStartDate(addReservationEntryForm.getReservationStartDate());
        reservationEntry.setReservationEndDate(addReservationEntryForm.getReservationEndDate());
        return reservationEntry;
    }
}
