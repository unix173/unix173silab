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
    public void addItem(Vozilo vozilo) {
        voziloDao.addItem(vozilo);
    }

    @Override
    public void setItem(Vozilo vozilo) {
        voziloDao.setItem(vozilo);
    }

    @Override
    public void deleteItem(Vozilo vozilo) {
        voziloDao.deleteItem(vozilo);
    }

    @Override
    public List<Vozilo> getBestItemsForReservationFromItemTypeId(Long id, Integer quantity, Date startDate, Date endDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<Vozilo> vozila = new ArrayList<Vozilo>();
        List<Vozilo> availableVozilos = voziloDao.getAvailableItemsOfItemType(id, df.format(startDate), df.format(endDate));

        for(Vozilo vozilo : availableVozilos){
            if(quantity-- == 0) break;
            vozila.add(vozilo);
        }
        return vozila;
    }

    @Override
    public List<Vozilo> getItems() {
        return voziloDao.getItems();
    }

    @Override
    public Vozilo getItemById(String id) {
        return voziloDao.getItemById(id);
    }
}
