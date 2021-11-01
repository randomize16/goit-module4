package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.goit.config.DataSourceHolder;
import ua.goit.model.Order;
import ua.goit.model.OrderLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
        String sql = "insert into orders(id, number, description, user_id)" +
                " values (?, ?, ?, ?)";
        Long orderId = DbHelper.generateId("orders_id_seq");
        try (Connection connection = DataSourceHolder.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, orderId);
                ps.setLong(2, entity.getNumber());
                ps.setString(3, entity.getDescription());
                ps.setLong(4, entity.getUserId());
                ps.execute();
            }
            createLines(entity.getLines(), orderId, connection);

            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("Error while creating order", e);
        }

        LOGGER.debug("Record was created");
        return Optional.empty();
    }

    private void createLines(List<OrderLine> lines,Long orderId, Connection connection) {
        String sql = "insert into order_lines(order_id, item_id, item_count)" +
                " values (?, ?, ?)";
        lines.forEach(orderLine -> {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, orderId);
                ps.setLong(2, orderLine.getItemId());
                ps.setInt(3, orderLine.getItemCount());
                ps.execute();
            } catch (SQLException e) {
                LOGGER.error("Error while creating order lines", e);
            }
        });
    }

    @Override
    public void update(Order entity) {

    }

    public void printOrder(Long id) {
        try {
            ResultSet rs = DbHelper.getWithPreparedStatement("select number, o.description, u.name from orders o\n" +
                    "join users u on u.id = o.user_id where o.id = ?", ps -> {
                ps.setLong(1, id);
            });
            if (rs.next()) {
                LOGGER.info("Order with id = " + id);
                LOGGER.info("Number: " + rs.getString(1) +
                        " Description: " + rs.getString(2) +
                        " User: " + rs.getString(3));

                String linesSql = "select c.name, i.name, ol.item_count from order_lines ol\n" +
                        "join items i on ol.item_id = i.id\n" +
                        "join category c on i.category_id = c.id\n" +
                        "where ol.order_id = ?";
                ResultSet lineRs = DbHelper.getWithPreparedStatement(linesSql, ps -> {
                    ps.setLong(1, id);
                });
                while(lineRs.next()) {
                    LOGGER.info("\t Category: "+ lineRs.getString(1)+
                            " Item: "+ lineRs.getString(2) +
                            " Count: " + lineRs.getString(3));
                }
                lineRs.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
