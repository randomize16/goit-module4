package ua.goit.webapp.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        if ((session == null || session.getAttribute("user") == null)
                && !"/login".equalsIgnoreCase(req.getRequestURI())) {
            req.getRequestDispatcher("/login").forward(req, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
