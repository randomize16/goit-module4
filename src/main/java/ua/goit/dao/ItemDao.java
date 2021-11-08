package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ItemDao extends AbstractDao<Item>{

    private static final Logger LOGGER = LogManager.getLogger(Item.class);

    @Override
    String getTableName() {
        return "items";
    }

    @Override
    public Item mapToEntity(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setId(rs.getLong("id"));
        item.setCategoryId(rs.getLong("category_id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setTest(rs.getInt("test"));
        return item;
    }

    @Override
    public Optional<Item> create(Item entity) {
        String sql = "insert into items(name, description, category_id)" +
                " values (?, ?, ?)";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getDescription());
            ps.setLong(3, entity.getCategoryId());
        });
        LOGGER.debug("Record was created");
        return Optional.empty();
    }

    @Override
    public void update(Item entity) {
    }
}
