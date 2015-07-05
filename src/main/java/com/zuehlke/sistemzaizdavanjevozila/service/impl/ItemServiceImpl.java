package com.zuehlke.sistemzaizdavanjevozila.service.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.ItemDao;
import com.zuehlke.sistemzaizdavanjevozila.model.Item;
import com.zuehlke.sistemzaizdavanjevozila.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {


    private ItemDao itemDao;

    @Autowired
    public ItemServiceImpl(ItemDao itemDao){
        this.itemDao = itemDao;
    }

    @Override
    public void addItem(Item item) {
        itemDao.addItem(item);
    }

    @Override
    public void setItem(Item item) {
        itemDao.setItem(item);
    }

    @Override
    public void deleteItem(Item item) {
        itemDao.deleteItem(item);
    }

    @Override
    public List<Item> getBestItemsForReservationFromItemTypeId(Long id, Integer quantity, Date startDate, Date endDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<Item> items = new ArrayList<Item>();
        List<Item> availableItems = itemDao.getAvailableItemsOfItemType(id, df.format(startDate), df.format(endDate));

        for(Item item : availableItems){
            if(quantity-- == 0) break;
            items.add(item);
        }
        return items;
    }

    @Override
    public List<Item> getItems() {
        return itemDao.getItems();
    }

    @Override
    public Item getItemById(String id) {
        return itemDao.getItemById(id);
    }
}
