package ua.goit;

import ua.goit.console.CommandHandler;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Start application");
        CommandHandler commandHandler = new CommandHandler();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            commandHandler.handleCommand(scanner.nextLine());
        }
        System.out.println("END application");
    }

}
