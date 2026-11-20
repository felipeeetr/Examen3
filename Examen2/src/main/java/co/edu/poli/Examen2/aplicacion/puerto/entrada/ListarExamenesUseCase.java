package co.edu.poli.Examen2.aplicacion.puerto.entrada;

import java.util.List;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/** Puerto de entrada para el caso de uso de listar exámenes de sangre. */
public interface ListarExamenesUseCase {
    List<ExamenSangre> listar();
}

