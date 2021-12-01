package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.model.Item;
import ua.goit.model.OrderLine;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderLinesDao extends AbstractDao<OrderLine> {

    private static final Logger LOGGER = LogManager.getLogger(OrderLinesDao.class);

    public List<OrderLine> getOrderLines(Long orderId) {
        TypedQuery<OrderLine> query = em.createNamedQuery("OrderLine.lineByOrderId", OrderLine.class);
        query.setParameter("orderId", orderId);
        return query.getResultList();
    }

    @Override
    public List<OrderLine> getAll() {
        TypedQuery<OrderLine> query = em.createQuery("from OrderLine", OrderLine.class);
        return query.getResultList();
    }

    @Override
    public Optional<OrderLine> get(Long id) {
        OrderLine entity = em.find(OrderLine.class, id);
        return Optional.of(entity);
    }
}
