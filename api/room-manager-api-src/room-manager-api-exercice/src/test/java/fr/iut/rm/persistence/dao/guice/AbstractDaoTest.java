package fr.iut.rm.persistence.dao.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.UnitOfWork;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.util.Modules;
import fr.iut.rm.MainModule;
import fr.iut.rm.persistence.dao.PersistenceStarter;
import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import java.io.IOException;


/**
 * Abstract test class which starts a transaction at startup and rollbacks it at the end
 */
public abstract class AbstractDaoTest {

    protected Injector injector;


    @Before
    public void setUp() {
        //Initializes injector
        this.injector = Guice.createInjector(new JpaPersistModule("room-manager-test"), Modules.override(new MainModule()).with(new PersistenceTestModule()));

        //Init persistence layer
        PersistenceStarter persistenceStarter = this.injector.getInstance(PersistenceStarter.class);
        try {
            persistenceStarter.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.injector.getInstance(UnitOfWork.class).begin();
    }

    @After
    public void tearDown() {
        this.injector.getInstance(UnitOfWork.class).end();
        PersistenceStarter persistenceStarter = this.injector.getInstance(PersistenceStarter.class);
        persistenceStarter.stop();
    }

    /**
     * Persists object
     *
     * @param entities entities to persist
     */
    public void persist(Object... entities) {
        EntityManager em = this.injector.getProvider(EntityManager.class).get();
        em.getTransaction().begin();
        for (Object entity : entities) {
            em.persist(entity);
        }
        em.getTransaction().commit();
    }

    /**
     * Deletes object
     *
     * @param entities entities to delete
     */
    protected void delete(Object... entities) {
        EntityManager em = this.injector.getProvider(EntityManager.class).get();
        em.getTransaction().begin();
        for (Object entity : entities) {
            em.remove(entity);
        }
        em.getTransaction().commit();
    }

    /**
     * Detach object
     *
     * @param entity entity to detach
     */
    protected void detach(Object... entity) {
        EntityManager em = this.injector.getProvider(EntityManager.class).get();
        for (Object o : entity) {
            em.detach(o);
        }

    }

    /**
     * Loads entity from its identifier
     *
     * @param type entity type
     * @param id   object id
     * @return jpa managed object
     */
    protected <T> T reload(Class<T> type, Object id) {
        EntityManager em = this.injector.getProvider(EntityManager.class).get();
        return em.find(type, id);
    }
}
