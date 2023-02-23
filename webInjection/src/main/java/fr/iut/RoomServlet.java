package fr.iut;

import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "room", urlPatterns = {"/room"})
@Singleton
public class RoomServlet extends HttpServlet {


    protected List<Room> fillRoom(){
        List<Room> listOfRoom = new ArrayList<Room>();
        listOfRoom.add(new Room("Room1",0,10));
        listOfRoom.add(new Room("Room2",5,30));
        listOfRoom.add(new Room("Room3",10,10));
        return listOfRoom;
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        Template freemarkerTemplate = null;
        freemarker.template.Configuration freemarkerConfiguration =
                new freemarker.template.Configuration();
        freemarkerConfiguration.setClassForTemplateLoading(this.getClass(), "/");
        freemarkerConfiguration.setObjectWrapper(new DefaultObjectWrapper());
        try {
            freemarkerTemplate =
                    freemarkerConfiguration.getTemplate("templates/listRoom.ftl");
        } catch (IOException e) {
            System.out.println("Unable to process request,error during freemarker template retrieval.");  }
        List<Room> fakeRooms = fillRoom();
        Map<String, Object> root = new HashMap<String, Object>();
        // navigation data and links
        root.put("title", "freemarker Servlet");
        root.put("fakeRooms",fakeRooms);
        PrintWriter out = response.getWriter();
        assert freemarkerTemplate != null;
        try {
            freemarkerTemplate.process(root, out);
            out.close();}
        catch (TemplateException e) { e.printStackTrace(); }
        // set mime type
        response.setContentType("text/html");
    }
}

