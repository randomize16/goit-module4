package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.goit.config.DataSourceHolder;
import ua.goit.model.*;

import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderDao extends AbstractDao<Order> {

    private static final Logger LOGGER = LogManager.getLogger(OrderDao.class);

    private static OrderDao instance;
    private OrderDao() {
        super(Order.class);
    }

    public static OrderDao getInstance() {
        if (instance == null) {
            instance  = new OrderDao();
        }
        return instance;
    }

    public Optional<OrderView> getOrderView(Long id) {
//        try {
//            ResultSet rs = DbHelper.getWithPreparedStatement("select number, o.description, u.name from orders o\n" +
//                    "join users u on u.id = o.user_id where o.id = ?", ps -> {
//                ps.setLong(1, id);
//            });
//            if (rs.next()) {
//
//                OrderView orderView = new OrderView();
//                orderView.setNumber(rs.getInt(1));
//                orderView.setDescription(rs.getString(2));
//                orderView.setUserName(rs.getString(3));
//
//                String linesSql = "select c.name, i.name, ol.item_count from order_lines ol\n" +
//                        "join items i on ol.item_id = i.id\n" +
//                        "join category c on i.category_id = c.id\n" +
//                        "where ol.order_id = ?";
//                ResultSet lineRs = DbHelper.getWithPreparedStatement(linesSql, ps -> {
//                    ps.setLong(1, id);
//                });
//                while(lineRs.next()) {
//                    OrderLineView line = new OrderLineView();
//                    line.setCategory(lineRs.getString(1));
//                    line.setItem(lineRs.getString(2));
//                    line.setCount(lineRs.getInt(3));
//                    orderView.getLines().add(line);
//                }
//                lineRs.close();
//                rs.close();
//                return Optional.of(orderView);
//            }
//        } catch (SQLException e) {
//           LOGGER.error(e);
//        }
        return Optional.empty();
    }
}
