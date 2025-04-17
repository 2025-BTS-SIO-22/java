package bts.LabLune.presentacion;

import bts.LabLune.LabLuneApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class SistemaPacientFx extends Application {

    private ConfigurableApplicationContext applicationContext;
    private Stage primaryStage;

    @Override
    public void init() {
        this.applicationContext =
                new SpringApplicationBuilder(LabLuneApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        mostrarLogin(); // Mostrar primero la pantalla de login
    }

    public void mostrarLogin() throws Exception {
        FXMLLoader loader = new FXMLLoader(LabLuneApplication.class.getResource("/templates/loginIndex.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        Scene loginScene = new Scene(loader.load());
        primaryStage.setScene(loginScene);
        primaryStage.show();

    }

    public void mostrarPrincipal() throws Exception {
        FXMLLoader loader = new FXMLLoader(LabLuneApplication.class.getResource("/templates/index.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        Scene principalScene = new Scene(loader.load());
        primaryStage.setScene(principalScene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
}