package co.edu.poli.Examen2.aplicacion.servicio;

import co.edu.poli.Examen2.aplicacion.puerto.entrada.GuardarExamenUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.salida.BuscarExamenPort;
import co.edu.poli.Examen2.aplicacion.puerto.salida.GuardarExamenPort;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/** Servicio que implementa la lógica para guardar un examen de sangre. */
public class GuardarExamenServicio implements GuardarExamenUseCase {
    public BuscarExamenPort buscarExamenPort;
    public GuardarExamenPort guardarExamenPort;

    public GuardarExamenServicio(BuscarExamenPort buscarExamenPort, GuardarExamenPort guardarExamenPort) {
        this.buscarExamenPort = buscarExamenPort;
        this.guardarExamenPort = guardarExamenPort;
    }

    @Override
    public void guardar(ExamenSangre examen) {
        examen.validar();
        if (buscarExamenPort.buscarPorCodigo(examen.codigo) != null) {
            throw new IllegalStateException("El código ya existe");
        }
        guardarExamenPort.guardar(examen);
    }
}

