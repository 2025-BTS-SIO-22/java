package bts.LabLune.controlador;
import bts.LabLune.modelo.UserDTO;
import bts.LabLune.session.UserSession;
import bts.LabLune.servicio.AutentificacionServicio;
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
public class UserControlador {

    @Autowired
    private AutentificacionServicio autentificacionServicio;

    @FXML
    private TextField userNameLogin;

    @FXML
    private PasswordField passwordLogin;

    @FXML
    private Label mensajeLabel;

    // Método para autenticar al usuario
    @FXML
    public void login(ActionEvent event) {
        String username = userNameLogin.getText();
        String password = passwordLogin.getText();

        UserDTO userDTO = autentificacionServicio.autenticarUsuario(username, password);

        if (userDTO != null) {
            UserSession.startSession(userDTO);  // 👉 Guarda la sesión
            mensajeLabel.setText("Login exitoso!");

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/home.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                mensajeLabel.setText("Error al cargar la vista de inicio.");
            }

        } else {
            mensajeLabel.setText("Error al autenticar usuario.");
        }
    }}