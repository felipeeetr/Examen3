package co.edu.poli.Examen2.infraestructura.ui;

import java.util.List;
import co.edu.poli.Examen2.aplicacion.fachada.ExamenesFacade;
import co.edu.poli.Examen2.aplicacion.puerto.salida.PersistenciaException;
import co.edu.poli.Examen2.dominio.constructor.ExamenSangreBuilder;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;
import co.edu.poli.Examen2.dominio.modelo.FactorRH;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Adaptador de entrada que actúa como controlador de la interfaz gráfica JavaFX.
 * 
 * <p>Captura los eventos del usuario disparados desde {@code primary.fxml}, construye las entidades
 * de dominio a través de {@link ExamenSangreBuilder} y delega la ejecución de los casos de uso
 * exclusivamente en {@link ExamenesFacade}.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class PrimaryController {

    /** Campo de texto para ingresar el código del examen. */
    @FXML
    public TextField txtCodigoExamen;

    /** Campo de texto para ingresar el nombre completo del paciente. */
    @FXML
    public TextField txtNombrePaciente;

    /** Campo de texto para ingresar el costo base del examen. */
    @FXML
    public TextField txtCosto;

    /** Selector desplegable para elegir el tipo de sangre (A, B, AB, O). */
    @FXML
    public ComboBox<String> cmbTipoSangre;

    /** Opción de radio para seleccionar Factor RH Positivo. */
    @FXML
    public RadioButton rbPositivo;

    /** Opción de radio para seleccionar Factor RH Negativo. */
    @FXML
    public RadioButton rbNegativo;

    /** Grupo de alternancia para asegurar la selección exclusiva del factor RH. */
    @FXML
    public ToggleGroup tgFactorRH;

    /** Área de texto donde se imprimen los mensajes, resultados y notificaciones del sistema. */
    @FXML
    public TextArea txtAreaMensajes;

    /** Fachada de aplicación inyectada que centraliza las operaciones del sistema. */
    public ExamenesFacade fachada;

    /**
     * Construye el controlador inyectando la fachada de servicios de aplicación.
     * 
     * @param fachada Fachada {@link ExamenesFacade} que provee acceso unificado a los casos de uso.
     */
    public PrimaryController(ExamenesFacade fachada) {
        this.fachada = fachada;
    }

    /**
     * Manejador del evento de guardar examen (botón 💾 Guardar).
     * 
     * <p>Construye el examen con {@link ExamenSangreBuilder}, lo envía a la fachada y muestra
     * el resultado o los mensajes de error en {@link #txtAreaMensajes}.</p>
     */
    @FXML
    public void handleGuardar() {
        try {
            ExamenSangre examen = new ExamenSangreBuilder()
                .codigo(txtCodigoExamen.getText())
                .nombrePaciente(txtNombrePaciente.getText())
                .costoDesdeTexto(txtCosto.getText())
                .tipoSangreDesdeTexto(cmbTipoSangre.getValue())
                .factorRH(rbPositivo.isSelected() ? FactorRH.POSITIVO : rbNegativo.isSelected() ? FactorRH.NEGATIVO : null)
                .construir();

            fachada.guardar(examen);
            txtAreaMensajes.appendText("Examen guardado exitosamente: " + examen + "\n");
        } catch (IllegalArgumentException | IllegalStateException e) {
            txtAreaMensajes.appendText(e.getMessage() + "\n");
        }
    }

    /**
     * Manejador del evento de listar exámenes (botón 📋 Listar).
     * 
     * <p>Recupera la lista de exámenes desde la fachada e imprime cada registro en {@link #txtAreaMensajes}.</p>
     */
    @FXML
    public void handleListar() {
        List<ExamenSangre> lista = fachada.listar();
        if (lista.isEmpty()) {
            txtAreaMensajes.appendText("No hay exámenes registrados\n");
        } else {
            for (ExamenSangre examen : lista) {
                txtAreaMensajes.appendText(examen.toString() + "\n");
            }
        }
    }

    /**
     * Manejador del evento de serialización (botón 📦 Serializar).
     * 
     * <p>Solicita a la fachada serializar la lista actual en disco e informa la cantidad procesada.</p>
     */
    @FXML
    public void handleSerializar() {
        try {
            int cantidad = fachada.serializar();
            txtAreaMensajes.appendText("Exámenes serializados: " + cantidad + "\n");
        } catch (PersistenciaException e) {
            txtAreaMensajes.appendText(e.getMessage() + "\n");
        }
    }

    /**
     * Manejador del evento de deserialización (botón 📂 Deserializar).
     * 
     * <p>Solicita a la fachada leer y restaurar los exámenes desde archivo e informa la cantidad cargada.</p>
     */
    @FXML
    public void handleDeserializar() {
        try {
            int cantidad = fachada.deserializar();
            txtAreaMensajes.appendText("Exámenes deserializados: " + cantidad + "\n");
        } catch (PersistenciaException e) {
            txtAreaMensajes.appendText(e.getMessage() + "\n");
        }
    }

    /**
     * Manejador del evento de limpieza de campos (botón 🧹 Limpiar).
     * 
     * <p>Borra el texto de los campos de entrada, reinicia el ComboBox y restablece el radio a Positivo.</p>
     */
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
