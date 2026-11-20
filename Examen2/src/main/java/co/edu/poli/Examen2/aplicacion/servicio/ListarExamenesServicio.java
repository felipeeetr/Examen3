package co.edu.poli.Examen2.aplicacion.servicio;

import java.util.List;
import co.edu.poli.Examen2.aplicacion.puerto.entrada.ListarExamenesUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.salida.ListarExamenesPort;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/** Servicio que implementa la lógica para listar los exámenes de sangre. */
public class ListarExamenesServicio implements ListarExamenesUseCase {
    public ListarExamenesPort listarExamenesPort;

    public ListarExamenesServicio(ListarExamenesPort listarExamenesPort) {
        this.listarExamenesPort = listarExamenesPort;
    }

    @Override
    public List<ExamenSangre> listar() {
        return listarExamenesPort.listar();
    }
}

