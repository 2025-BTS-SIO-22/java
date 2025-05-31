package bts.lab_lune;

import bts.lab_lune.presentation.initFx;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabLuneApplication {
    public static void main(String[] args) {
        // Solo lanzamos la aplicación JavaFX
        Application.launch(initFx.class, args);
    }
}
