package ua.goit.webapp.servlets;

import ua.goit.model.Category;
import ua.goit.model.Order;
import ua.goit.model.OrderView;
import ua.goit.model.User;
import ua.goit.service.CategoryService;
import ua.goit.service.HandleBodyUtil;
import ua.goit.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/orders/*")
public class OrdersServlet extends HttpServlet {

    private OrderService service;

    @Override
    public void init() throws ServletException {
        this.service = (OrderService) getServletContext().getAttribute("orderService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Optional<Category> modelFromStream =
//                HandleBodyUtil.getModelFromStream(req.getInputStream(), Category.class);
//        modelFromStream.ifPresent(category -> service.create(category));
//        resp.sendRedirect("/users");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String idStr = requestURI.substring(7);
        String deleteId = req.getParameter("deleteId");
        if (deleteId != null) {
            service.delete(Long.parseLong(deleteId));
            resp.sendRedirect("/orders");
        } else if("/new".equalsIgnoreCase(idStr)) {
            handleNew(req, resp);
        }
        else if (!"".equalsIgnoreCase(idStr)) {
            try {
                Long id = Long.parseLong(idStr.replace("/", ""));
                handleId(id, req, resp);
            } catch (RuntimeException e) {
                resp.sendRedirect("/orders");
            }
        } else {
            handleAll(req,resp);
        }
    }

    private void handleAll(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Order> all = service.getAll();
        req.setAttribute("orders", all);
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/jsp/orders.jsp").forward(req, resp);
    }

    private void handleId(Long id, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Optional<Order> order = service.get(id);
        if (order.isPresent()) {
            req.setAttribute("order", order.get());
            req.setCharacterEncoding("UTF-8");
            req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/orders");
        }
    }

    private void handleNew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("order", new Order());
        req.setAttribute("isNew", true);
        req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
    }
}
