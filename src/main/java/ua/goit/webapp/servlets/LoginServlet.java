package ua.goit.webapp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.goit.dao.UserDao;
import ua.goit.model.User;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDao dao;

    @Override
    public void init() throws ServletException {
        this.dao = (UserDao) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = dao.getByName(userName);
            if( user != null
                    && user.getPassword() != null
                    && user.getPassword().equals(password)) {
                HttpSession session = request.getSession(); //Creating a session
                session.setAttribute("user", userName); //setting session attribute
                session.setMaxInactiveInterval(10 * 60);
                request.setAttribute("userName", userName);
                response.sendRedirect("/jsp/main.jsp");
            } else {
                request.setAttribute("errMessage", "User with name " + userName + " invalid");

                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
