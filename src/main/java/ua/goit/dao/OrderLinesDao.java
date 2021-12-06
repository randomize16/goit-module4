package ua.goit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.model.OrderLine;

import javax.persistence.TypedQuery;
import java.util.List;

public class OrderLinesDao extends AbstractDao<OrderLine> {

    public List<OrderLine> getOrderLines(Long orderId) {
        TypedQuery<OrderLine> query = em.createNamedQuery("OrderLine.lineByOrderId", OrderLine.class);
        query.setParameter("orderId", orderId);
        return query.getResultList();
    }
}
