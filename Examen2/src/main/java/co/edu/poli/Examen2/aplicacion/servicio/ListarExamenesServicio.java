package co.edu.poli.Examen2.aplicacion.servicio;

import java.util.List;
import co.edu.poli.Examen2.aplicacion.puerto.entrada.ListarExamenesUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.salida.ListarExamenesPort;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Servicio de aplicación que implementa el caso de uso para listar todos los exámenes de sangre.
 * 
 * <p>Delega la recuperación de la información en el puerto secundario {@link ListarExamenesPort}.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class ListarExamenesServicio implements ListarExamenesUseCase {

    /** Puerto secundario para recuperar los exámenes persistidos. */
    public ListarExamenesPort listarExamenesPort;

    /**
     * Construye el servicio asociando el puerto de salida de listado.
     * 
     * @param listarExamenesPort Puerto para consultar exámenes.
     */
    public ListarExamenesServicio(ListarExamenesPort listarExamenesPort) {
        this.listarExamenesPort = listarExamenesPort;
    }

    /**
     * Obtiene la colección completa de exámenes desde el repositorio.
     * 
     * @return Lista con los exámenes registrados.
     */
    @Override
    public List<ExamenSangre> listar() {
        return listarExamenesPort.listar();
    }
}
