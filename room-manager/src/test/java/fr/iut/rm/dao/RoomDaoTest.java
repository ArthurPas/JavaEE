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
        room.setDescription("TestDesc");


        RoomDao dao = this.getRoomDao();
        dao.saveOrUpdate(room);
        Room saved = dao.findByName(room.getName());

        assertThat(saved).isNotNull();
        assertThat(saved.getName()).isNotNull();
        assertThat(saved.getName()).isEqualTo(room.getName());
        assertThat(saved.getDescription()).isEqualTo("TestDesc");
    }
    @Test
    public void testDeleteByName() {

        Room room = new Room();
        room.setName("TestRoom");

        RoomDao dao = this.getRoomDao();
        dao.saveOrUpdate(room);
        dao.removeRoom("TestRoom");
        Room saved = dao.findByName("TestRoom");
        assertThat(saved).isNull();
    }
    private RoomDao getRoomDao() {
        return this.injector.getInstance(RoomDao.class);
    }

}
