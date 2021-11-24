package ua.goit.service;

import ua.goit.dao.CategoryDao;
import ua.goit.model.Category;

import java.util.List;
import java.util.Optional;

public class CategoryService {

    CategoryDao categoryDao = new CategoryDao();

    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    public Optional<Category> get(Long id) {
        return categoryDao.get(id);
    }

    public void create(Category category) {
        categoryDao.create(category);
    }
}
