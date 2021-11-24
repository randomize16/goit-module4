package ua.goit.webapp.servlets.item;

import ua.goit.model.Item;
import ua.goit.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/items/*")
public class ItemsServlet extends HttpServlet {

    private ItemService service;

    @Override
    public void init() throws ServletException {
        this.service = (ItemService) getServletContext().getAttribute("itemService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> all = service.getAll();
        req.setAttribute("items", all);
        req.getRequestDispatcher("/jsp/items.jsp").forward(req, resp);
    }
}
