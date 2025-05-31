package bts.lab_lune.config;

import javafx.fxml.FXMLLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;
import java.io.IOException;
import java.io.InputStream;

public class SpringLoader {


    @Component
    public class SpringFXMLLoader {

        private static ApplicationContext context;

        @Autowired
        public SpringFXMLLoader(ApplicationContext context) {
            SpringFXMLLoader.context = context;
        }

        public static FXMLLoader load(String fxmlPath) throws IOException {
            FXMLLoader loader = new FXMLLoader();

            InputStream fxmlStream = SpringFXMLLoader.class.getResourceAsStream("/templates/" + fxmlPath);
            if (fxmlStream == null) {
                throw new IOException("FXML no encontrado: " + fxmlPath);
            }

            loader.setControllerFactory(context::getBean);
            loader.load(fxmlStream);
            return loader;
        }
    }
}
