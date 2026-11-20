package co.edu.poli.Examen2.vista;

import co.edu.poli.Examen2.aplicacion.servicio.DeserializarExamenesServicio;
import co.edu.poli.Examen2.aplicacion.servicio.GuardarExamenServicio;
import co.edu.poli.Examen2.aplicacion.servicio.ListarExamenesServicio;
import co.edu.poli.Examen2.aplicacion.servicio.SerializarExamenesServicio;
import co.edu.poli.Examen2.infraestructura.persistencia.ExamenArchivoAdapter;
import co.edu.poli.Examen2.infraestructura.persistencia.ExamenMemoriaRepository;
import co.edu.poli.Examen2.infraestructura.ui.PrimaryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** Raíz de composición y punto de entrada de la aplicación JavaFX. */
public class App extends Application {

    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        ExamenMemoriaRepository memoriaRepo = new ExamenMemoriaRepository();
        ExamenArchivoAdapter archivoAdapter = new ExamenArchivoAdapter("examenes.ser");

        GuardarExamenServicio guardarServicio = new GuardarExamenServicio(memoriaRepo, memoriaRepo);
        ListarExamenesServicio listarServicio = new ListarExamenesServicio(memoriaRepo);
        SerializarExamenesServicio serializarServicio = new SerializarExamenesServicio(memoriaRepo, archivoAdapter);
        DeserializarExamenesServicio deserializarServicio = new DeserializarExamenesServicio(archivoAdapter, memoriaRepo);

        PrimaryController controller = new PrimaryController(
            guardarServicio,
            listarServicio,
            serializarServicio,
            deserializarServicio
        );

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/co/edu/poli/Examen2/view/primary.fxml"));
        fxmlLoader.setControllerFactory(c -> controller);

        scene = new Scene(fxmlLoader.load(), 640, 580);
        stage.setTitle("Exámenes de Sangre");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
