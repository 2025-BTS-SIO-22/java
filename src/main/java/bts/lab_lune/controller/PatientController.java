package bts.lab_lune.controller;

import bts.lab_lune.config.SpringFXMLLoader;
import bts.lab_lune.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import bts.lab_lune.service.IPatientService;
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
public class PatientController implements Initializable {
    //Metodo para inizializar correctamente
    //Con el loger estamos indicando que es enviara informacion por consola
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    //Integrar componente de service de spring
    //Con este atributo ya tenemos acceso al servciio de resultservice
    //  Agregar atributos a mapear
    @Autowired
    private IPatientService patientService;

    @FXML
    private TableView<Patient> patientTable;

    //Al ser atributo privado agregamos que es componente de nuestra vista
    @FXML
    private TableColumn<Patient, Integer> idPatient;

    @FXML
    private TableColumn<Patient, String> namePatient;

    @FXML
    private TableColumn<Patient, String> lastnamePatient;

    //Como se muestra lista se configura la tabla para poder recuperarla de la bd
    //Lista de tipo observable para que cualquier cambio se refleje en esta lista
    private final ObservableList<Patient> patientList = FXCollections.observableArrayList();

    //Mapeo componentes, text area que agregamos
    //Para asociarlo en nuestra clase de Controller se agrega el fxml
    @FXML
    private TextField namePatientTextField;

    @FXML
    private TextField lastnamePatientTextField;

    @FXML
    private Integer idPatientIntern;

    @Autowired
    private SpringFXMLLoader springFXMLLoader;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Configurar tabla para que solo se pueda seleccionar un elemento
        patientTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configureColumn();
        listPatient();
        //Configurar columnas de la tabla para info de la base de datos
    }

    private void configureColumn() {
        idPatient.setCellValueFactory(new PropertyValueFactory<>("idPatient"));
        namePatient.setCellValueFactory(new PropertyValueFactory<>("namePatient"));
        lastnamePatient.setCellValueFactory(new PropertyValueFactory<>("lastnamePatient"));
    }

    private void listPatient() {
        logger.info("List of patients");
        // Limpiar la lista antes de llenarla
        patientList.clear();
        // Agregar todos los patientes de la base de datos
        patientList.addAll(patientService.listPatient());
        // Relacionar la tabla con la lista
        patientTable.setItems(patientList);
    }

    public void addPatient() {
        if (namePatientTextField.getText().isEmpty() || lastnamePatientTextField.getText().isEmpty()) {
            setMessage("Error", "You must provide all the data details.");
            namePatientTextField.requestFocus();
            return;
        }

        var patient = new Patient();
        setFormData(patient);

        //agragamos utilizando service de spring
        patientService.savePatient(patient);
        setMessage("Information", "Patient added");
        clearForm();
        listPatient();
    }

    public void PatientForm() {
        //Seleccionamos "SINGLE" para seleccionar un registro a la vez
        var patient = patientTable.getSelectionModel().getSelectedItem();

        if (patient != null) {
            //crear un atributo idpatient en fxml idinterno
            idPatientIntern = patient.getIdPatient();
            namePatientTextField.setText(patient.getNamePatient());
            lastnamePatientTextField.setText(patient.getLastnamePatient());
        }
    }

    private void setFormData(Patient patient) {
        patient.setNamePatient(namePatientTextField.getText());
        patient.setLastnamePatient(lastnamePatientTextField.getText());
    }

    public void updatePatient() {
        if (idPatientIntern == null) {
            setMessage("Information", "You must select a record");
            return;
        }

        if (namePatientTextField.getText() == null || namePatientTextField.getText().isEmpty()) {
            setMessage("Error", "You must provide data");
            namePatientTextField.requestFocus();
            return;
        }

        var patient = patientTable.getSelectionModel().getSelectedItem();
        setFormData(patient);
        patientService.savePatient(patient);

        setMessage("Information", "Data modify");
        clearForm();
        listPatient();
    }

    public void removePatient() {
        if (idPatientIntern == null) {
            setMessage("Information", "You must select a record");
            return;
        }

        var patient = patientTable.getSelectionModel().getSelectedItem();
        patientService.deletePatient(patient);

        setMessage("Information", "Patient delete " + patient.getNamePatient());
        clearForm();
        listPatient();
    }

    public void clearForm() {
        idPatientIntern = null;
        namePatientTextField.clear();
        lastnamePatientTextField.clear();
    }

    private void setMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        //con esto esperamos a que el usuario muestre el mensaje
        alert.showAndWait();
    }
}
