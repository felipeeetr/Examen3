package co.edu.poli.Examen2.aplicacion.puerto.salida;

import java.util.List;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/** Puerto de salida para obtener la lista de exámenes de sangre persistidos. */
public interface ListarExamenesPort {
    List<ExamenSangre> listar();
}

