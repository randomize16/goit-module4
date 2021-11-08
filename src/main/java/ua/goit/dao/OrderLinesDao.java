package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.model.OrderLine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderLinesDao extends AbstractDao<OrderLine> {

    private static final Logger LOGGER = LogManager.getLogger(OrderLinesDao.class);

    @Override
    String getTableName() {
        return "order_line";
    }

    @Override
    public OrderLine mapToEntity(ResultSet rs) throws SQLException {
        OrderLine ol = new OrderLine();
        ol.setId(rs.getLong("id"));
        ol.setOrderId(rs.getLong("order_id"));
        ol.setItemCount(rs.getInt("item_count"));
        ol.setItemId(rs.getLong("item_id"));
        return ol;
    }

    @Override
    public Optional<OrderLine> create(OrderLine entity) {
        return Optional.empty();
    }

    @Override
    public void update(OrderLine entity) {
    }

    public List<OrderLine> getOrderLines(Long orderId) {
        String query = "select * from order_lines where order_id = ?";
        List<OrderLine> resultList = new ArrayList<>();
        try {
            ResultSet resultSet = DbHelper.getWithPreparedStatement(
                    query, ps -> {
                        ps.setLong(1, orderId);
                    });
            while (resultSet.next()) {
                LOGGER.debug("Record was selected");
                resultList.add(mapToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
