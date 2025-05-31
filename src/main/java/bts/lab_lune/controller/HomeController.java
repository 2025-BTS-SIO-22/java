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

    @FXML
    private Button patientButton;

    @FXML
    private Button doctorButton;

    @FXML
    private Button resultButton;

    @FXML
    private void handlePatient(ActionEvent event) throws IOException {
        Parent root = SpringFXMLLoader.load("templates/patient.fxml");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Patient");
        stage.show();
    }

    @FXML
    private void handleDoctor(ActionEvent event) throws IOException {
        Parent root = SpringFXMLLoader.load("templates/doctor.fxml");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Doctor");
        stage.show();
    }

    @FXML
    private void handleResult(ActionEvent event) throws IOException {
        Parent root = SpringFXMLLoader.load("templates/resultat.fxml");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Result");
        stage.show();
    }

    @FXML
    public void initialize() {
        User user = UserSession.getUserSession();

        doctorButton.setVisible(false);
        patientButton.setVisible(false);

        if (user.isAdmin()) {
            doctorButton.setVisible(true);
            patientButton.setVisible(true);
        }
    }
}
