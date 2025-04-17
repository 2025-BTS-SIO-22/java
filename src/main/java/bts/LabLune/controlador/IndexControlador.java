package bts.LabLune.controlador;

import bts.LabLune.config.SpringFXMLLoader;
import bts.LabLune.modelo.Patient;
import bts.LabLune.servicio.PacientServicio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

public class IndexControlador implements Initializable {
    //Metodo para inizializar correctamente

    //Con el loger estamos indicando que es enviara informacion por consola
    private static final Logger logger =
            LoggerFactory.getLogger(IndexControlador.class);


    //Integrar componente de servicio de spring
    //Con este atributo ya tenemos acceso al servciio de resultServicio
    //  Agregar atributos a mapear

    @Autowired
    private PacientServicio pacientServicio;

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
    private final ObservableList<Patient> patientList =
            FXCollections.observableArrayList();

    //Mapeo componentes, text area que agregamos

    //Para asociarlo en nuestra clase de controlador se agrega el fxml

    @FXML
    private TextField namePatientTextField;
    @FXML
    private TextField lastnamePatientTextField;


    @FXML
    private Integer idPacientInterno;

    @Autowired
    private SpringFXMLLoader springFXMLLoader;
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
    //Configurar tabla para que solo se pueda seleccionar un elemento
        patientTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumnas();
        listarPacientes();
    //Configurar columnas de la tabla para info de la base de datos

    }
    private void configurarColumnas(){
        idPatient.setCellValueFactory(new PropertyValueFactory<>("idPatient"));
        namePatient.setCellValueFactory(new PropertyValueFactory<>("namePatient"));
        lastnamePatient.setCellValueFactory(new PropertyValueFactory<>("lastnamePatient"));


    }
    private void listarPacientes(){
        logger.info("Ejecutando listado de pacientes");
        //Limpiar lista de pacientes
        patientList.clear();
        //Agregar todos los elementos que hay en bd
        patientList.addAll(pacientServicio.listarPacientes());
        //Relaconar la tabla con la lista
        patientTable.setItems(patientList);
    }
    public void agregarPaciente() {
        if (namePatientTextField.getText().isEmpty() ||
            lastnamePatientTextField.getText().isEmpty()
        ) {
            mostrarMensaje("Error", "You must provide all the data details.");
            namePatientTextField.requestFocus();
            return;
        } else {
            var pacient = new Patient();
            recolectarDatosFormulario(pacient);

            //agragamos utilizando servicio de spring
            pacientServicio.guardarPaciente(pacient);
            mostrarMensaje("Informacion", "Paciente agregado");
            limpiarFormulario();
            listarPacientes();

        }
    }
    public void cargarPacienteFormulario(){
        //Seleccionamos "SINGLE" para seleccionar un registro a la vez
        var pacient = patientTable.getSelectionModel().getSelectedItem();
        if(pacient != null) {
            //crear un atributo idpaciente en fxml idinterno
            idPacientInterno = pacient.getIdPatient();
            namePatientTextField.setText(pacient.getNamePatient());
            lastnamePatientTextField.setText(pacient.getLastnamePatient());

        }
    }


    private void recolectarDatosFormulario (Patient patient) {

    patient.setNamePatient(namePatientTextField.getText());
    patient.setLastnamePatient(lastnamePatientTextField.getText());

    }
    public void modificarPaciente(){

        if (idPacientInterno == null){
            mostrarMensaje("Information", "You must select a record");
         return;

        }
        if (namePatientTextField.getText() == null || namePatientTextField.getText().isEmpty())
               {
            mostrarMensaje("Error", "You must provide data" );
            namePatientTextField.requestFocus();
            return;
        }
        var patient = patientTable.getSelectionModel().getSelectedItem();
        recolectarDatosFormulario(patient);
        pacientServicio.guardarPaciente(patient);
        mostrarMensaje("Information", "Data modify");
        listarPacientes();
    }
    public void eliminarPacient(){
        var patient = patientTable.getSelectionModel().getSelectedItem();
        if (patient!=null){
            logger.info("Regirsto a eliminar{}", patient.toString());
            pacientServicio.eliminarPaciente(patient);
            mostrarMensaje("Information", "Patient delete" +patient.getIdPatient());
            limpiarFormulario();
            listarPacientes();

        } else {
            mostrarMensaje("Error", "Is not been select any data");
        }
    }
    public void limpiarFormulario(){
        idPacientInterno=null;
        namePatientTextField.clear();
        lastnamePatientTextField.clear();


    }
    private void mostrarMensaje(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        //con esto esperamos a que el usuario muestre el mensaje
        alerta.showAndWait();

    }
}
