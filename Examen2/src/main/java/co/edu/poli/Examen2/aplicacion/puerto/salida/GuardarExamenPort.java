package co.edu.poli.Examen2.aplicacion.puerto.salida;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Puerto de salida secundario para persistir una entidad de examen de sangre.
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public interface GuardarExamenPort {

    /**
     * Persiste un examen de sangre en el mecanismo de almacenamiento del sistema.
     * 
     * @param examen Examen que debe ser guardado.
     */
    void guardar(ExamenSangre examen);
}
