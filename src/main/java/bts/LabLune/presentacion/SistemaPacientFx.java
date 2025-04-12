package bts.LabLune.presentacion;

import bts.LabLune.LabLuneApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import java.io.IOException;

public class SistemaPacientFx extends Application {
//importar fabrica de spring

    private ConfigurableApplicationContext applicationContext;

    /* public static void main(String[] args) {
    //  launch(args);
       }*/

    //    metodo init antes de metodo start
    @Override
    public void init() {
        //iniciar fabrica de Sprinc, metemos en el objeto la clase a utilizar, junto con el metodo run para que se ejecute
        this.applicationContext =
                new SpringApplicationBuilder(LabLuneApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Metodo para iniciar la aplicacion de javafx
//
//        Cargar archivo Index y que se muestre info
//        Cargamos en memoria la interface grafica agregamos la ruta
        FXMLLoader loader =
                new FXMLLoader(LabLuneApplication.class.getResource("/templates/index.fxml"));
//        objeto loader carga todos los objetos de spring, getbean proporciona todos los objetos de spring y se cargan en javafx
        loader.setControllerFactory(applicationContext::getBean);
//       //objeto de tipo escene
//
        Scene escena = new Scene(loader.load());
//        //objeto stage, establece la escena que hemos creado para mostrar
        stage.setScene(escena);
//        //objeto show para que se muestre la informacion
        stage.show();
    }
 /*   public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(
                    LabLuneApplication.class.getResource("/templates/index.fxml")
            );
            loader.setControllerFactory(applicationContext::getBean);

            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw e;
        }*/


    //metodo para detener aplicacion, para cerrar nuestra fabrica de sprin
    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

}
