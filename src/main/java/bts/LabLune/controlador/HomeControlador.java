package bts.LabLune.controlador;


import bts.LabLune.config.SpringFXMLLoader;
import bts.LabLune.modelo.UserDTO;
import bts.LabLune.session.UserSession;
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

public class HomeControlador {
    @Autowired
    private SpringFXMLLoader springFXMLLoader;
    // Definir los botones de la vista
    @FXML
    private Button patientsButton;

    @FXML
    private Button doctorsButton;

    @FXML
    private Button resultsButton;

    // Método que se ejecuta cuando se hace clic en el botón "Patients"
    @FXML
    private void handlePatientsButton(ActionEvent event) {
        irAPatients(event);  // Llamamos a la función para navegar a la vista de pacientes
    }

    // Método que se ejecuta cuando se hace clic en el botón "Doctors"
    @FXML
    private void handleDoctorsButton(ActionEvent event) {

            iraDoctors(event);

    }

    // Método que se ejecuta cuando se hace clic en el botón "Results"
    @FXML
    private void handleResultsButton(ActionEvent event) {
        iraResults(event);
        // Aquí puedes agregar lógica para navegar a la vista de "Results"
    }


    public void irAPatients(ActionEvent event) {
        try {
            // Si es admin, cargamos la vista de pacientes
            Parent root = SpringFXMLLoader.load("templates/index.fxml");  // Cargar la vista de pacientes
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Pacientes");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // o muestra una alerta si prefieres
        }
    }

    public void iraDoctors(ActionEvent event) {
        try {
            // Usar la instancia inyectada de SpringFXMLLoader
            Parent root = SpringFXMLLoader.load("templates/doctor.fxml");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Doctores");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iraResults(ActionEvent event) {
        try {
            // Usar la instancia inyectada de SpringFXMLLoader
            Parent root = SpringFXMLLoader.load("templates/resultat.fxml");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Resultados");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize() {
        // Obtener el usuario actual de la sesión
        UserDTO user = UserSession.getUserSession();

        if (user != null) {
            if (!user.isAdmin()) {
                // Si NO es admin, ocultamos los botones de Pacientes y Doctores
                doctorsButton.setVisible(false);  // Ocultar botón de Doctores si no es admin
                patientsButton.setVisible(false);
                resultsButton.setVisible(false); // Ocultar botón de Pacientes si no es admin
            }
        }
    }
}

