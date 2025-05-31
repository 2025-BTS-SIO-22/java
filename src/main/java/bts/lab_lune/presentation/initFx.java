package bts.lab_lune.presentation;

import bts.lab_lune.LabLuneApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class initFx extends Application {
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        // Crear el contexto Spring una única vez aquí
        this.applicationContext = new SpringApplicationBuilder(LabLuneApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(LabLuneApplication.class.getResource("/templates/loginIndex.fxml"));
        loader.setControllerFactory(applicationContext::getBean); // Inyectar beans con Spring
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        // Cerrar contexto Spring cuando se cierra JavaFX
        applicationContext.close();
        Platform.exit();
    }
}