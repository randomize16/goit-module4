package ua.goit.server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.service.TemplateHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


abstract public class AbstractHandler implements HttpHandler {

    public static final Logger LOGGER = LogManager.getLogger(AbstractHandler.class);

    protected TemplateHandler templateHandler = TemplateHandler.getInstance();
    protected Gson jsonParser = new Gson();

    protected abstract String getTemplateName();
    protected void get(HttpExchange exchange) throws IOException {};
    protected void post(HttpExchange exchange) {};

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod()) {
            case "GET": get(exchange);break;
            case "POST": post(exchange);break;
        }
    }

    protected Map<String, String> getUrlParams(HttpExchange exchange) {
        URI requestURI = exchange.getRequestURI();
        String query = requestURI.getQuery();
        if (query == null) {
            return Collections.emptyMap();
        }
        return Arrays.stream(query.split("&"))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(k -> k[0], v -> v[1]));
    }

    protected <T> Optional<T> getRequestBody(HttpExchange exchange, Class<T> classType) {
        try (InputStream inputStream = exchange.getRequestBody();
             Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            String jsonStr = scanner.nextLine();
            return Optional.of(jsonParser.fromJson(jsonStr, classType));
        } catch (IOException e) {
            LOGGER.error("Request body getting error", e);
        }
        return Optional.empty();
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
