package co.edu.poli.Examen2.aplicacion.puerto.salida;

import java.util.List;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/** Puerto de salida para serializar y escribir exámenes en almacenamiento externo. */
public interface SerializarExamenesPort {
    void escribir(List<ExamenSangre> examenes);
}

