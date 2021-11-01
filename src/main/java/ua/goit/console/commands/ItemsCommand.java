package ua.goit.console.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.console.Command;
import ua.goit.dao.ItemDao;
import ua.goit.model.Item;
import ua.goit.model.User;

import java.util.List;
import java.util.function.Consumer;

public class ItemsCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ItemsCommand.class);

    private final ItemDao itemDao = new ItemDao();

    @Override
    public void handle(String params, Consumer<Command> setActive) {
        String[] paramsArray = params.split(" ");
        String subParams = String.join(" ", params.replace(paramsArray[0]+ " ", ""));
        switch (paramsArray[0]) {
            case "create": create(subParams);break;
            case "get": get(subParams);break;
            case "getAll": getAll();break;
            case "delete": delete(subParams);break;
            case "update": update(subParams);break;
        }
    }

    @Override
    public void printActiveMenu() {
        LOGGER.info("-------Item menu-----------");
    }

    private void update(String subParams) {
        LOGGER.warn("NOT IMPLEMENTED");
    }

    private void delete(String subParams) {
        LOGGER.warn("NOT IMPLEMENTED");

    }

    private void getAll() {
        List<Item> all = itemDao.getAll();
        System.out.println("Returned "+ all.size() + " items");
        for (Item item : all) {
            System.out.println(item);
        }

    }

    private void get(String subParams) {
        LOGGER.warn("NOT IMPLEMENTED");

    }

    private void create(String subParams) {
        String[] paramsArray = subParams.split(" ");
        Item item = new Item();
        item.setCategoryId(Long.parseLong(paramsArray[0]));
        item.setName(paramsArray[1]);
        item.setDescription(paramsArray[2]);
        itemDao.create(item);
    }
}
