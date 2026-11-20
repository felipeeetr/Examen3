package co.edu.poli.Examen2.infraestructura.persistencia;

import java.util.ArrayList;
import java.util.List;
import co.edu.poli.Examen2.aplicacion.puerto.salida.BuscarExamenPort;
import co.edu.poli.Examen2.aplicacion.puerto.salida.GuardarExamenPort;
import co.edu.poli.Examen2.aplicacion.puerto.salida.ListarExamenesPort;
import co.edu.poli.Examen2.aplicacion.puerto.salida.ReemplazarExamenesPort;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/** Adaptador de persistencia en memoria para la gestión de exámenes de sangre. */
public class ExamenMemoriaRepository implements GuardarExamenPort, BuscarExamenPort, ListarExamenesPort, ReemplazarExamenesPort {
    public List<ExamenSangre> examenes = new ArrayList<>();

    @Override
    public void guardar(ExamenSangre examen) {
        examenes.add(examen);
    }

    @Override
    public ExamenSangre buscarPorCodigo(String codigo) {
        for (ExamenSangre examen : examenes) {
            if (examen.codigo != null && examen.codigo.equals(codigo)) {
                return examen;
            }
        }
        return null;
    }

    @Override
    public List<ExamenSangre> listar() {
        return new ArrayList<>(examenes);
    }

    @Override
    public void reemplazar(List<ExamenSangre> nuevosExamenes) {
        examenes.clear();
        if (nuevosExamenes != null) {
            examenes.addAll(nuevosExamenes);
        }
    }
}

