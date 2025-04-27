package bts.lab_lune.controller;

import bts.lab_lune.config.SpringFXMLLoader;
import bts.lab_lune.model.User;
import bts.lab_lune.session.UserSession;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HomeController {
    @Autowired
    private SpringFXMLLoader springFXMLLoader;

    // Definir los botones de la vista
    @FXML
    private Button patientButton;

    @FXML
    private Button doctorButton;

    @FXML
    private Button resultButton;

    // Método que se ejecuta cuando se hace clic en el botón "Patients"
    @FXML
    private void handlePatient(ActionEvent event) throws IOException {
        // Llamamos a la función para navegar a la vista de patientes
        // Si es admin, cargamos la vista de patientes
        // Cargar la vista de patientes
        Parent root = SpringFXMLLoader.load("templates/patient.fxml");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Patient");
        stage.show();
    }

    // Método que se ejecuta cuando se hace clic en el botón "Doctors"
    @FXML
    private void handleDoctor(ActionEvent event) throws IOException {
        // Usar la instancia inyectada de SpringFXMLLoader
        Parent root = SpringFXMLLoader.load("templates/doctor.fxml");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Doctor");
        stage.show();
    }

    // Método que se ejecuta cuando se hace clic en el botón "Results"
    @FXML
    private void handleResult(ActionEvent event) throws IOException {
        // Usar la instancia inyectada de SpringFXMLLoader
        Parent root = SpringFXMLLoader.load("templates/result.fxml");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Result");
        stage.show();
    }

    @FXML
    public void initialize() {
        // Obtener el usuario actual de la sesión
        User user = UserSession.getUserSession();

        if (user != null && !user.isAdmin()) {
            // Si NO es admin, ocultamos los botones de Pacientes y Doctores
            // Ocultar botón de Doctores si no es admin
            doctorButton.setVisible(false);
            // Ocultar botón de Pacientes si no es admin
            patientButton.setVisible(false);
        }
    }
}
