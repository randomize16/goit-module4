package ua.goit.server.handlers;

import com.sun.net.httpserver.HttpExchange;

public class IndexHandler extends AbstractHandler {

    @Override
    String getTemplateName() {
        return "index";
    }

    @Override
    protected void get(HttpExchange exchange) {
        handleResponse(exchange);
    }

}
