package ua.goit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.config.DbMigration;
import ua.goit.console.CommandHandler;

import java.util.Scanner;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.debug("Start application");
        DbMigration.migrate();

        runMainApp();
        LOGGER.info("END application");
    }

    public static void runMainApp() {
        CommandHandler commandHandler = new CommandHandler();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            commandHandler.handleCommand(scanner.nextLine());
        }
    }

}
