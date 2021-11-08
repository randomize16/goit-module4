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

    private final OrderDao orderDao = new OrderDao();
    private final UserDao userDao = UserDao.getInstance();
    private final ItemDao itemDao = new ItemDao();
    private final CategoryDao categoryDao = new CategoryDao();
    private final OrderLinesDao orderLinesDao = new OrderLinesDao();

    public void save(Order order) {
        if (order.getId() == null) {
            orderDao.create(order);
            LOGGER.info("Order was created.");
        } else {
            orderDao.update(order);
        }
    }

    public List<Order> getAll() {
        return this.orderDao.getAll();
    }

    public Optional<OrderView> getOrderView(Long id) {
        return this.orderDao.get(id)
                .map(order -> {
                    OrderView view = new OrderView();
                    view.setNumber(order.getNumber());
                    view.setDescription(order.getDescription());
                    userDao.get(order.getId())
                                    .ifPresent(user -> view.setUserName(user.getName()));
                    view.setLines(orderLinesDao.getOrderLines(order.getId())
                            .stream()
                            .map(orderLine -> {
                                OrderLineView lineView = new OrderLineView();
                                lineView.setCount(orderLine.getItemCount());
                                this.itemDao.get(orderLine.getItemId())
                                        .ifPresent(item -> {
                                            lineView.setItem(item.getName());
                                            this.categoryDao.get(item.getCategoryId())
                                                    .ifPresent(category ->
                                                            lineView.setCategory(category.getName()));
                                        });
                                return lineView;
                            })
                            .collect(Collectors.toList())
                    );
                    return view;
                });
//        return this.orderDao.getOrderView(id);
    }
}
