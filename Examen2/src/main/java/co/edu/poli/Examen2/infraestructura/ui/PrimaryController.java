package co.edu.poli.Examen2.infraestructura.ui;

import java.util.List;
import co.edu.poli.Examen2.aplicacion.puerto.entrada.DeserializarExamenesUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.entrada.GuardarExamenUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.entrada.ListarExamenesUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.entrada.SerializarExamenesUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.salida.PersistenciaException;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;
import co.edu.poli.Examen2.dominio.modelo.FactorRH;
import co.edu.poli.Examen2.dominio.modelo.TipoSangre;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/** Adaptador de entrada que controla la interfaz gráfica JavaFX para la gestión de exámenes. */
public class PrimaryController {

    @FXML
    public TextField txtCodigoExamen;
    @FXML
    public TextField txtNombrePaciente;
    @FXML
    public TextField txtCosto;
    @FXML
    public ComboBox<String> cmbTipoSangre;
    @FXML
    public RadioButton rbPositivo;
    @FXML
    public RadioButton rbNegativo;
    @FXML
    public ToggleGroup tgFactorRH;
    @FXML
    public TextArea txtAreaMensajes;

    public GuardarExamenUseCase guardarExamenUseCase;
    public ListarExamenesUseCase listarExamenesUseCase;
    public SerializarExamenesUseCase serializarExamenesUseCase;
    public DeserializarExamenesUseCase deserializarExamenesUseCase;

    public PrimaryController(GuardarExamenUseCase guardarExamenUseCase,
                             ListarExamenesUseCase listarExamenesUseCase,
                             SerializarExamenesUseCase serializarExamenesUseCase,
                             DeserializarExamenesUseCase deserializarExamenesUseCase) {
        this.guardarExamenUseCase = guardarExamenUseCase;
        this.listarExamenesUseCase = listarExamenesUseCase;
        this.serializarExamenesUseCase = serializarExamenesUseCase;
        this.deserializarExamenesUseCase = deserializarExamenesUseCase;
    }

    @FXML
    public void handleGuardar() {
        String codigo = txtCodigoExamen.getText();
        String nombrePaciente = txtNombrePaciente.getText();
        double costo;
        try {
            costo = Double.parseDouble(txtCosto.getText());
        } catch (NumberFormatException e) {
            txtAreaMensajes.appendText("El costo debe ser un valor numérico válido\n");
            return;
        }

        TipoSangre tipoSangre = null;
        if (cmbTipoSangre.getValue() != null) {
            try {
                tipoSangre = TipoSangre.valueOf(cmbTipoSangre.getValue());
            } catch (IllegalArgumentException e) {
                tipoSangre = null;
            }
        }

        FactorRH factorRH = null;
        if (rbPositivo.isSelected()) {
            factorRH = FactorRH.POSITIVO;
        } else if (rbNegativo.isSelected()) {
            factorRH = FactorRH.NEGATIVO;
        }

        ExamenSangre examen = new ExamenSangre(codigo, nombrePaciente, costo, tipoSangre, factorRH);
        try {
            guardarExamenUseCase.guardar(examen);
            txtAreaMensajes.appendText("Examen guardado exitosamente: " + examen + "\n");
        } catch (IllegalArgumentException | IllegalStateException e) {
            txtAreaMensajes.appendText(e.getMessage() + "\n");
        }
    }

    @FXML
    public void handleListar() {
        List<ExamenSangre> lista = listarExamenesUseCase.listar();
        if (lista.isEmpty()) {
            txtAreaMensajes.appendText("No hay exámenes registrados\n");
        } else {
            for (ExamenSangre examen : lista) {
                txtAreaMensajes.appendText(examen.toString() + "\n");
            }
        }
    }

    @FXML
    public void handleSerializar() {
        try {
            int cantidad = serializarExamenesUseCase.serializar();
            txtAreaMensajes.appendText("Exámenes serializados: " + cantidad + "\n");
        } catch (PersistenciaException e) {
            txtAreaMensajes.appendText(e.getMessage() + "\n");
        }
    }

    @FXML
    public void handleDeserializar() {
        try {
            int cantidad = deserializarExamenesUseCase.deserializar();
            txtAreaMensajes.appendText("Exámenes deserializados: " + cantidad + "\n");
        } catch (PersistenciaException e) {
            txtAreaMensajes.appendText(e.getMessage() + "\n");
        }
    }

    @FXML
    public void handleLimpiar() {
        txtCodigoExamen.clear();
        txtNombrePaciente.clear();
        txtCosto.clear();
        cmbTipoSangre.setValue(null);
        cmbTipoSangre.getSelectionModel().clearSelection();
        rbPositivo.setSelected(true);
    }
}

