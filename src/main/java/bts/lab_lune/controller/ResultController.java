package bts.lab_lune.controller;

import bts.lab_lune.model.Patient;
import bts.lab_lune.model.Result;
import bts.lab_lune.service.IPatientService;
import bts.lab_lune.service.PatientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import bts.lab_lune.service.IResultService;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.*;

import javax.swing.*;

@Component
public class ResultController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(ResultController.class);

    @Autowired
    private IResultService resultService;

    @Autowired
    private IPatientService iPatientService;

    @Autowired
    private PatientService patientService;

    @FXML
    private SplitMenuButton splitPacientes;


    @FXML
    private TextArea descriptionField;

    @FXML
    private TableView<Result> resultTable;
    private ObservableList<Result> resultList = FXCollections.observableArrayList();



    @FXML
    private TableColumn<Result, String> description;

    @FXML
    private TableColumn<Result, String> namePatient;

    @FXML
    private TableColumn<Result, String> lastnamePatient;

    @FXML
    private Integer idResultIntern;
    private Patient pacienteSeleccionado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Configurar tabla para que solo se pueda seleccionar un elemento
        resultTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configureColumn();
        resultList();
        cargarPacientesEnMenu();
        //Configurar columnas de la tabla para info de la base de datos
    }

    @FXML
    private void cargarResultadosFormulario(MouseEvent event) {
        System.out.println("Tabla o AnchorPane clickeado");
        Result result = resultTable.getSelectionModel().getSelectedItem();
        if (result != null) {
            idResultIntern = result.getIdResult();
            descriptionField.setText(result.getDescription());
            pacienteSeleccionado = result.getPatient(); // actualiza la variable interna

            // actualiza el texto del SplitMenuButton para reflejar el paciente
            splitPacientes.setText("Select patients"+
                    result.getPatient().getNamePatient() + " " + result.getPatient().getLastnamePatient()
            );
        }
    }

    private void cargarPacientesEnMenu() {
        splitPacientes.getItems().clear();
        List<Patient> patients = patientService.listPatient();
        for (Patient patient : patients) {
            MenuItem item = new MenuItem(patient.getNamePatient() + " " + patient.getLastnamePatient());
            item.setOnAction(e -> {
                pacienteSeleccionado = patient;  // Guardamos el paciente seleccionado
                splitPacientes.setText(patient.getNamePatient() + " " + patient.getLastnamePatient());
                System.out.println("Paciente seleccionado: " + pacienteSeleccionado.getNamePatient());
            });
            splitPacientes.getItems().add(item);
        }
    }

    public void addResult() {
       if (pacienteSeleccionado == null) {
            mostrarAlerta("Error", "Selecciona un paciente");
            return;
        }

        // Verificamos si el paciente existe en la BD
        Optional<Patient> pacienteEnBD = Optional.ofNullable(patientService.findPatientById(pacienteSeleccionado.getIdPatient()));
        if (!pacienteEnBD.isPresent()) {
            mostrarAlerta("Error", "El paciente seleccionado no existe en la base de datos.");
            return;
        }

        String descripcion = descriptionField.getText();
        if (descripcion == null || descripcion.isBlank()) {
            mostrarAlerta("Error", "Escribe una descripción");
            return;
        }

        Result nuevoResultado = new Result();
        // Usamos el paciente obtenido de la BD para evitar problemas
        nuevoResultado.setPatient(pacienteEnBD.get());
        nuevoResultado.setDescription(descripcion);

        resultService.saveResult(nuevoResultado);
        resultTable.getItems().add(nuevoResultado);

        descriptionField.clear();
    }



    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void configureColumn() {
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        namePatient.setCellValueFactory(new PropertyValueFactory<>("namePatient"));
        lastnamePatient.setCellValueFactory(new PropertyValueFactory<>("lastnamePatient"));
    }

    private void resultList() {
        logger.info("List of results");
        // Limpiar la lista antes de llenarla
        resultList.clear();
        // Agregar todos los resultes de la base de datos
        resultList.addAll(resultService.list());
        // Relacionar la tabla con la lista
        resultTable.setItems(resultList);
    }



    public void ResultForm() {
        //Seleccionamos "SINGLE" para seleccionar un registro a la vez
        var result = resultTable.getSelectionModel().getSelectedItem();

        if (result != null) {
            //crear un atributo idresult en fxml idinterno
            idResultIntern = result.getIdResult();
            descriptionField.setText(result.getDescription());
        }
    }

    private void setFormData(Result result) {
        result.setDescription(descriptionField.getText());
    }

    public void updateResult() {
        if (idResultIntern == null) {
            setMessage("Information", "Selecciona un registro");
            return;
        }

        if (descriptionField.getText().isEmpty() || pacienteSeleccionado == null) {
            setMessage("Error", "Debes proporcionar datos");
            descriptionField.requestFocus();
            return;
        }

        Result result = resultTable.getSelectionModel().getSelectedItem();
        result.setDescription(descriptionField.getText());
        result.setPatient(pacienteSeleccionado);

        resultService.saveResult(result);

        setMessage("Information", "Datos modificados");
        clearForm();
        resultList();
    }
    public void removeResult() {
        if (idResultIntern == null) {
            setMessage("Information", "You must select a record");
            return;
        }

        var result = resultTable.getSelectionModel().getSelectedItem();
        resultService.deleteResult(result);

        setMessage("Information", "Result delete " + result.getIdResult());
        clearForm();
        resultList();
    }

    public void clearForm() {
        idResultIntern = null;
        descriptionField.clear();
    }

    private void setMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        //con esto esperamos a que el usuario muestre el mensaje
        alert.showAndWait();
    }

}
