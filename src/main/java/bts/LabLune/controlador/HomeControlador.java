package bts.LabLune.controlador;


import bts.LabLune.config.SpringFXMLLoader;
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
        System.out.println("Doctors button clicked");
        // Aquí puedes agregar lógica para navegar a la vista de "Doctors"
    }

    // Método que se ejecuta cuando se hace clic en el botón "Results"
    @FXML
    private void handleResultsButton(ActionEvent event) {
        System.out.println("Results button clicked");
        // Aquí puedes agregar lógica para navegar a la vista de "Results"
    }


    public void irAPatients(ActionEvent event) {


        try {
            Parent root = SpringFXMLLoader.load("templates/index.fxml");  // Cargar la vista de index
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ventana Index");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // o muestra una alerta si prefieres
        }
    }

    public void iraDoctors(ActionEvent event) {
        try {
            Parent root = SpringFXMLLoader.load("templates/doctorindex.fxml");  // Cargar la vista de index
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ventana Index");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // o muestra una alerta si prefieres
            //
        }
    }
}

