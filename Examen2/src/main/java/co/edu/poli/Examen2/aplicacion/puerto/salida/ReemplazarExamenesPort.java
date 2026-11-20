package co.edu.poli.Examen2.aplicacion.puerto.salida;

import java.util.List;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/** Puerto de salida para reemplazar la lista actual de exámenes de sangre. */
public interface ReemplazarExamenesPort {
    void reemplazar(List<ExamenSangre> examenes);
}

