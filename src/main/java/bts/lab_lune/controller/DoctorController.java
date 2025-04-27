package bts.lab_lune.controller;

import bts.lab_lune.config.SpringFXMLLoader;
import bts.lab_lune.model.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import bts.lab_lune.service.IDoctorService;
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
public class DoctorController implements Initializable {
    //Metodo para inizializar correctamente
    //Con el loger estamos indicando que es enviara informacion por consola
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    //Integrar componente de service de spring
    //Con este atributo ya tenemos acceso al servciio de resultservice
    //  Agregar atributos a mapear
    @Autowired
    private IDoctorService doctorService;

    @FXML
    private TableView<Doctor> doctorTable;

    //Al ser atributo privado agregamos que es componente de nuestra vista
    @FXML
    private TableColumn<Doctor, Integer> idDoctor;

    @FXML
    private TableColumn<Doctor, String> nameDoctor;

    @FXML
    private TableColumn<Doctor, String> lastnameDoctor;

    //Como se muestra lista se configura la tabla para poder recuperarla de la bd
    //Lista de tipo observable para que cualquier cambio se refleje en esta lista
    private final ObservableList<Doctor> doctorList = FXCollections.observableArrayList();

    //Mapeo componentes, text area que agregamos
    //Para asociarlo en nuestra clase de Controller se agrega el fxml
    @FXML
    private TextField nameDoctorTextField;

    @FXML
    private TextField lastnameDoctorTextField;

    @FXML
    private Integer idDoctorIntern;

    @Autowired
    private SpringFXMLLoader springFXMLLoader;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Configurar tabla para que solo se pueda seleccionar un elemento
        doctorTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configureColumn();
        listDoctor();
        //Configurar columnas de la tabla para info de la base de datos
    }

    private void configureColumn() {
        idDoctor.setCellValueFactory(new PropertyValueFactory<>("idDoctor"));
        nameDoctor.setCellValueFactory(new PropertyValueFactory<>("nameDoctor"));
        lastnameDoctor.setCellValueFactory(new PropertyValueFactory<>("lastnameDoctor"));
    }

    private void listDoctor() {
        logger.info("List of doctors");
        // Limpiar la lista antes de llenarla
        doctorList.clear();
        // Agregar todos los doctores de la base de datos
        doctorList.addAll(doctorService.listDoctor());
        // Relacionar la tabla con la lista
        doctorTable.setItems(doctorList);
    }

    public void addDoctor() {
        if (nameDoctorTextField.getText().isEmpty() || lastnameDoctorTextField.getText().isEmpty()) {
            setMessage("Error", "You must provide all the data details.");
            nameDoctorTextField.requestFocus();
            return;
        }

        var doctor = new Doctor();
        setFormData(doctor);

        //agragamos utilizando service de spring
        doctorService.saveDoctor(doctor);
        setMessage("Information", "Doctor added");
        clearForm();
        listDoctor();
    }

    public void DoctorForm() {
        //Seleccionamos "SINGLE" para seleccionar un registro a la vez
        var doctor = doctorTable.getSelectionModel().getSelectedItem();

        if (doctor != null) {
            //crear un atributo iddoctor en fxml idinterno
            idDoctorIntern = doctor.getIdDoctor();
            nameDoctorTextField.setText(doctor.getNameDoctor());
            lastnameDoctorTextField.setText(doctor.getLastnameDoctor());
        }
    }

    private void setFormData(Doctor doctor) {
        doctor.setNameDoctor(nameDoctorTextField.getText());
        doctor.setLastnameDoctor(lastnameDoctorTextField.getText());
    }

    public void updateDoctor() {
        if (idDoctorIntern == null) {
            setMessage("Information", "You must select a record");
            return;
        }

        if (nameDoctorTextField.getText() == null || nameDoctorTextField.getText().isEmpty()) {
            setMessage("Error", "You must provide data");
            nameDoctorTextField.requestFocus();
            return;
        }

        var doctor = doctorTable.getSelectionModel().getSelectedItem();
        setFormData(doctor);
        doctorService.saveDoctor(doctor);

        setMessage("Information", "Data modify");
        clearForm();
        listDoctor();
    }

    public void removeDoctor() {
        if (idDoctorIntern == null) {
            setMessage("Information", "You must select a record");
            return;
        }

        var doctor = doctorTable.getSelectionModel().getSelectedItem();
        doctorService.removeDoctor(doctor);

        setMessage("Information", "Doctor delete " + doctor.getNameDoctor());
        clearForm();
        listDoctor();
    }

    public void clearForm() {
        idDoctorIntern = null;
        nameDoctorTextField.clear();
        lastnameDoctorTextField.clear();
    }

    private void setMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        //con esto esperamos a que el usuario muestre el mensaje
        alert.showAndWait();
    }
}
