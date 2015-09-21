package com.zuehlke.sistemzaizdavanjevozila.service.impl;

import com.zuehlke.sistemzaizdavanjevozila.core.RezervacijaUtil;
import com.zuehlke.sistemzaizdavanjevozila.dao.RezervacijaDao;
import com.zuehlke.sistemzaizdavanjevozila.form.DodajStavkuRezervacijeForm;
import com.zuehlke.sistemzaizdavanjevozila.model.*;
import com.zuehlke.sistemzaizdavanjevozila.service.RezervacijaService;
import com.zuehlke.sistemzaizdavanjevozila.service.VoziloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class RezervacijaServiceImpl implements RezervacijaService {

    @Autowired
    private RezervacijaDao rezervacijaDao;

    @Autowired
    private VoziloService voziloService;

    @Autowired
    RezervacijaUtil rezervacijaUtil;

    @Override
    public List<Rezervacija> getReservations() {
        return rezervacijaDao.getReservations();
    }

    @Override
    public Rezervacija getReservationById(Long id) {
        return (rezervacijaDao.getReservationById(id));
    }

    @Override
    public void addReservation(Rezervacija rezervacija) {
        rezervacijaDao.addReservation(rezervacija);
    }

    @Override
    public void setReservation(Rezervacija rezervacija) {
        rezervacijaDao.setReservation(rezervacija);
    }

    @Override
    public void deleteReservation(Rezervacija rezervacija) {
        rezervacijaDao.deleteReservation(rezervacija);
    }

    @Override
    public List<Rezervacija> getReservationsByUserId(Long id) {
        return rezervacijaDao.getReservationsByUserId(id);
    }

    @Override
    public Rezervacija createReservation(List<DodajStavkuRezervacijeForm> dodajStavkuRezervacijeForms, Korisnik korisnik) {
        List<StavkaRezervacije> reservationEntries = getReservationEntries(dodajStavkuRezervacijeForms);
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setCreationDate(new Date());
        rezervacija.setPrice(rezervacijaUtil.countReservationPrice(reservationEntries));
        rezervacija.setKorisnik(korisnik);
        rezervacija.setStatusRezervacije(StatusRezervacije.CREATED);
        for (StavkaRezervacije stavkaRezervacije : reservationEntries) {
            rezervacija.addReservationEntry(stavkaRezervacije);
        }
        rezervacija.setReservationEntries(new HashSet<StavkaRezervacije>(reservationEntries));
        return rezervacija;
    }

    private List<StavkaRezervacije> getReservationEntries(List<DodajStavkuRezervacijeForm> dodajStavkuRezervacijeForms) {
        List<StavkaRezervacije> reservationEntries = new ArrayList<StavkaRezervacije>();
        for (DodajStavkuRezervacijeForm dodajStavkuRezervacijeForm : dodajStavkuRezervacijeForms) {
            List<Vozilo> vozilos = voziloService.getBestItemsForReservationFromItemTypeId(dodajStavkuRezervacijeForm.getItemTypeId(), dodajStavkuRezervacijeForm.getDesiredQuantity(), dodajStavkuRezervacijeForm.getReservationStartDate(), dodajStavkuRezervacijeForm.getReservationEndDate());
            for (Vozilo vozilo : vozilos) {
                reservationEntries.add(rezervacijaUtil.createReservationEntry(vozilo, dodajStavkuRezervacijeForm));
            }
        }
        return reservationEntries;
    }
}
