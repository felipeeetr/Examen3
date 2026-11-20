package co.edu.poli.Examen2.aplicacion.puerto.salida;

import java.util.List;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Puerto de salida secundario para serializar y escribir colecciones de exámenes en un medio externo.
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public interface SerializarExamenesPort {

    /**
     * Escribe la lista de exámenes en un archivo o almacenamiento externo.
     * 
     * @param examenes Lista de exámenes a serializar.
     * @throws PersistenciaException si ocurre algún fallo en el proceso de escritura.
     */
    void escribir(List<ExamenSangre> examenes);
}
