package bts.LabLune.controlador;

import bts.LabLune.modelo.Pacient;
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
import java.time.LocalDate;
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
    private TableView<Pacient> pacientTable;

    //Al ser atributo privado agregamos que es componente de nuestra vista


    @FXML
    private TableColumn<Pacient, Integer> idPacient;

    @FXML
    private TableColumn<Pacient, String> namePacient;

    @FXML
    private TableColumn<Pacient, String> lastnamePacient;

    @FXML
    private TableColumn<Pacient,Integer> idResult;

    @FXML
    private TableColumn <Pacient, Integer> idDoctor;

    @FXML
    private TableColumn<Pacient, LocalDate> date;

    //Como se muestra lista se configura la tabla para poder recuperarla de la bd
    //Lista de tipo observable para que cualquier cambio se refleje en esta lista
    private final ObservableList<Pacient> pacientList =
            FXCollections.observableArrayList();

    //Mapeo componentes, text area que agregamos

    //Para asociarlo en nuestra clase de controlador se agrega el fxml

    @FXML
    private TextField namePatientTextField;
    @FXML
    private TextField lastnamePatientTextField;
    @FXML
    private TextField resultPatientTextField;
    @FXML
    private TextField doctorTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Integer idPacientInterno;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    //Configurar tabla para que solo se pueda seleccionar un elemento
        pacientTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumnas();
        listarPacientes();
    //Configurar columnas de la tabla para info de la base de datos

    }
    private void configurarColumnas(){
        idPacient.setCellValueFactory(new PropertyValueFactory<>("idPacient"));
        namePacient.setCellValueFactory(new PropertyValueFactory<>("namePacient"));
        lastnamePacient.setCellValueFactory(new PropertyValueFactory<>("lastnamePacient"));
        idResult.setCellValueFactory(new PropertyValueFactory<>("idResult"));
        idDoctor.setCellValueFactory(new PropertyValueFactory<>("idDoctor"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

    }
    private void listarPacientes(){
        logger.info("Ejecutando listado de pacientes");
        //Limpiar lista de pacientes
        pacientList.clear();
        //Agregar todos los elementos que hay en bd
        pacientList.addAll(pacientServicio.listarPacientes());
        //Relacionar la tabla con la lista
        pacientTable.setItems(pacientList);

    }
    public void agregarPaciente(){ if (namePatientTextField.getText().isEmpty() ||
            lastnamePatientTextField.getText().isEmpty() ||
            resultPatientTextField.getText().isEmpty() ||
            doctorTextField.getText().isEmpty() ||
            datePicker.getValue() == null)  {

        mostrarMensaje("Error", "You must provide all the data details.");
        namePatientTextField.requestFocus();
        return;
    }
        else {
            var pacient = new Pacient();
            recolectarDatosFormulario(pacient);
            pacient.setIdPacient(null);
            //agragamos utilizando servicio de spring
            pacientServicio.guardarPaciente(pacient);
            mostrarMensaje("Informacion", "Paciente agregado");
            limpiarFormulario();
            listarPacientes();


        }
    }
    public void cargarPacienteFormulario(){
        //Seleccionamos "SINGLE" para seleccionar un registro a la vez
        var pacient = pacientTable.getSelectionModel().getSelectedItem();
        if(pacient != null) {
            //crear un atributo idpaciente en fxml idinterno
            idPacientInterno = pacient.getIdPacient();
            namePatientTextField.setText(pacient.getNamePacient());
            lastnamePatientTextField.setText(pacient.getLastnamePacient());
            resultPatientTextField.setText(String.valueOf(pacient.getIdResult()));
            doctorTextField.setText(String.valueOf(pacient.getIdDoctor()));
            datePicker.setValue(pacient.getDate());
        }
    }


    private void recolectarDatosFormulario (Pacient pacient) {
        if (idPacientInterno!= null) {
            pacient.setIdPacient(idPacientInterno);
        }
    pacient.setNamePacient(namePatientTextField.getText());
    pacient.setLastnamePacient(lastnamePatientTextField.getText());
    pacient.setIdResult(Integer.parseInt(resultPatientTextField.getText()));
    pacient.setIdDoctor(Integer.parseInt(doctorTextField.getText()));
    pacient.setDate(datePicker.getValue());
    }
    public void modificarPaciente(){

        if (idPacientInterno == null){
            mostrarMensaje("Information", "You must select a record");
         return;

        }
        if (namePatientTextField.getText().isEmpty() || lastnamePatientTextField.getText().isEmpty() ||
                resultPatientTextField.getText().isEmpty() ||
                doctorTextField.getText().isEmpty() ||
                datePicker.getValue() == null) {
            mostrarMensaje("Error", "You must provide data" );
            namePatientTextField.requestFocus();
            return;
        }
        var pacient = new Pacient();
        recolectarDatosFormulario(pacient);
        pacientServicio.guardarPaciente(pacient);
        mostrarMensaje("Information", "Data modify");
    }
    public void eliminarPacient(){
        var pacient = pacientTable.getSelectionModel().getSelectedItem();
        if (pacient!=null){
            logger.info("Regirsto a eliminar" + pacient.toString());
            pacientServicio.eliminarPaciente(pacient);
            mostrarMensaje("Information", "Patient delete" +pacient.getIdPacient());
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
        resultPatientTextField.clear();
        doctorTextField.clear();
        datePicker.setValue(null); ;

    }
    private void mostrarMensaje(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        //con esto esperamos a que el usuario muestre el mensaje
        alerta.showAndWait();

    }
}
