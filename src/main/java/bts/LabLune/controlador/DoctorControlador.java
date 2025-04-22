package bts.LabLune.controlador;
import bts.LabLune.config.SpringFXMLLoader;
import bts.LabLune.modelo.Doctor;
import bts.LabLune.servicio.DoctorServicio;
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

public class DoctorControlador implements Initializable {
    //Metodo para inizializar correctamente

    //Con el loger estamos indicando que es enviara informacion por consola
    private static final Logger logger =
            LoggerFactory.getLogger(IndexControlador.class);


    //Integrar componente de servicio de spring
    //Con este atributo ya tenemos acceso al servciio de resultServicio
    //  Agregar atributos a mapear

    @Autowired
    private DoctorServicio doctorServicio;

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
    private final ObservableList<Doctor> doctorList =
            FXCollections.observableArrayList();

    //Mapeo componentes, text area que agregamos

    //Para asociarlo en nuestra clase de controlador se agrega el fxml

    @FXML
    private TextField nameDoctorTextField;
    @FXML
    private TextField lastnameDoctorTextField;


    @FXML
    private Integer idDoctorInterno;

    @Autowired
    private SpringFXMLLoader springFXMLLoader;
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Configurar tabla para que solo se pueda seleccionar un elemento
        doctorTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumnas();
        listarDoctores();
        //Configurar columnas de la tabla para info de la base de datos

    }
    private void configurarColumnas(){
        idDoctor.setCellValueFactory(new PropertyValueFactory<>("idDoctor"));
        nameDoctor.setCellValueFactory(new PropertyValueFactory<>("nameDoctor"));
        lastnameDoctor.setCellValueFactory(new PropertyValueFactory<>("lastnameDoctor"));


    }
    private void listarDoctores(){
        logger.info("Ejecutando listado de doctores");
        //Limpiar lista de doctores
        doctorList.clear();
        //Agregar todos los elementos que hay en bd
        doctorList.addAll(doctorServicio.listarDoctores());
        //Relaconar la tabla con la lista
        doctorTable.setItems(doctorList);
    }
    public void agregarDoctor() {
        if (nameDoctorTextField.getText().isEmpty() ||
                lastnameDoctorTextField.getText().isEmpty()
        ) {
            mostrarMensaje("Error", "You must provide all the data details.");
            nameDoctorTextField.requestFocus();
            return;
        } else {
            var doctor = new Doctor();
            recolectarDatosFormulario(doctor);

            //agragamos utilizando servicio de spring
            doctorServicio.guardarDoctor(doctor);
            mostrarMensaje("Informacion", "Doctor agregado");
            limpiarFormulario();
            listarDoctores();

        }
    }
    public void cargarDoctoresFormulario(){
        //Seleccionamos "SINGLE" para seleccionar un registro a la vez
        var doctor = doctorTable.getSelectionModel().getSelectedItem();
        if(doctor != null) {
            //crear un atributo iddoctor en fxml idinterno
            idDoctorInterno = doctor.getIdDoctor();
            nameDoctorTextField.setText(doctor.getNameDoctor());
            lastnameDoctorTextField.setText(doctor.getLastnameDoctor());

        }
    }


    private void recolectarDatosFormulario (Doctor doctor) {

        doctor.setNameDoctor(nameDoctorTextField.getText());
        doctor.setLastnameDoctor(lastnameDoctorTextField.getText());

    }
    public void modificarDoctor(){

        if (idDoctorInterno == null){
            mostrarMensaje("Information", "You must select a record");
            return;

        }
        if (nameDoctorTextField.getText() == null || nameDoctorTextField.getText().isEmpty())
        {
            mostrarMensaje("Error", "You must provide data" );
            nameDoctorTextField.requestFocus();
            return;
        }
        var doctor = doctorTable.getSelectionModel().getSelectedItem();
        recolectarDatosFormulario(doctor);
        doctorServicio.guardarDoctor(doctor);
        mostrarMensaje("Information", "Data modify");
        listarDoctores();
    }
    public void eliminarDoctor(){
        var doctor = doctorTable.getSelectionModel().getSelectedItem();
        if (doctor!=null){
            logger.info("Regirsto a eliminar{}", doctor.toString());
            doctorServicio.eliminarDoctor(doctor);
            mostrarMensaje("Information", "Doctor delete" +doctor.getIdDoctor());
            limpiarFormulario();
            listarDoctores();

        } else {
            mostrarMensaje("Error", "Is not been select any data");
        }
    }
    public void limpiarFormulario(){
        idDoctorInterno=null;
        nameDoctorTextField.clear();
        lastnameDoctorTextField.clear();


    }
    private void mostrarMensaje(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        //con esto esperamos a que el usuario muestre el mensaje
        alerta.showAndWait();

    }
}
