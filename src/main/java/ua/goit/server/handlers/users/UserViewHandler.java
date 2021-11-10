package ua.goit.server.handlers.users;

import com.sun.net.httpserver.HttpExchange;
import ua.goit.model.User;
import ua.goit.server.dto.UserDto;
import ua.goit.server.handlers.AbstractHandler;
import ua.goit.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserViewHandler extends AbstractHandler {

    private static UserService service = UserService.getInstance();

    @Override
    protected String getTemplateName() {
        return "user";
    }

    @Override
    protected void get(HttpExchange exchange) {
        Map<String, String> urlParams = getUrlParams(exchange);
        String id = urlParams.get("id");
        if (id == null) {
            throw new RuntimeException("Need id for users");
        }
        Optional<User> userOptional = service.get(Long.parseLong(id));
        userOptional.map(user -> {
            Map<String, String> params = new HashMap<>();
            params.put("name", user.getName());
            params.put("description", user.getDescription());
            params.put("id", user.getId().toString());
            params.put("action", "view");
            return params;
        }).ifPresent(params -> {
            handleResponse(exchange, params);
        });
    }

    @Override
    protected void post(HttpExchange exchange) {
        getRequestBody(exchange, UserDto.class)
                .ifPresent(userDto -> {
                    User user = new User();
                    user.setId(Long.parseLong(userDto.getId()));
                    user.setName(userDto.getName());
                    user.setDescription(userDto.getDescription());
                    service.update(user);
                    exchange.getResponseHeaders().set("Location", "/users");
                });
    }
}
