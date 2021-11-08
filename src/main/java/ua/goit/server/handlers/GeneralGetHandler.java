package ua.goit.server.handlers;

import com.sun.net.httpserver.HttpExchange;

public class GeneralGetHandler extends AbstractHandler {

    private final String templateName;

    public GeneralGetHandler(String templateName) {
        this.templateName = templateName;
    }

    @Override
    String getTemplateName() {
        return this.templateName;
    }

    @Override
    protected void get(HttpExchange exchange) {
        handleResponse(exchange);
    }
}
