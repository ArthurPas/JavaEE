package fr.iut.rm;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import fr.iut.rm.control.ControlRoom;
import fr.iut.rm.persistence.domain.EntryLeave;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Entry point for command-line program. It's mainly a dumb main static method.
 */
public final class App {
    /**
     * quit constant
     */
    private static final String QUIT = "q";
    /**
     * help constant
     */
    private static final String HELP = "h";
    /**
     * creat constant
     */
    private static final String CREATE = "c";
    /**
     * delete constant
     */
    private static final String DELETE = "d";
    /**
     * list constant
     */
    private static final String LIST = "l";
    /**
     * add access entry constant
     */
    private static final String ADDENTRY = "a";
    /**
     * add access leave constant
     */
    private static final String ADDLEAVE= "b";
    /**
     * list all access for a room
     */
    private static final String LISTACCESSBYROOM= "z";
    /**
     * list all access for a person
     */
    private static final String LISTACCESSBYPERSON= "y";

    /**
     * standard logger
     */
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    /**
     * the available options for CLI management
     */
    private final Options options = new Options();

    @Inject
     ControlRoom cr;

    /**
     * Invoked at module initialization time
     */
    public App() {
        // build options command line options
        options.addOption(OptionBuilder.withDescription("List all rooms").create(LIST));
        options.addOption(OptionBuilder.withArgName("name").hasArgs(2).withDescription("Create new room").create(CREATE));
        options.addOption(OptionBuilder.withArgName("name").hasArgs(2).withDescription("Add an entry").create(ADDENTRY));
        options.addOption(OptionBuilder.withArgName("name").hasArgs(2).withDescription("Add a leave").create(ADDLEAVE));
        options.addOption(OptionBuilder.withArgName("name").hasArg().withDescription("list all the access of a room").create(LISTACCESSBYROOM));
        options.addOption(OptionBuilder.withArgName("name").hasArg().withDescription("list all the access of a person").create(LISTACCESSBYPERSON));
        options.addOption(OptionBuilder.withDescription("Display help message").create(HELP));
        options.addOption(OptionBuilder.withDescription("Quit").create(QUIT));
        options.addOption(OptionBuilder.withArgName("name").hasArg().withDescription("delete a room").create(DELETE));
    }



    /**
     * Displays help message
     */
    private void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("room-manager.jar", options);
    }

    public void run() {
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        Scanner sc = new Scanner(System.in);
        do {
            String str = sc.nextLine();
            try {
                cmd = parser.parse(options, str.split(" "));
                if (cmd.hasOption(HELP)) {
                    showHelp();
                } else if (cmd.hasOption(LIST)) {
                    cr.showRooms();
                } else if (cmd.hasOption(CREATE)) {
                    String[] creationOptions = cmd.getOptionValues(CREATE);
                    if(creationOptions.length == 0 ){
                        logger.warn("specify at least the name of the room ");
                    }
                    String name = creationOptions[0];
                    String desc = null;
                    if(creationOptions.length > 1 ){
                        desc = creationOptions[1];
                    }
                    cr.createRoom(name,desc);
                } else if (cmd.hasOption(DELETE)) {
                    String name = cmd.getOptionValue(DELETE);
                    if (name != null && !name.isEmpty()) {
                        cr.deleteRoom(name);
                    }
                }else if (cmd.hasOption(ADDENTRY)) {
                    String[] addOptions = cmd.getOptionValues(ADDENTRY);
                    if(addOptions.length <2 ){
                        System.out.println("specify the name and the room ");
                    }
                    else {
                        String name = addOptions[0];
                        String room = addOptions[1];
                        cr.addAccess(room,name,EntryLeave.ENTRY);
                    }
                }
                else if (cmd.hasOption(ADDLEAVE)) {
                    String[] addOptions = cmd.getOptionValues(ADDLEAVE);
                    if(addOptions.length <2 ){
                        System.out.println("specify the name and the room ");
                    }
                    else {
                        String name = addOptions[0];
                        String room = addOptions[1];
                        cr.addAccess(room,name,EntryLeave.LEAVE);
                    }
                } else if (cmd.hasOption(LISTACCESSBYROOM)) {
                    String listOption = cmd.getOptionValue(LISTACCESSBYROOM);
                    if (listOption.isEmpty()) {
                        System.out.println("specify the room ");
                    } else {
                        cr.showAccessByRoom(listOption);
                    }
                }else if (cmd.hasOption(LISTACCESSBYPERSON)) {
                    String listOption = cmd.getOptionValue(LISTACCESSBYPERSON);
                    if(listOption.isEmpty()){
                        System.out.println("specify the person ");
                    }
                    else {
                        cr.showAccessByPerson(listOption);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
                showHelp();
            }
        } while (!cmd.hasOption(QUIT));
    }

    /**
     * Main program entry point
     *
     * @param args main program args
     */
    public static void main(final String[] args) {
        logger.info("Room-Manager version {} started", Configuration.getVersion());
        logger.debug("create guice injector");
        Injector injector = Guice.createInjector(new JpaPersistModule("room-manager"), new MainModule());
        logger.info("starting persistency service");
        PersistService ps = injector.getInstance(PersistService.class);
        ps.start();

        App app =  injector.getInstance(App.class);

        app.showHelp();
        app.run();

        logger.info("Program finished");
        System.exit(0);
    }


}
