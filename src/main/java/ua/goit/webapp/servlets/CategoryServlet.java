package ua.goit.webapp.servlets;

import ua.goit.model.Category;
import ua.goit.model.Item;
import ua.goit.model.User;
import ua.goit.service.CategoryService;
import ua.goit.service.HandleBodyUtil;
import ua.goit.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@WebServlet("/categories/*") // categories/5 catories/fglkjgdlfkgj
public class CategoryServlet extends HttpServlet {

    private CategoryService service;

    @Override
    public void init() throws ServletException {
        this.service = (CategoryService) getServletContext().getAttribute("categoryService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Category> modelFromStream =
                HandleBodyUtil.getModelFromStream(req.getInputStream(), Category.class);
        modelFromStream.ifPresent(category -> service.create(category));
        resp.sendRedirect("/users");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String idStr = requestURI.substring(11);
        if("/new".equalsIgnoreCase(idStr)) {
            handleNew(req, resp);
        }
        else if (!"".equalsIgnoreCase(idStr)) {
            try {
                Long id = Long.parseLong(idStr.replace("/", ""));
                handleId(id, req, resp);
            } catch (RuntimeException e) {
                resp.sendRedirect("/categories");
            }
        } else {
            handleAll(req,resp);
        }
    }

    private void handleAll(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Category> all = service.getAll();
        req.setAttribute("categories", all);
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/jsp/categories.jsp").forward(req, resp);
    }

    private void handleId(Long id, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Optional<Category> category = service.get(id);
        if (category.isPresent()) {
            req.setAttribute("category", category.get());
            List<Category> all = service.getAll();
            req.setAttribute("categories", all);
            req.setCharacterEncoding("UTF-8");
            req.getRequestDispatcher("/jsp/category.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/categories");
        }
    }

    private void handleNew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("category", new Category());
        List<Category> all = service.getAll();
        req.setAttribute("categories", all);
        req.getRequestDispatcher("/jsp/category.jsp").forward(req, resp);
    }
}
