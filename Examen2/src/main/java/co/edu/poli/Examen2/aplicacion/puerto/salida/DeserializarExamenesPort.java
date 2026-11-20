package co.edu.poli.Examen2.aplicacion.puerto.salida;

import java.util.List;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Puerto de salida secundario para leer y deserializar exámenes desde un medio externo.
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public interface DeserializarExamenesPort {

    /**
     * Lee y reconstruye la lista de exámenes de sangre desde almacenamiento persistente.
     * 
     * @return Lista de exámenes leídos del medio persistente.
     * @throws PersistenciaException si ocurre un fallo al acceder o deserializar el archivo.
     */
    List<ExamenSangre> leer();
}
