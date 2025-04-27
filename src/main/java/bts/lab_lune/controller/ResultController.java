package bts.lab_lune.controller;

import bts.lab_lune.model.Patient;
import bts.lab_lune.model.Result;
import bts.lab_lune.service.IPatientService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import bts.lab_lune.service.IResultService;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.*;

@Component
public class ResultController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(ResultController.class);

    @Autowired
    private IResultService resultService;

    @Autowired
    private IPatientService patientService;

    @FXML
    private ComboBox<Patient> patientField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TableView<Result> resultTable;

    private ObservableList<Result> resultList;

    @FXML
    private TableColumn<Result, String> description;

    @FXML
    private TableColumn<Result, String> namePatient;

    @FXML
    private TableColumn<Result, String> lastnamePatient;

    @FXML
    private Integer idResultIntern;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Configurar tabla para que solo se pueda seleccionar un elemento
        resultTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configureColumn();
        resultList();
        //Configurar columnas de la tabla para info de la base de datos
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


    public void addResult() {
        if (descriptionField.getText().isEmpty() || patientField.getItems().isEmpty()) {
            setMessage("Error", "You must provide all the data details.");
            descriptionField.requestFocus();
            return;
        }

        var result = new Result();
        setFormData(result);

        //agragamos utilizando service de spring
        resultService.saveResult(result);
        setMessage("Information", "Result added");
        clearForm();
        resultList();
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
            setMessage("Information", "You must select a record");
            return;
        }

        if (description.getText() == null || patientField.getItems().isEmpty()) {
            setMessage("Error", "You must provide data");
            descriptionField.requestFocus();
            return;
        }

        var result = resultTable.getSelectionModel().getSelectedItem();
        setFormData(result);
        resultService.saveResult(result);

        setMessage("Information", "Data modify");
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
