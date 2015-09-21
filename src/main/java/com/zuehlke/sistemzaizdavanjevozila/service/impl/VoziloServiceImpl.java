package com.zuehlke.sistemzaizdavanjevozila.service.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.VoziloDao;
import com.zuehlke.sistemzaizdavanjevozila.model.Vozilo;
import com.zuehlke.sistemzaizdavanjevozila.service.VoziloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VoziloServiceImpl implements VoziloService {


    private VoziloDao voziloDao;

    @Autowired
    public VoziloServiceImpl(VoziloDao voziloDao){
        this.voziloDao = voziloDao;
    }

    @Override
    public void sacuvajVozilo(Vozilo vozilo) {
        voziloDao.sacuvajVozilo(vozilo);
    }

    @Override
    public void izmeniVozilo(Vozilo vozilo) {
        voziloDao.izmeniVozilo(vozilo);
    }

    @Override
    public void obrisiVozilo(Vozilo vozilo) {
        voziloDao.obrisiVozilo(vozilo);
    }

    @Override
    public List<Vozilo> vratiAdekvatnaVozilaZaRezervisanje(Long id, Integer quantity, Date startDate, Date endDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<Vozilo> vozila = new ArrayList<Vozilo>();
        List<Vozilo> availableVozilos = voziloDao.vratiAdekvatnaVozilaZaRezervisanje(id, df.format(startDate), df.format(endDate));

        for(Vozilo vozilo : availableVozilos){
            if(quantity-- == 0) break;
            vozila.add(vozilo);
        }
        return vozila;
    }

    @Override
    public List<Vozilo> vratiVozila() {
        return voziloDao.vratiVozila();
    }

    @Override
    public Vozilo ucitajVoziloID(String id) {
        return voziloDao.ucitajVoziloID(id);
    }
}
