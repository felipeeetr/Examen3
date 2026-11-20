package co.edu.poli.Examen2.aplicacion.puerto.entrada;

import java.util.List;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Puerto de entrada primario para el caso de uso de consulta y listado de exámenes de sangre.
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public interface ListarExamenesUseCase {

    /**
     * Obtiene la colección completa de todos los exámenes de sangre registrados actualmente.
     * 
     * @return Lista con los exámenes registrados. Puede estar vacía pero nunca es nula.
     */
    List<ExamenSangre> listar();
}
