package fr.iut.rm.persistence.dao;

import fr.iut.rm.persistence.dao.guice.AbstractDaoTest;
import fr.iut.rm.persistence.domain.Room;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class RoomDaoTest extends AbstractDaoTest {

    @Test
    public void testPersistAndGet() {
        final Room room = new Room();
        room.setName("room name");
        room.setDescription("room description");

        injector.getInstance(RoomDao.class).saveOrUpdate(room);

        // Detach entity from persistence bag
        detach(room);

        assertThat(room.getId()).isPositive();

        //Perform DAO call
        Room found = injector.getInstance(RoomDao.class).get(room.getId());
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(room.getName());
        assertThat(found.getDescription()).isEqualTo(room.getDescription());
    }

}
