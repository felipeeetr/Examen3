package co.edu.poli.Examen2.aplicacion.servicio;

import java.util.List;
import co.edu.poli.Examen2.aplicacion.puerto.entrada.DeserializarExamenesUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.salida.DeserializarExamenesPort;
import co.edu.poli.Examen2.aplicacion.puerto.salida.ReemplazarExamenesPort;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Servicio de aplicación que implementa el caso de uso para deserializar exámenes de sangre.
 * 
 * <p>Lee la colección externa mediante {@link DeserializarExamenesPort}, actualiza el repositorio
 * en memoria usando {@link ReemplazarExamenesPort} y retorna la cantidad de elementos cargados.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class DeserializarExamenesServicio implements DeserializarExamenesUseCase {

    /** Puerto secundario para leer exámenes del almacenamiento externo. */
    public DeserializarExamenesPort deserializarExamenesPort;

    /** Puerto secundario para actualizar la colección activa en memoria. */
    public ReemplazarExamenesPort reemplazarExamenesPort;

    /**
     * Construye el servicio inyectando los puertos de lectura y reemplazo.
     * 
     * @param deserializarExamenesPort Puerto para leer del archivo.
     * @param reemplazarExamenesPort Puerto para reemplazar la memoria activa.
     */
    public DeserializarExamenesServicio(DeserializarExamenesPort deserializarExamenesPort, ReemplazarExamenesPort reemplazarExamenesPort) {
        this.deserializarExamenesPort = deserializarExamenesPort;
        this.reemplazarExamenesPort = reemplazarExamenesPort;
    }

    /**
     * Ejecuta la lectura desde archivo y restauración en memoria de los exámenes.
     * 
     * @return Número de exámenes cargados con éxito.
     */
    @Override
    public int deserializar() {
        List<ExamenSangre> lista = deserializarExamenesPort.leer();
        reemplazarExamenesPort.reemplazar(lista);
        return lista.size();
    }
}
