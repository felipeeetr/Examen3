package co.edu.poli.Examen2.aplicacion.servicio;

import java.util.List;
import co.edu.poli.Examen2.aplicacion.puerto.entrada.SerializarExamenesUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.salida.ListarExamenesPort;
import co.edu.poli.Examen2.aplicacion.puerto.salida.SerializarExamenesPort;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/** Servicio que implementa la lógica para serializar los exámenes de sangre. */
public class SerializarExamenesServicio implements SerializarExamenesUseCase {
    public ListarExamenesPort listarExamenesPort;
    public SerializarExamenesPort serializarExamenesPort;

    public SerializarExamenesServicio(ListarExamenesPort listarExamenesPort, SerializarExamenesPort serializarExamenesPort) {
        this.listarExamenesPort = listarExamenesPort;
        this.serializarExamenesPort = serializarExamenesPort;
    }

    @Override
    public int serializar() {
        List<ExamenSangre> lista = listarExamenesPort.listar();
        serializarExamenesPort.escribir(lista);
        return lista.size();
    }
}

