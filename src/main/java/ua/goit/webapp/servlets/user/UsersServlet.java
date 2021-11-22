package ua.goit.webapp.servlets.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goit.model.User;
import ua.goit.service.HandleBodyUtil;
import ua.goit.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UserService service;

    @Override
    public void init() throws ServletException {
        this.service = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteId = req.getParameter("deleteId");
        if (deleteId != null) {
            User user = new User();
            user.setId(Long.parseLong(deleteId));
            service.delete(user);
            resp.sendRedirect("/users");
        } else {
            List<User> all = service.getAll();
            Object[] users = all.toArray();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/jsp/users.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> modelFromStream = HandleBodyUtil.getModelFromStream(req.getInputStream(), User.class);
        modelFromStream.ifPresent(user -> service.create(user));
        resp.sendRedirect("/users");
    }
}
