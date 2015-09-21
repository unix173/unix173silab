package com.zuehlke.sistemzaizdavanjevozila.core;

import com.zuehlke.sistemzaizdavanjevozila.form.DodajStavkuRezervacijeForm;
import com.zuehlke.sistemzaizdavanjevozila.model.StavkaRezervacije;
import com.zuehlke.sistemzaizdavanjevozila.model.Vozilo;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RezervacijaUtilImpl implements RezervacijaUtil{

    public Set<Long> reservedItemTypeIdList(List<DodajStavkuRezervacijeForm> dodajStavkuRezervacijeForms) {
        Set<Long> reservedItemTypeIdSet = new HashSet<Long>();
        for (DodajStavkuRezervacijeForm dodajStavkuRezervacijeForm : dodajStavkuRezervacijeForms) {
            reservedItemTypeIdSet.add(dodajStavkuRezervacijeForm.getItemTypeId());
        }
        return reservedItemTypeIdSet;
    }

    public double countReservationPrice(List<StavkaRezervacije> reservationEntries) {
        double totalPrice = 0;
        for (StavkaRezervacije stavkaRezervacije : reservationEntries) {
            totalPrice += stavkaRezervacije.getVozilo().getTipVozila().getCena() * ((stavkaRezervacije.getReservationEndDate().getTime() - stavkaRezervacije.getReservationStartDate().getTime()) / (1000*60*60*24));
        }
        return totalPrice;
    }

    public StavkaRezervacije createReservationEntry(Vozilo vozilo, DodajStavkuRezervacijeForm dodajStavkuRezervacijeForm) {
        StavkaRezervacije stavkaRezervacije = new StavkaRezervacije();
        stavkaRezervacije.setVozilo(vozilo);
        stavkaRezervacije.setReservationStartDate(dodajStavkuRezervacijeForm.getReservationStartDate());
        stavkaRezervacije.setReservationEndDate(dodajStavkuRezervacijeForm.getReservationEndDate());
        return stavkaRezervacije;
    }
}
