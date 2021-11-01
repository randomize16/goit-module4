package ua.goit.console.commands;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.goit.console.Command;
import ua.goit.model.Order;
import ua.goit.model.OrderLine;
import ua.goit.model.OrderView;
import ua.goit.service.OrderService;

public class OrdersCommand implements Command {

    public static final Logger LOGGER = LogManager.getLogger(OrdersCommand.class);

    private final OrderService orderService = new OrderService();

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
        this.orderService.save(this.order);
    }

    private void getAll(String s) {
        List<Order> all = this.orderService.getAll();
        if (all.isEmpty()) {
            LOGGER.info("Order list is empty");
        } else {
            all.forEach(System.out::println);
        }

    }

    private void get(String params) {
        String[] paramsArray = params.split(" ");
        this.orderService.getOrderView(Long.parseLong(paramsArray[0]))
                .ifPresent(orderView -> {
                    LOGGER.info("Order with id = " + paramsArray[0]);
                    LOGGER.info("Number: " + orderView.getNumber() +
                            " Description: " + orderView.getDescription() +
                            " User: " + orderView.getUserName());
                    orderView.getLines()
                            .forEach(orderLineView -> {
                                LOGGER.info("\t Category: " + orderLineView.getCategory() +
                                        " Item: " + orderLineView.getItem() +
                                        " Count: " + orderLineView.getCount());
                            });
                });
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
