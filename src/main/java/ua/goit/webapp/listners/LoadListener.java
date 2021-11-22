package ua.goit.webapp.listners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ua.goit.config.DbMigration;
import ua.goit.dao.UserDao;
import ua.goit.service.UserService;

@WebListener
public class LoadListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DbMigration.migrate();
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("userDao", UserDao.getInstance());
        servletContext.setAttribute("userService", UserService.getInstance());
    }
}
