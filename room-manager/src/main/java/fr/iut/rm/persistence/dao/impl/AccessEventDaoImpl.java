package fr.iut.rm.persistence.dao.impl;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import fr.iut.rm.persistence.dao.AccessEventDao;
import fr.iut.rm.persistence.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import java.util.List;
@Singleton
public class AccessEventDaoImpl implements AccessEventDao {
    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RoomDaoImpl.class);

    /**
     * Entity Manager used to perform database operations
     */
    @Inject
    private Provider<EntityManager> em;

    @Override
    public List<AccessEvent> findAccessByRoom(String name) {
        StringBuilder query = new StringBuilder("from ");
        query.append(AccessEvent.class.getName()).append(" as ae");
        query.append(" where ae.").append(AccessEvent_.theRoom.getName()).append(". ").append(Room_.name.getName()).append(" = :name");
        List<AccessEvent> resultList = em.get().createQuery(query.toString()).setParameter("name", name).getResultList();
        return resultList;
    }

    @Override
    public List<AccessEvent> findAccessByPerson(String name) {
        StringBuilder query = new StringBuilder("from ");
        query.append(AccessEvent.class.getName()).append(" as ae");
        query.append(" where ae.").append(AccessEvent_.accessName.getName()).append(" = :name");
        List<AccessEvent> resultList = em.get().createQuery(query.toString()).setParameter("name", name).getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public void saveOrUpdate(AccessEvent accessEvent) {
        this.em.get().persist(accessEvent);
        logger.debug("Access Event '{}' saved", accessEvent.getId());
    }
}
