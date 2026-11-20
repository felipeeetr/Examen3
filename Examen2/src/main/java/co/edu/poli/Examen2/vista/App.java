package co.edu.poli.Examen2.vista;

import co.edu.poli.Examen2.aplicacion.fachada.ExamenesFacade;
import co.edu.poli.Examen2.aplicacion.servicio.DeserializarExamenesServicio;
import co.edu.poli.Examen2.aplicacion.servicio.GuardarExamenServicio;
import co.edu.poli.Examen2.aplicacion.servicio.ListarExamenesServicio;
import co.edu.poli.Examen2.aplicacion.servicio.SerializarExamenesServicio;
import co.edu.poli.Examen2.dominio.costo.CostoRecargoRhNegativo;
import co.edu.poli.Examen2.infraestructura.persistencia.ExamenArchivoAdapter;
import co.edu.poli.Examen2.infraestructura.persistencia.ExamenMemoriaRepository;
import co.edu.poli.Examen2.infraestructura.ui.PrimaryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Raíz de composición (Composition Root) y punto de entrada de la aplicación JavaFX.
 * 
 * <p>Inicializa los adaptadores de salida, los servicios de aplicación con sus estrategias de costo,
 * ensambla la fachada {@link ExamenesFacade}, configura el controlador {@link PrimaryController}
 * mediante una fábrica personalizada en {@link FXMLLoader} y despliega la ventana principal.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class App extends Application {

    /** Escena gráfica principal de la ventana de la aplicación. */
    public static Scene scene;

    /**
     * Inicializa y despliega la interfaz gráfica de usuario.
     * 
     * @param stage Escenario principal provisto por la plataforma JavaFX.
     * @throws IOException si no es posible cargar el archivo FXML de la vista.
     */
    @Override
    public void start(Stage stage) throws IOException {
        ExamenMemoriaRepository memoriaRepo = new ExamenMemoriaRepository();
        ExamenArchivoAdapter archivoAdapter = new ExamenArchivoAdapter("examenes.ser");

        GuardarExamenServicio guardarServicio = new GuardarExamenServicio(
            memoriaRepo,
            memoriaRepo,
            new CostoRecargoRhNegativo(0.20)
        );
        ListarExamenesServicio listarServicio = new ListarExamenesServicio(memoriaRepo);
        SerializarExamenesServicio serializarServicio = new SerializarExamenesServicio(memoriaRepo, archivoAdapter);
        DeserializarExamenesServicio deserializarServicio = new DeserializarExamenesServicio(archivoAdapter, memoriaRepo);

        ExamenesFacade fachada = new ExamenesFacade(
            guardarServicio,
            listarServicio,
            serializarServicio,
            deserializarServicio
        );

        PrimaryController controller = new PrimaryController(fachada);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/co/edu/poli/Examen2/view/primary.fxml"));
        fxmlLoader.setControllerFactory(c -> controller);

        scene = new Scene(fxmlLoader.load(), 640, 580);
        stage.setTitle("Exámenes de Sangre");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Método de arranque estándar para entornos donde el lanzador de JavaFX lo requiera.
     * 
     * @param args Argumentos pasados por línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}
