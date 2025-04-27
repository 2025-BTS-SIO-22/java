package bts.lab_lune.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class SpringFXMLLoader {
    private static ApplicationContext context = null;

    @Autowired
    public SpringFXMLLoader(ApplicationContext context) {
        SpringFXMLLoader.context = context;
    }

    public static Parent load(String url) throws IOException {
        URL fxmlResource = Thread.currentThread().getContextClassLoader().getResource(url);

        if (fxmlResource == null) {
            throw new IOException("FXML no matching route for path: " + url);
        }

        FXMLLoader loader = new FXMLLoader(fxmlResource);
        loader.setControllerFactory(context::getBean);
        return loader.load();
    }
}