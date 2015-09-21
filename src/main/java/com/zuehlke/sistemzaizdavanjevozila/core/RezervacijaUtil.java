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

   Set<Long> vratiListuIDRezervisanihTipovaVozila(List<DodajStavkuRezervacijeForm> dodajStavkuRezervacijeForms);

   double izracunajCenuRezervacije(List<StavkaRezervacije> reservationEntries);

   StavkaRezervacije kreirajStavkuRezervacije(Vozilo vozilo, DodajStavkuRezervacijeForm dodajStavkuRezervacijeForm);
}
