package com.zuehlke.sistemzaizdavanjevozila.dao.impl;

import com.zuehlke.sistemzaizdavanjevozila.core.ItemTypeInfoDTO;
import com.zuehlke.sistemzaizdavanjevozila.dao.ItemTypeDao;
import com.zuehlke.sistemzaizdavanjevozila.model.ItemType;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class ItemTypeDaoImpl implements ItemTypeDao {

    private SessionFactory sessionFactory;

    @Autowired
    public ItemTypeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ItemType> getItemTypes() {
        return sessionFactory.getCurrentSession().createCriteria(ItemType.class).setResultTransformer((Criteria.DISTINCT_ROOT_ENTITY)).list();
    }

    @Override
    public ItemType getItemTypeById(Long id) {
        return (ItemType) sessionFactory.getCurrentSession().get(ItemType.class, id);
    }

    @Override
    public void addItemType(ItemType itemType) {
        sessionFactory.getCurrentSession().save(itemType);
    }

    @Override
    public void setItemType(ItemType itemType) {
        sessionFactory.getCurrentSession().update(itemType);
    }

    @Override
    public List<ItemType> getItemTypeByName(String name) {
        return sessionFactory.getCurrentSession().getNamedQuery("getItemTypeByName").setString("name", "%" + name + "%").list();
    }

    @Override
    public void deleteItemType(ItemType itemType) {
        sessionFactory.getCurrentSession().delete(itemType);
    }

    @Override
    public List<ItemTypeInfoDTO> getAvailableItemTypes(Date startDate, Date endDate) {
        /**TODO:
         * Thread safety
         */
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String query =
                "select " +
                        "new com.zuehlke.sistemzaizdavanjevozila.core.ItemTypeInfoDTO(it.id, count(*),it.name ) " +
                        "from ItemType it join it.items i " +
                        "where i.id not in " +
                        "(select distinct i.id " +
                        "from ReservationEntry  re left outer join re.item i " +
                        "where (re.reservationStartDate <= :endDate and re.reservationEndDate >= :startDate)) " +
                        "group by it.id";
        List<ItemTypeInfoDTO> itemTypeInfoDTOList =
                sessionFactory.getCurrentSession().createQuery(query)
                        .setString("startDate", df.format(startDate))
                        .setString("endDate", df.format(endDate))
                        .list();
        return itemTypeInfoDTOList;
    }

}

