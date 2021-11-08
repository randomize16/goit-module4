package ua.goit.server.handlers;

import com.sun.net.httpserver.HttpExchange;

import java.util.Map;

public class UsersHandler extends AbstractHandler {

    @Override
    String getTemplateName() {
        return "users";
    }

    @Override
    protected void get(HttpExchange exchange) {
        handleResponse(exchange, Map.of(
                "userName", "Valera",
                "userDetail", "Kurochkin"
        ));
    }
}
