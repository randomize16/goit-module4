package ua.goit.server.handlers.users;

import com.sun.net.httpserver.HttpExchange;
import ua.goit.model.User;
import ua.goit.server.dto.UserDto;
import ua.goit.server.handlers.AbstractHandler;
import ua.goit.service.UserService;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class UserCreateHandler extends AbstractHandler {

    private static UserService service = UserService.getInstance();

    @Override
    protected String getTemplateName() {
        return "user";
    }

    @Override
    protected void get(HttpExchange exchange) {
        handleResponse(exchange, Collections.singletonMap("action", "create"));
    }

    @Override
    protected void post(HttpExchange exchange) {
            getRequestBody(exchange, UserDto.class)
                    .ifPresent(userDto -> {
                        User user = new User();
                        user.setName(userDto.getName());
                        user.setDescription(userDto.getDescription());
                        service.create(user);
                        exchange.getResponseHeaders().set("Location", "/users");
                    });
    }
}
