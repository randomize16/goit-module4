package ua.goit.console.commands;

import ua.goit.console.Command;
import ua.goit.dao.CategoryDao;
import ua.goit.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CategoryCommand implements Command {

    public static final Logger  LOGGER = LogManager.getLogger(CategoryCommand.class);

    CategoryDao categoryDao = new CategoryDao();

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
        LOGGER.info("--------Category menu----------");
    }

    private void update(String subParams) {

    }

    private void delete(String subParams) {
        String[] paramsArray = subParams.split(" ");
        Optional<Category> category = categoryDao
                .get(Long.parseLong(paramsArray[0]));
        if (category.isPresent()) {
            categoryDao.delete(category.get());
        } else {
            System.out.println("Category with id "  + paramsArray[0] + " not found");
        }
    }

    private void getAll() {
        List<Category> all = categoryDao.getAll();
        System.out.println("Returned "+ all.size() + " categories");
        for (Category category : all) {
            System.out.println(category);
        }
    }

    private void get(String subParams) {
        String[] paramsArray = subParams.split(" ");
        Optional<Category> category = categoryDao
                .get(Long.parseLong(paramsArray[0]));
        if (category.isPresent()) {
            System.out.println(category.get());
        } else {
            System.out.println("Category with id "  + paramsArray[0] + " not found");
        }
    }

    private void create(String subParams) {// category create NAME DESC [PARENT_ID]
        String[] paramsArray = subParams.split(" ");
        Category category = new Category();
        category.setName(paramsArray[0]);
        category.setDescription(paramsArray[1]);
        if (paramsArray.length > 2) {
            category.setParentId(Long.parseLong(paramsArray[2]));
        }
        categoryDao.create(category);
    }
}
