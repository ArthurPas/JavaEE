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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "home", urlPatterns = {"/home"})
@Singleton
public class HomeServlet extends HttpServlet {

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
                    freemarkerConfiguration.getTemplate("templates/home.ftl");
        } catch (IOException e) {
            System.out.println("Unable to process request,error during freemarker template retrieval.");  }

                    Map<String, Object> root = new HashMap<String, Object>();
            // navigation data and links
            root.put("title", "freemarker Servlet");
            root.put("now",
                    SimpleDateFormat.getDateTimeInstance().format(new Date()));
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