package ua.goit.console.commands;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.goit.console.Command;

public class MainMenuCommand implements Command {

	private static final Logger LOGGER = LogManager.getLogger(MainMenuCommand.class);

	Map<String, Command> commands = Map.of(
			"users", new UsersCommand(),
			"category", new CategoryCommand(),
			"items", new ItemsCommand(),
			"orders", new OrdersCommand()
	);

	@Override
	public void handle(String params, Consumer<Command> setActive) {
		Optional<String> commandString = getCommandString(params);
		commandString.map(commands::get)
				.ifPresent(command -> {
					setActive.accept(command);
					command.handle(params.replace(commandString.get(),
							"").trim(), setActive);
				});
	}

	@Override
	public void printActiveMenu() {
		LOGGER.info("---------Main menu----------");
		LOGGER.info("Command list: " + this.commands.keySet());
	}
}
