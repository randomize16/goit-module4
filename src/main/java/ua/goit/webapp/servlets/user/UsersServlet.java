package ua.goit.webapp.servlets.user;

import ua.goit.model.User;
import ua.goit.service.HandleBodyUtil;
import ua.goit.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            Optional<User> user = service.get(Long.parseLong(deleteId));
            user.ifPresent(user1 -> service.delete(user1));
            resp.sendRedirect("/users");
        } else {
            List<User> all = service.getAll();
            req.setAttribute("users", all);
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
