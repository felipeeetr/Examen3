package co.edu.poli.Examen2.aplicacion.fachada;

import java.util.List;
import co.edu.poli.Examen2.aplicacion.puerto.entrada.DeserializarExamenesUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.entrada.GuardarExamenUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.entrada.ListarExamenesUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.entrada.SerializarExamenesUseCase;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Fachada que unifica y simplifica el acceso a todos los casos de uso de exámenes de sangre (Patrón Facade).
 * 
 * <p>Proporciona una interfaz cohesiva de alto nivel para los adaptadores de entrada (como la interfaz gráfica
 * de usuario en JavaFX), evitando que los clientes tengan que conocer y cablear múltiples interfaces de casos de uso.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class ExamenesFacade {

    /** Caso de uso para registrar y guardar exámenes. */
    public GuardarExamenUseCase guardarUseCase;

    /** Caso de uso para listar exámenes existentes. */
    public ListarExamenesUseCase listarUseCase;

    /** Caso de uso para serializar exámenes a disco. */
    public SerializarExamenesUseCase serializarUseCase;

    /** Caso de uso para deserializar exámenes desde disco. */
    public DeserializarExamenesUseCase deserializarUseCase;

    /**
     * Construye la fachada inyectando las dependencias de los cuatro casos de uso principales.
     * 
     * @param guardarUseCase Instancia del caso de uso de guardado.
     * @param listarUseCase Instancia del caso de uso de listado.
     * @param serializarUseCase Instancia del caso de uso de serialización.
     * @param deserializarUseCase Instancia del caso de uso de deserialización.
     */
    public ExamenesFacade(GuardarExamenUseCase guardarUseCase,
                          ListarExamenesUseCase listarUseCase,
                          SerializarExamenesUseCase serializarUseCase,
                          DeserializarExamenesUseCase deserializarUseCase) {
        this.guardarUseCase = guardarUseCase;
        this.listarUseCase = listarUseCase;
        this.serializarUseCase = serializarUseCase;
        this.deserializarUseCase = deserializarUseCase;
    }

    /**
     * Guarda un examen delegando en {@link GuardarExamenUseCase}.
     * 
     * @param examen Examen de sangre a registrar.
     */
    public void guardar(ExamenSangre examen) {
        guardarUseCase.guardar(examen);
    }

    /**
     * Recupera todos los exámenes registrados delegando en {@link ListarExamenesUseCase}.
     * 
     * @return Lista completa de exámenes.
     */
    public List<ExamenSangre> listar() {
        return listarUseCase.listar();
    }

    /**
     * Serializa los exámenes delegando en {@link SerializarExamenesUseCase}.
     * 
     * @return Número de exámenes guardados en el archivo.
     */
    public int serializar() {
        return serializarUseCase.serializar();
    }

    /**
     * Deserializa los exámenes delegando en {@link DeserializarExamenesUseCase}.
     * 
     * @return Número de exámenes restaurados desde el archivo.
     */
    public int deserializar() {
        return deserializarUseCase.deserializar();
    }
}
