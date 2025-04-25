package bts.LabLune.controlador;
import bts.LabLune.modelo.Resultat;
import bts.LabLune.modelo.Patient;
import bts.LabLune.servicio.IPacientServicio;
import bts.LabLune.servicio.IResultatServicio;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultatControlador {

    @Autowired
    private IResultatServicio resultatService;

    @Autowired
    private IPacientServicio patientService;

    @FXML
    private ComboBox<Patient> comboBoxPaciente;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TableView<Resultat> tablaResultados;

    private ObservableList<Resultat> listaResultados;

    @FXML
    public void initialize() {
        cargarPacientes();
        cargarResultados();
    }

    private void cargarPacientes() {
        List<Patient> pacientes = patientService.listarPacientes(); // Este método debe existir
        comboBoxPaciente.setItems(FXCollections.observableArrayList(pacientes));
    }

    private void cargarResultados() {
        listaResultados = FXCollections.observableArrayList(resultatService.listar());
        tablaResultados.setItems(listaResultados);
    }
    public void cargarPacienteFormulario(){
        //Seleccionamos "SINGLE" para seleccionar un registro a la vez
        var pacient = resultatTable.getSelectionModel().getSelectedItem();
        if(pacient != null) {
            //crear un atributo idpaciente en fxml idinterno
            idPacientInterno = pacient.getIdPatient();
            namePatientTextField.setText(pacient.getNamePatient());
            lastnamePatientTextField.setText(pacient.getLastnamePatient());

        }
    }


    @FXML
    private void agregarResultado() {
        Resultat r = new Resultat();
        r.setPatient(comboBoxPaciente.getValue());
        r.setDescripcion(txtDescripcion.getText());
        resultatService.guardarResultat(r);
        cargarResultados(); // Refrescar la tabla
        limpiarCampos();
    }

    @FXML
    private void eliminarResultado() {
        Resultat seleccionado = tablaResultados.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            resultatService.eliminarResultat(seleccionado);
            cargarResultados(); // Refrescar
        }
    }

    @FXML
    private void limpiarCampos() {
        txtDescripcion.clear();
        comboBoxPaciente.getSelectionModel().clearSelection();
    }
}