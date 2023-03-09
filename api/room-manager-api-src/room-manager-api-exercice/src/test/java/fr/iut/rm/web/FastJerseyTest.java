package fr.iut.rm.web;

import com.sun.jersey.test.framework.JerseyTest;
import org.junit.AfterClass;

/**
 * THis implementation starts and stop container only once
 */
public class FastJerseyTest extends JerseyTest {

    // Static reference used to stop container
    private static FastJerseyTest self = null;

    @AfterClass
    public static void stop() throws Exception {
        self.stopContainer();
        self = null;
    }

    @Override
    public void setUp() throws Exception {
        if (self == null) {
            self = this;
            super.setUp();
        }
    }

    @Override
    public void tearDown() throws Exception {
    }

    private void stopContainer() throws Exception {
        super.tearDown();
    }
}
