package fr.iut;

import com.google.inject.servlet.ServletModule;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

public class Module extends ServletModule {
    /*Logger*/
    private static final Logger logger =
            LoggerFactory.getLogger(Module.class);

    @Override
    protected void configureServlets() {
        super.configureServlets();
        serve("/hello").with(HelloServlet.class);
        serve("/home").with(HomeServlet.class);
        serve("/protected").with(ProtectedServlet.class);
        serve("/qrcode").with(QRCodeServlet.class);
        serve("/room").with(RoomServlet.class);
        logger.debug("WebModule configureServlets ended.");
    }
}