package ua.goit.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.dao.*;
import ua.goit.model.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);

    private final OrderDao orderDao = OrderDao.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final ItemDao itemDao = ItemDao.getInstance();
    private final CategoryDao categoryDao = CategoryDao.getInstance();
    private final OrderLinesDao orderLinesDao = new OrderLinesDao();

    public void save(Order order) {
        if (order.getId() == null) {
            orderDao.create(order);
            LOGGER.info("Order was created.");
        } else {
            orderDao.update(order);
        }
    }

    public Optional<Order> get(Long id) {
        return this.orderDao.get(id);
    }

    public void delete(Long id) {
        this.orderDao.get(id)
                .ifPresent(this.orderDao::delete);
    }

    public List<Order> getAll() {
        return this.orderDao.getAll();
    }

//    public Optional<OrderView> getOrderView(Long id) {
//        return this.orderDao.get(id)
//                .map(order -> {
//                    OrderView view = new OrderView();
//                    view.setNumber(order.getNumber());
//                    view.setDescription(order.getDescription());
//                    view.setUserName(order.getUser().getName());
//                    view.setLines(order.getLines().stream()
//                            .map(orderLine -> {
//                                OrderLineView orderLineView = new OrderLineView();
//                                orderLineView.setItem(orderLine.getItem().getName());
//
//                            })
//                            .collect(Collectors.toList()));
//                    return view;
//                });
//    }
}
