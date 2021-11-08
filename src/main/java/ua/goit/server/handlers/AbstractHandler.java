package ua.goit.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.service.TemplateHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;


abstract public class AbstractHandler implements HttpHandler {

    public static final Logger LOGGER = LogManager.getLogger(AbstractHandler.class);

    private TemplateHandler templateHandler = TemplateHandler.getInstance();

    abstract String getTemplateName();
    protected void get(HttpExchange exchange) {};
    protected void post(HttpExchange exchange) {};

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod()) {
            case "GET": get(exchange);break;
            case "POST": post(exchange);break;
        }
    }

    protected void handleResponse(HttpExchange exchange) {
        handleResponse(exchange, Collections.emptyMap());
    }

    protected void handleResponse(HttpExchange exchange, Map<String, String> params) {
        String body = templateHandler.handleTemplate(getTemplateName(), params);
        try (OutputStream outputStream = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, body.length());
            outputStream.write(body.getBytes());
        } catch (IOException e) {
            LOGGER.error("Error while sending response.", e);
        }
    }

}
