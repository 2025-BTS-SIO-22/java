package bts.lab_lune.controller;

import bts.lab_lune.model.User;
import bts.lab_lune.session.UserSession;
import bts.lab_lune.service.AuthenticationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private AuthenticationService authenticationService;

    @FXML
    private TextField userNameLogin;

    @FXML
    private PasswordField passwordLogin;

    @FXML
    private Label messageLabel;

    // Método para autenticar al usuario
    @FXML
    public void login(ActionEvent event) throws IOException {
        String username = userNameLogin.getText();
        String password = passwordLogin.getText();
        User user = authenticationService.authenticateUser(username, password);

        if (user != null) {
            // 👉 Guarda la sesión
            UserSession.setUserSession(user);
            messageLabel.setText("Login success!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/home.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            messageLabel.setText("Error to load user.");
        }
    }
}
