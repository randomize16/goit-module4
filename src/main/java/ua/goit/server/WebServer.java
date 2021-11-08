package ua.goit.server;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.server.handlers.GeneralGetHandler;
import ua.goit.server.handlers.IndexHandler;
import ua.goit.server.handlers.UsersHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.Executors;

public class WebServer {

    public static final Logger LOGGER = LogManager.getLogger(WebServer.class);

    private final Map<String, HttpHandler> handlers = Map.of(
            "/", new IndexHandler(),
            "/users", new UsersHandler(),
            "/groups", new GeneralGetHandler("groups"),
            "/items", new GeneralGetHandler("items")
    );

    public void start() {
        try {
            HttpServer server = HttpServer.create(
                    new InetSocketAddress("localhost", 80), 0);
            server.setExecutor(Executors.newFixedThreadPool(10));
            server.start();
            handlers.forEach(server::createContext);
        } catch (IOException e) {
            LOGGER.error("Problem with starting web server", e);
        }
    }


    public static void main(String[] args) {
        WebServer server = new WebServer();
        server.start();
    }
}
