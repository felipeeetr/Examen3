package co.edu.poli.Examen2.aplicacion.puerto.salida;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/** Puerto de salida para persistir un examen de sangre. */
public interface GuardarExamenPort {
    void guardar(ExamenSangre examen);
}
