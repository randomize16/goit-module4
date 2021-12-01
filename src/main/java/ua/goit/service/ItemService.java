package ua.goit.service;

import ua.goit.dao.ItemDao;
import ua.goit.model.Item;

import java.util.List;

public class ItemService {

    private ItemDao dao = ItemDao.getInstance();

    public List<Item> getAll() {
        return dao.getAll();
    }

}
