package com.zuehlke.sistemzaizdavanjevozila.core;

import com.zuehlke.sistemzaizdavanjevozila.form.DodajStavkuRezervacijeForm;
import com.zuehlke.sistemzaizdavanjevozila.model.StavkaRezervacije;
import com.zuehlke.sistemzaizdavanjevozila.model.Vozilo;

import java.util.List;
import java.util.Set;

/**
 * Created by ivsi on 9/21/2015.
 */
public interface RezervacijaUtil {

   Set<Long> reservedItemTypeIdList(List<DodajStavkuRezervacijeForm> dodajStavkuRezervacijeForms);

   double countReservationPrice(List<StavkaRezervacije> reservationEntries);

   StavkaRezervacije createReservationEntry(Vozilo vozilo, DodajStavkuRezervacijeForm dodajStavkuRezervacijeForm);
}
