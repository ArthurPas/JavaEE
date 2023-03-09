package fr.iut.rm.web.api;


import com.sun.jersey.api.client.ClientResponse;
import fr.iut.rm.persistence.dao.AccessEventDao;
import fr.iut.rm.persistence.dao.RoomDao;
import fr.iut.rm.persistence.domain.AccessEvent;
import fr.iut.rm.persistence.domain.Room;
import fr.iut.rm.web.api.model.RoomVO;
import fr.iut.rm.web.guice.AbstractRestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class RoomResourceTest extends AbstractRestTest {

    private String resourcePath = "/rooms";

    @Test
    public void testGet() {
        String path = this.resourcePath + "/12";


        Room expected = new Room();
        expected.setId(12);
        expected.setName("Room name");
        expected.setDescription("Room desc");

        final AccessEvent event1 = new AccessEvent();
        final AccessEvent event2 = new AccessEvent();

        // Mock dao behaviour
        when(injector.getInstance(RoomDao.class).get(12L)).thenReturn(expected);
        when(injector.getInstance(AccessEventDao.class).findBy(12L, null)).thenReturn(Arrays.asList(event1, event2));

        // Perform API call
        ClientResponse response = resource().path(path).type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        assertThat(response.getClientResponseStatus().getFamily()).isEqualTo(Response.Status.Family.SUCCESSFUL);

        //TODO uncomment lines to verify implementation
//        RoomDetailsVO result = response.getEntity(RoomDetailsVO.class);
//
//        SoftAssertions.assertSoftly(soft -> {
//            soft.assertThat(result).isNotNull();
//            soft.assertThat(result.getId()).isEqualTo(12);
//            soft.assertThat(result.getName()).isEqualTo("Room name");
//            soft.assertThat(result.getDescription()).isEqualTo("Room desc");
//            soft.assertThat(result.getAccessEventsCount()).isEqualTo(2);
//        });
    }


    @Test
    public void testList() {
        String path = this.resourcePath;

        Room expected1 = new Room();
        expected1.setId(12);
        expected1.setName("Room name");
        expected1.setDescription("Room desc");

        Room expected2 = new Room();
        expected2.setId(13);
        expected2.setName("Room name 13");
        expected2.setDescription("Room desc 13");

        // Mock dao behaviour
        when(injector.getInstance(RoomDao.class).findAll())
                .thenReturn(Arrays.asList(expected1, expected2));

        when(injector.getInstance(RoomDao.class).searchByName("name filter"))
                .thenReturn(Arrays.asList(expected1));

        // Perform API call
        ClientResponse response = resource().path(path).type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        assertThat(response.getStatus()).isEqualTo(200);

        RoomVO[] result = response.getEntity(RoomVO[].class);

        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(result).hasSize(2);

            soft.assertThat(result[0].getId()).isEqualTo(12);
            soft.assertThat(result[0].getName()).isEqualTo("Room name");
            soft.assertThat(result[0].getDescription()).isEqualTo("Room desc");

            soft.assertThat(result[1].getId()).isEqualTo(13);
            soft.assertThat(result[1].getName()).isEqualTo("Room name 13");
            soft.assertThat(result[1].getDescription()).isEqualTo("Room desc 13");
        });

        //TODO uncomment lines to verify implementation
//        // Test query filter
//        final MultivaluedMap<String, String> params = new MultivaluedMapImpl();
//        params.put("q", Arrays.asList("name filter"));
//        response = resource()
//                .queryParams(params)
//                .path(path)
//                .type(MediaType.APPLICATION_JSON_TYPE)
//                .get(ClientResponse.class);
//
//        assertThat(response.getStatus()).isEqualTo(200);
//
//        final RoomVO[] result2 = response.getEntity(RoomVO[].class);
//
//        SoftAssertions.assertSoftly(soft -> {
//            soft.assertThat(result2).hasSize(1);
//
//            soft.assertThat(result[0].getId()).isEqualTo(12);
//            soft.assertThat(result[0].getName()).isEqualTo("Room name");
//            soft.assertThat(result[0].getDescription()).isEqualTo("Room desc");
//        });

        // Test without results

        // Mock dao behaviour
        when(injector.getInstance(RoomDao.class).findAll())
                .thenReturn(Collections.emptyList());

        response = resource().path(path).type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getEntity(RoomVO[].class)).isEmpty();
    }
    //TODO uncomment test to verify implementation
//    @Test
//    public void testCreate() {
//        String path = this.resourcePath;
//
//
//        SaveRoomVO roomToSave = new SaveRoomVO();
//        roomToSave.setName("Room name");
//        roomToSave.setDescription("Room desc");
//
//        // Perform API call
//        ClientResponse response = resource().path(path).type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, roomToSave);
//        assertThat(response.getStatus()).isEqualTo(200);
//
//        // Retrieve object given to the DAO
//        ArgumentCaptor<Room> captor = ArgumentCaptor.forClass(Room.class);
//        verify(injector.getInstance(RoomDao.class)).saveOrUpdate(captor.capture());
//
//        Room capturedRoom = captor.getValue();
//
//        SoftAssertions.assertSoftly(soft -> {
//            soft.assertThat(capturedRoom).isNotNull();
//            soft.assertThat(capturedRoom.getName()).isEqualTo("Room name");
//            soft.assertThat(capturedRoom.getDescription()).isEqualTo("Room desc");
//        });
//
//
//        // Check with bad name
//        roomToSave.setName(null);
//        response = resource().path(path).type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, roomToSave);
//        assertThat(response.getStatus()).isEqualTo(400);
//
//        // Check with existing name
//        roomToSave.setName("already exists");
//        when(injector.getInstance(RoomDao.class).findByName("already exists")).thenReturn(new Room());
//        response = resource().path(path).type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, roomToSave);
//        assertThat(response.getStatus()).isEqualTo(409);
//    }
}