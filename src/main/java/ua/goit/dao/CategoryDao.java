package ua.goit.dao;

import ua.goit.model.Category;
import ua.goit.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CategoryDao extends AbstractDao<Category> {
    @Override
    String getTableName() {
        return "category";
    }

    @Override
    public Category mapToEntity(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("id"));
        category.setName(resultSet.getString("name"));
        category.setDescription(resultSet.getString("description"));
        category.setParentId(resultSet.getLong("parent_id"));
        return category;
    }

    @Override
    public Optional<Category> create(Category category) {
        String sql = "insert into category(name, description, parent_id) values (?, ?, ?)";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setLong(3, category.getParentId());
        });
        System.out.println("Record was created");
        return Optional.empty();
    }

    @Override
    public void update(Category entity) {
    }
}
