package ua.goit.webapp.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter("/*")
public class LogsFilter implements Filter {

    public static final Logger LOGGER = LogManager.getLogger(LogsFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info(request.getLocalAddr());
        chain.doFilter(request, response);
    }
}
