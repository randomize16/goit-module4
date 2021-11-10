package ua.goit.server.handlers.users;

import com.sun.net.httpserver.HttpExchange;
import ua.goit.model.User;
import ua.goit.server.handlers.AbstractHandler;
import ua.goit.service.UserService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDeleteHandler extends AbstractHandler {

    private static UserService service = UserService.getInstance();

    @Override
    protected String getTemplateName() {
        return "";
    }

    @Override
    protected void get(HttpExchange exchange) throws IOException {
        Map<String, String> urlParams = getUrlParams(exchange);
        String id = urlParams.get("id");
        if (id == null) {
            throw new RuntimeException("Need id for users");
        }
        User user = new User();
        user.setId(Long.parseLong(id));
        service.delete(user);
        exchange.getResponseHeaders().set("Location", "/users");
        exchange.sendResponseHeaders(301, 0L);
        exchange.close();
    }
}
