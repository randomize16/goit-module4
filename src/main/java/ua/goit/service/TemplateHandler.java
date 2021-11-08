package ua.goit.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class TemplateHandler {

    private static TemplateHandler instance;

    private static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    private TemplateHandler() {
    }

    public static TemplateHandler getInstance() {
        if (instance == null) {
            instance = new TemplateHandler();
        }
        return instance;
    }

    public String handleTemplate(String templateName, Map<String, String> params) {
        String template = getTemplate(templateName);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            template = template.replaceAll("{{" + entry.getKey() + "}}" , entry.getValue());
        }
        return template;
    }

    public String getTemplate(String templateName) {
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/templates/" + templateName + ".html");
            return new String(inputStream.readAllBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

}
