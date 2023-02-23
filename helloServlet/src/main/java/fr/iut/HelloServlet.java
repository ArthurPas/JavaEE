package fr.iut;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println( "<HTML>" );
        out.println( "<HEAD>");
        out.println( "<TITLE>Page generee par une servlet</TITLE>" );
        out.println( "</HEAD>" );
        out.println( "<BODY>" );
        out.println( "<H1>Hello world !</H1>" );
        out.println( "</BODY>" );
        out.println( "</HTML>" );
        out.close();
    }
}
