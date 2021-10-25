package ua.goit.console;

import ua.goit.console.commands.CategoryCommand;
import ua.goit.console.commands.ItemsCommand;
import ua.goit.console.commands.UsersCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    Map<String, Command> commandMap = new HashMap<>();

    public CommandHandler() {
        commandMap.put("users", new UsersCommand());
        commandMap.put("category", new CategoryCommand());
        commandMap.put("items", new ItemsCommand());
    }

    public void handleCommand(String params) {
        int firstSpace = params.indexOf(" ");
        if (firstSpace > -1) {
            Command command = commandMap
                    .get(params.substring(0, firstSpace));
            if (command != null) {
                command.handle(params.substring(firstSpace + 1));
            } else {
                System.out.println("Unknown command");
            }
        } else {
            System.out.println("Unknown command");
        }
    }
}
