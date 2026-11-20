package co.edu.poli.Examen2.aplicacion.servicio;

import java.util.List;
import co.edu.poli.Examen2.aplicacion.puerto.entrada.SerializarExamenesUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.salida.ListarExamenesPort;
import co.edu.poli.Examen2.aplicacion.puerto.salida.SerializarExamenesPort;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Servicio de aplicación que implementa el caso de uso para serializar los exámenes de sangre.
 * 
 * <p>Recupera la lista actual a través de {@link ListarExamenesPort}, la envía a {@link SerializarExamenesPort}
 * y devuelve el número total de exámenes serializados.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class SerializarExamenesServicio implements SerializarExamenesUseCase {

    /** Puerto secundario para obtener los exámenes en memoria. */
    public ListarExamenesPort listarExamenesPort;

    /** Puerto secundario para persistir la lista serializada en archivo. */
    public SerializarExamenesPort serializarExamenesPort;

    /**
     * Construye el servicio inyectando los puertos requeridos.
     * 
     * @param listarExamenesPort Puerto para consultar los exámenes.
     * @param serializarExamenesPort Puerto para escribir la colección en almacenamiento secundario.
     */
    public SerializarExamenesServicio(ListarExamenesPort listarExamenesPort, SerializarExamenesPort serializarExamenesPort) {
        this.listarExamenesPort = listarExamenesPort;
        this.serializarExamenesPort = serializarExamenesPort;
    }

    /**
     * Ejecuta la serialización de todos los exámenes registrados.
     * 
     * @return Cantidad de exámenes serializados.
     */
    @Override
    public int serializar() {
        List<ExamenSangre> lista = listarExamenesPort.listar();
        serializarExamenesPort.escribir(lista);
        return lista.size();
    }
}
