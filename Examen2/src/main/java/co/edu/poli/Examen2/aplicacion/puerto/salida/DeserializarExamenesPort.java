package co.edu.poli.Examen2.aplicacion.puerto.salida;

import java.util.List;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/** Puerto de salida para deserializar y leer exámenes desde almacenamiento externo. */
public interface DeserializarExamenesPort {
    List<ExamenSangre> leer();
}

