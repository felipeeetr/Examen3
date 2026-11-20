package co.edu.poli.Examen2.aplicacion.puerto.salida;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Puerto de salida secundario para buscar un examen de sangre por su código único.
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public interface BuscarExamenPort {

    /**
     * Busca un examen por su código de identificación.
     * 
     * @param codigo Código identificador a consultar.
     * @return El {@link ExamenSangre} coincidente, o {@code null} si no existe ningún examen con dicho código.
     */
    ExamenSangre buscarPorCodigo(String codigo);
}
