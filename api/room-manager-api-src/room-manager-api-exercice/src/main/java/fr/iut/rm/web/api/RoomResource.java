package fr.iut.rm.web.api;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.sun.jersey.api.NotFoundException;
import fr.iut.rm.persistence.dao.AccessEventDao;
import fr.iut.rm.persistence.dao.RoomDao;
import fr.iut.rm.persistence.domain.Room;
import fr.iut.rm.web.api.model.RoomDetailsVO;
import fr.iut.rm.web.api.model.RoomVO;
import fr.iut.rm.web.api.model.SaveRoomVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Room WS Endpoint
 */
@Path("/rooms")
@RequestScoped
public class RoomResource {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RoomResource.class);

    /**
     * Injected Room DAO
     */
    @Inject
    private RoomDao roomDao;
    /**
     * Injected Access Event DAO
     */
    @Inject
    private AccessEventDao accessEventDao;

    /**
     * List all rooms
     *
     * @return all defined rooms
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public List<RoomVO> listRooms(@QueryParam(value="filtre") String filtre) {
        logger.debug("List all rooms");
        List<Room> rooms = roomDao.findAll();
        //Retrieve rooms from DB
        if(filtre != null){
            rooms = roomDao.searchByName(filtre);
        }
        // Convert rooms into visual object (select only necessary fields)
        final List<RoomVO> roomsVO = rooms.stream().map(room -> {
            final RoomVO roomVO = new RoomVO();
            roomVO.setId(room.getId());
            roomVO.setName(room.getName());
            roomVO.setDescription(room.getDescription());
            return roomVO;
        }).collect(Collectors.toList());

        //Return objects
        return roomsVO;
    }

    /**
     * Retrieve a room by its name.
     *
     * @param roomId room identifier
     * @return found room
     */
    @GET
    @Path("/{roomId}")
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public RoomDetailsVO getRoom(@PathParam(value = "roomId") long roomId) {
        logger.debug("Retrieve room with id {}", roomId);
        final Room room = roomDao.get(roomId);
        if (room == null) {
            throw new NotFoundException("Room with id " + roomId + " does not exist");
        }
        RoomDetailsVO roomVO = new RoomDetailsVO();
        roomVO.setId(room.getId());
        roomVO.setName(room.getName());
        if(room.getDescription() != null){
            roomVO.setDescription(room.getDescription());
        }
        return roomVO;
    }

    /**
     * Creates a Room
     *
     * @param roomVO room object to create
     * @return Response :
     * - 400 if given room contains bad values
     * - 409 if a room already exists with the given name
     * - 200 with the created room id in case of success
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public Response createRoom(SaveRoomVO roomVO) {
        logger.debug("Create a room");
        if(roomVO == null || roomVO.getTheName() == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Room room = roomDao.findByName(roomVO.getTheName());
        if(room != null){
            return Response.status(Response.Status.CONFLICT).build();
        }
        Room newRooom = new Room();
        if(roomVO.getTheDescription() != null){
            newRooom.setDescription(roomVO.getTheDescription());
        }
        newRooom.setName(roomVO.getTheName());
        roomDao.saveOrUpdate(newRooom);
        return Response.ok(newRooom.getId()).build();
    }
    // TODO creates other resources on access events
}