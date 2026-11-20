package co.edu.poli.Examen2.aplicacion.puerto.salida;

import java.util.List;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Puerto de salida secundario para reemplazar completamente la colección de exámenes en memoria.
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public interface ReemplazarExamenesPort {

    /**
     * Reemplaza el conjunto actual de exámenes por una nueva lista.
     * 
     * @param examenes Nueva lista de exámenes a establecer.
     */
    void reemplazar(List<ExamenSangre> examenes);
}
