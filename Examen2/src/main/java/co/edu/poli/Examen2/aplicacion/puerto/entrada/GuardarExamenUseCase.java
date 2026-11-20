package co.edu.poli.Examen2.aplicacion.puerto.entrada;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/** Puerto de entrada para el caso de uso de guardar un examen de sangre. */
public interface GuardarExamenUseCase {
    void guardar(ExamenSangre examen);
}

