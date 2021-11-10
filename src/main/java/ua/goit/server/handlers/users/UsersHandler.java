package ua.goit.server.handlers.users;

import com.sun.net.httpserver.HttpExchange;
import ua.goit.model.User;
import ua.goit.server.handlers.AbstractHandler;
import ua.goit.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UsersHandler extends AbstractHandler {

    private static UserService service = UserService.getInstance();

    @Override
    protected String getTemplateName() {
        return "users";
    }

    @Override
    protected void get(HttpExchange exchange) {
        List<User> users = service.getAll();
        String rowsTemplates = users.stream()
                .map(user -> {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", user.getName());
                    params.put("description", user.getDescription());
                    params.put("id", user.getId().toString());
                    return templateHandler.handleTemplate("table-row", params);
                })
                .collect(Collectors.joining());
        handleResponse(exchange, Map.of("tableRows", rowsTemplates));
    }
}
