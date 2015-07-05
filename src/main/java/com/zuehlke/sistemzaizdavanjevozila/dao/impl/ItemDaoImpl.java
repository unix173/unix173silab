package com.zuehlke.sistemzaizdavanjevozila.dao.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.ItemDao;
import com.zuehlke.sistemzaizdavanjevozila.model.Item;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ItemDaoImpl implements ItemDao {

    private SessionFactory sessionFactory;

    @Autowired
    public ItemDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Item> getItems() {
        return sessionFactory.getCurrentSession().createCriteria(Item.class).list();
    }

    @Override
    public Item getItemById(String id) {
        return (Item) sessionFactory.getCurrentSession().get(Item.class, id);
    }

    @Override
    public void addItem(Item item) {
        sessionFactory.getCurrentSession().save(item);
    }

    @Override
    public void setItem(Item item) {
        sessionFactory.getCurrentSession().update(item);
    }

    @Override
    public void deleteItem(Item item) {
        sessionFactory.getCurrentSession().delete(item);
    }

    @Override
    public List<Item> getAvailableItemsOfItemType(Long itemTypeId, String startDate, String endDate) {
        String query = "from Item i " +
                "where i.itemType.id = :itemTypeId and i.id not in " +
                "(select distinct i.id " +
                "from ReservationEntry  re right outer join re.item i " +
                "where (re.reservationStartDate <= :endDate and re.reservationEndDate >= :startDate))";

            List<Item> items =
                    sessionFactory.getCurrentSession().createQuery(query)
                            .setString("startDate", startDate)
                            .setString("endDate", endDate)
                            .setLong("itemTypeId", itemTypeId)
                            .list();
            return items;
        }
}



