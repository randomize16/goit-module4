package ua.goit;

import ua.goit.dao.OrderDao;
import ua.goit.dao.OrderLinesDao;

public class OrderService {
    private final OrderDao orderDao = new OrderDao();
    private final OrderLinesDao orderLinesDao = new OrderLinesDao();


    public void createNewOrder() {

    }
}
