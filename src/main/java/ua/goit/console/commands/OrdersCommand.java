package ua.goit.console.commands;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.goit.console.Command;
import ua.goit.dao.OrderDao;
import ua.goit.model.Order;
import ua.goit.model.OrderLine;

public class OrdersCommand implements Command {

	public static final Logger LOGGER = LogManager.getLogger(OrdersCommand.class);

	private final OrderDao orderDao = new OrderDao();

	Map<String, Consumer<String>> orderCommands = Map.of(
			"line", this::addLine,
			"clearLines", this::clearLines,
			"new", this::newOrder,
			"open", this::openOrder,
			"get", this::get,
			"getAll", this::getAll,
			"save", this::save

	);

	private void save(String s) {
		if (this.order.getId() == null) {
			orderDao.create(this.order);
			LOGGER.info("Order was created.");
		} else {
			orderDao.update(order);
		}
	}

	private void getAll(String s) {
		orderDao.getAll().forEach(System.out::println);
	}

	private void get(String params) {
		String[] paramsArray = params.split(" ");
		orderDao.printOrder(Long.parseLong(paramsArray[0]));
	}

	private void openOrder(String s) {

	}

	private Order order;

	private void newOrder(String params) {
		this.order = new Order();
		String[] paramsArray = params.split(" ");
		this.order.setNumber(Integer.parseInt(paramsArray[0]));
		this.order.setUserId(Long.parseLong(paramsArray[1]));
		this.order.setDescription(paramsArray[2]);
	}

	private void clearLines(String s) {
		if (this.order != null) {
			this.order.getLines().clear();
		}
	}


	@Override
	public void handle(String params, Consumer<Command> setActive) {
		Optional<String> commandString = getCommandString(params);
		commandString
				.map(orderCommands::get)
				.ifPresent(commandConsumer -> {
					commandConsumer.accept(
							params.replace(commandString.get(),
							"").trim());
				});
	}

	@Override
	public void printActiveMenu() {
		LOGGER.info("------ Order menu------");
		LOGGER.info("Commands: " + orderCommands.keySet());
	}

	private void addLine(String params) {
		String[] paramsArray = params.split(" ");
		OrderLine ol = new OrderLine();
		ol.setItemId(Long.parseLong(paramsArray[0]));
		ol.setItemCount(Integer.parseInt(paramsArray[1]));
		this.order.getLines().add(ol);
	}
}
