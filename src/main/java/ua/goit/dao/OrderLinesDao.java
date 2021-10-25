package ua.goit.dao;

import ua.goit.model.OrderLine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderLinesDao extends AbstractDao<OrderLine> {

    @Override
    String getTableName() {
        return "order_line";
    }

    @Override
    OrderLine mapToEntity(ResultSet rs) throws SQLException {
        OrderLine ol = new OrderLine();
        ol.setId(rs.getLong("id"));
        ol.setOrderId(rs.getLong("order_id"));
        ol.setItemCount(rs.getInt("count"));
        ol.setItemId(rs.getLong("item_id"));
        return null;
    }

    @Override
    public Optional<OrderLine> create(OrderLine entity) {
        return Optional.empty();
    }

    @Override
    public void update(OrderLine entity) {

    }
}
