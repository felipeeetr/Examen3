package co.edu.poli.Examen2.aplicacion.servicio;

import java.util.List;
import co.edu.poli.Examen2.aplicacion.puerto.entrada.DeserializarExamenesUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.salida.DeserializarExamenesPort;
import co.edu.poli.Examen2.aplicacion.puerto.salida.ReemplazarExamenesPort;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/** Servicio que implementa la lógica para deserializar exámenes de sangre. */
public class DeserializarExamenesServicio implements DeserializarExamenesUseCase {
    public DeserializarExamenesPort deserializarExamenesPort;
    public ReemplazarExamenesPort reemplazarExamenesPort;

    public DeserializarExamenesServicio(DeserializarExamenesPort deserializarExamenesPort, ReemplazarExamenesPort reemplazarExamenesPort) {
        this.deserializarExamenesPort = deserializarExamenesPort;
        this.reemplazarExamenesPort = reemplazarExamenesPort;
    }

    @Override
    public int deserializar() {
        List<ExamenSangre> lista = deserializarExamenesPort.leer();
        reemplazarExamenesPort.reemplazar(lista);
        return lista.size();
    }
}

