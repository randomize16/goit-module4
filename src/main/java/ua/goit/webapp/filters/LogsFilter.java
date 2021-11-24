package ua.goit.webapp.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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
