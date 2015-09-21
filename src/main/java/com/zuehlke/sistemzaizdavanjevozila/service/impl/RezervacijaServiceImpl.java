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
    public List<Rezervacija> vratiRezervacije() {
        return rezervacijaDao.vratiRezervacije();
    }

    @Override
    public Rezervacija ucitajRezervacijuID(Long id) {
        return (rezervacijaDao.ucitajRezervacijuID(id));
    }

    @Override
    public void sacuvajRezervaciju(Rezervacija rezervacija) {
        rezervacijaDao.sacuvajRezervaciju(rezervacija);
    }

    @Override
    public void izmeniRezervaciju(Rezervacija rezervacija) {
        rezervacijaDao.izmeniRezervaciju(rezervacija);
    }

    @Override
    public void obrisiRezervaciju(Rezervacija rezervacija) {
        rezervacijaDao.obrisiRezervaciju(rezervacija);
    }

    @Override
    public List<Rezervacija> vratiRezervacijeUserID(Long id) {
        return rezervacijaDao.ucitajRezervacijeKorisnik(id);
    }

    @Override
    public Rezervacija kreirajRezervaciju(List<DodajStavkuRezervacijeForm> dodajStavkuRezervacijeForms, Korisnik korisnik) {
        List<StavkaRezervacije> reservationEntries = getReservationEntries(dodajStavkuRezervacijeForms);
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setDatumKreiranja(new Date());
        rezervacija.setUkupnaCena(rezervacijaUtil.izracunajCenuRezervacije(reservationEntries));
        rezervacija.setKorisnik(korisnik);
        rezervacija.setStatusRezervacije(StatusRezervacije.CREATED);
        for (StavkaRezervacije stavkaRezervacije : reservationEntries) {
            rezervacija.dodajStavkuRezervacije(stavkaRezervacije);
        }
        rezervacija.setStavkeRezervacije(new HashSet<StavkaRezervacije>(reservationEntries));
        return rezervacija;
    }

    private List<StavkaRezervacije> getReservationEntries(List<DodajStavkuRezervacijeForm> dodajStavkuRezervacijeForms) {
        List<StavkaRezervacije> reservationEntries = new ArrayList<StavkaRezervacije>();
        for (DodajStavkuRezervacijeForm dodajStavkuRezervacijeForm : dodajStavkuRezervacijeForms) {
            List<Vozilo> vozilos = voziloService.vratiAdekvatnaVozilaZaRezervisanje(dodajStavkuRezervacijeForm.getItemTypeId(), dodajStavkuRezervacijeForm.getDesiredQuantity(), dodajStavkuRezervacijeForm.getReservationStartDate(), dodajStavkuRezervacijeForm.getReservationEndDate());
            for (Vozilo vozilo : vozilos) {
                reservationEntries.add(rezervacijaUtil.kreirajStavkuRezervacije(vozilo, dodajStavkuRezervacijeForm));
            }
        }
        return reservationEntries;
    }
}
