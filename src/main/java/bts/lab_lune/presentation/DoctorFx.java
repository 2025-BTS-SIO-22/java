package bts.lab_lune.presentation;

import bts.lab_lune.LabLuneApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class DoctorFx extends Application {
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        this.applicationContext = new SpringApplicationBuilder(LabLuneApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Mostrar primero la pantalla de login
        FXMLLoader loader = new FXMLLoader(LabLuneApplication.class.getResource("/templates/loginIndex.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        Scene loginScene = new Scene(loader.load());
        stage.setScene(loginScene);
        stage.show();
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
}