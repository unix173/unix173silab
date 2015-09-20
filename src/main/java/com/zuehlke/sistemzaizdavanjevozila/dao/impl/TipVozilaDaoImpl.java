package com.zuehlke.sistemzaizdavanjevozila.dao.impl;

import com.zuehlke.sistemzaizdavanjevozila.core.ItemTypeInfoDTO;
import com.zuehlke.sistemzaizdavanjevozila.dao.TipVozilaDao;
import com.zuehlke.sistemzaizdavanjevozila.model.TipVozila;
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
public class TipVozilaDaoImpl implements TipVozilaDao {

    private SessionFactory sessionFactory;

    @Autowired
    public TipVozilaDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<TipVozila> getItemTypes() {
        return sessionFactory.getCurrentSession().createCriteria(TipVozila.class).setResultTransformer((Criteria.DISTINCT_ROOT_ENTITY)).list();
    }

    @Override
    public TipVozila getItemTypeById(Long id) {
        return (TipVozila) sessionFactory.getCurrentSession().get(TipVozila.class, id);
    }

    @Override
    public void addItemType(TipVozila tipVozila) {
        sessionFactory.getCurrentSession().save(tipVozila);
    }

    @Override
    public void setItemType(TipVozila tipVozila) {
        sessionFactory.getCurrentSession().update(tipVozila);
    }

    @Override
    public List<TipVozila> getItemTypeByName(String name) {
        return sessionFactory.getCurrentSession().getNamedQuery("getItemTypeByName").setString("name", "%" + name + "%").list();
    }

    @Override
    public void deleteItemType(TipVozila tipVozila) {
        sessionFactory.getCurrentSession().delete(tipVozila);
    }

    @Override
    public List<ItemTypeInfoDTO> getAvailableItemTypes(Date startDate, Date endDate) {
        /**TODO:
         * Thread safety
         */
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String query =
                "select " +
                        "new com.zuehlke.sistemzaizdavanjevozila.core.ItemTypeInfoDTO(it.id, count(*),it.ime ) " +
                        "from TipVozila it join it.vozila i " +
                        "where i.id not in " +
                        "(select distinct i.id " +
                        "from StavkaRezervacije  re left outer join re.vozilo i " +
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

