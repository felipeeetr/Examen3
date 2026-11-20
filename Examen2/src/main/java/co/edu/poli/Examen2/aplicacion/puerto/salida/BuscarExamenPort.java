package co.edu.poli.Examen2.aplicacion.puerto.salida;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/** Puerto de salida para buscar un examen de sangre por su código. */
public interface BuscarExamenPort {
    ExamenSangre buscarPorCodigo(String codigo);
}
