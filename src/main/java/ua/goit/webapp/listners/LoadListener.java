package ua.goit.webapp.listners;


import ua.goit.config.DbMigration;
import ua.goit.config.PersistenceProvider;
import ua.goit.dao.UserDao;
import ua.goit.service.CategoryService;
import ua.goit.service.ItemService;
import ua.goit.service.OrderService;
import ua.goit.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class LoadListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PersistenceProvider.getEntityManager();
//        DbMigration.migrate();
//        ServletContext servletContext = sce.getServletContext();
//        servletContext.setAttribute("userDao", UserDao.getInstance());
//        servletContext.setAttribute("userService", UserService.getInstance());
//        servletContext.setAttribute("itemService", new ItemService());
//        servletContext.setAttribute("categoryService", new CategoryService());
//        servletContext.setAttribute("orderService", new OrderService());
    }
}
