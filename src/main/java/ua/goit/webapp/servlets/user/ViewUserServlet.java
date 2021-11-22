package ua.goit.webapp.servlets.user;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.model.User;
import ua.goit.service.HandleBodyUtil;
import ua.goit.service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

@WebServlet("/users/*")
public class ViewUserServlet extends HttpServlet {

    public static final Logger LOGGER = LogManager.getLogger(ViewUserServlet.class);
    private UserService service;
    protected Gson jsonParser = new Gson();

    @Override
    public void init() throws ServletException {
        this.service = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String id = requestURI.substring(7);
        if ("new".equalsIgnoreCase(id)) {
            req.setAttribute("user", new User());
            req.setAttribute("isNew", true);
            req.getRequestDispatcher("/jsp/user.jsp").forward(req, resp);
        }
        Optional<User> userOpt = service.get(Long.parseLong(id));
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            req.setAttribute("user", user);
            req.getRequestDispatcher("/jsp/user.jsp").forward(req, resp);
        }
        resp.sendRedirect("/users");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), User.class)
                .ifPresent(user -> {
                    service.update(user);
                });
        resp.sendRedirect("/users");
    }

}
