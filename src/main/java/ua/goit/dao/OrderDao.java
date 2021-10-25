package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderDao extends AbstractDao<Order> {

    private static final Logger LOGGER = LogManager.getLogger(OrderDao.class);

    @Override
    String getTableName() {
        return "orders";
    }

    @Override
    Order mapToEntity(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setNumber(rs.getInt("number"));
        order.setDescription(rs.getString("description"));
        return order;
    }

    @Override
    public Optional<Order> create(Order entity) {
        String sql = "insert into order(number, description, user_id)" +
                " values (?, ?, ?)";
        Optional<Long> createdId = DbHelper.executePreparedStatementAndGetId(sql, ps -> {
            ps.setLong(1, entity.getNumber());
            ps.setString(2, entity.getDescription());
            ps.setLong(3, entity.getUserId());
        });

        LOGGER.debug("Record was created");
        return createdId.flatMap(this::get);
    }

    @Override
    public void update(Order entity) {

    }
}
