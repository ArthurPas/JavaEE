package fr.iut.rm.dao;

import fr.iut.rm.dao.guice.AbstractDaoTest;
import fr.iut.rm.persistence.dao.RoomDao;
import fr.iut.rm.persistence.domain.Room;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class RoomDaoTest extends AbstractDaoTest {

    @Test
    public void testPersistAndFindByName() {

        Room room = new Room();
        room.setName("TestRoom");

        RoomDao dao = this.getRoomDao();
        dao.saveOrUpdate(room);
        Room saved = dao.findByName(room.getName());

        assertThat(saved).isNotNull();
        assertThat(saved.getName()).isNotNull();
        assertThat(saved.getName()).isEqualTo(room.getName());
    }

    private RoomDao getRoomDao() {
        return this.injector.getInstance(RoomDao.class);
    }

}
