package ua.goit.console.commands;

import ua.goit.console.Command;
import ua.goit.dao.UserDao;
import ua.goit.model.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UsersCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(UsersCommand.class);

    private static final UserDao userDao = UserDao.getInstance();

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

    private void getAll() {
        List<User> all = userDao.getAll();
        System.out.println("Returned "+ all.size() + " users");
        for (User user : all) {
            System.out.println(user);
        }
    }

    private void update(String params) { // user update ID NAME DESCRIPTION
        String[] paramsArray = params.split(" ");
        Optional<User> optionalUser = userDao
                .get(Long.parseLong(paramsArray[0]));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(paramsArray[1]);
            user.setDescription(paramsArray[2]);
            userDao.update(user);
        } else {
            System.out.println("User with id "  + paramsArray[0] + " not found");
        }
    }

    private void create(String params) { // users create NAME DESCRIPTION
        String[] paramsArray = params.split(" ");
        User user = new User();
        user.setName(paramsArray[0]);
        user.setDescription(paramsArray[1]);
        userDao.create(user);
    }

    private void get(String params) { // users get 1
        String[] paramsArray = params.split(" ");
        Optional<User> user = userDao
                .get(Long.parseLong(paramsArray[0]));
        if (user.isPresent()) {
            System.out.println(user.get());
        } else {
            System.out.println("User with id "  + paramsArray[0] + " not found");
        }
    }

    private void delete(String params) { // users get 1
        String[] paramsArray = params.split(" ");
        Optional<User> user = userDao
                .get(Long.parseLong(paramsArray[0]));
        if (user.isPresent()) {
            userDao.delete(user.get());
        } else {
            System.out.println("User with id "  + paramsArray[0] + " not found");
        }
    }

    @Override
    public void printActiveMenu() {
        LOGGER.info("-------Users menu-----------");
    }
}
