package co.edu.poli.Examen2.aplicacion.puerto.salida;

import java.util.List;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Puerto de salida secundario para recuperar todos los exámenes almacenados.
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public interface ListarExamenesPort {

    /**
     * Recupera la lista completa de exámenes persistidos.
     * 
     * @return Colección de exámenes de sangre.
     */
    List<ExamenSangre> listar();
}
