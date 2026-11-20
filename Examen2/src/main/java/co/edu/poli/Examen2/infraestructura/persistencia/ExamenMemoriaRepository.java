package co.edu.poli.Examen2.infraestructura.persistencia;

import java.util.ArrayList;
import java.util.List;
import co.edu.poli.Examen2.aplicacion.puerto.salida.BuscarExamenPort;
import co.edu.poli.Examen2.aplicacion.puerto.salida.GuardarExamenPort;
import co.edu.poli.Examen2.aplicacion.puerto.salida.ListarExamenesPort;
import co.edu.poli.Examen2.aplicacion.puerto.salida.ReemplazarExamenesPort;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Adaptador de salida para la persistencia de exámenes en memoria volátil (RAM).
 * 
 * <p>Implementa los puertos secundarios {@link GuardarExamenPort}, {@link BuscarExamenPort},
 * {@link ListarExamenesPort} y {@link ReemplazarExamenesPort} utilizando una colección {@link ArrayList}.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class ExamenMemoriaRepository implements GuardarExamenPort, BuscarExamenPort, ListarExamenesPort, ReemplazarExamenesPort {

    /** Colección en memoria donde se almacenan las instancias activas de {@link ExamenSangre}. */
    public List<ExamenSangre> examenes = new ArrayList<>();

    /**
     * Agrega un nuevo examen a la lista en memoria.
     * 
     * @param examen Examen de sangre a almacenar.
     */
    @Override
    public void guardar(ExamenSangre examen) {
        examenes.add(examen);
    }

    /**
     * Busca un examen recorriendo la lista en memoria y comparando el código.
     * 
     * @param codigo Código identificador a buscar.
     * @return El examen coincidente o {@code null} si no existe.
     */
    @Override
    public ExamenSangre buscarPorCodigo(String codigo) {
        for (ExamenSangre examen : examenes) {
            if (examen.codigo != null && examen.codigo.equals(codigo)) {
                return examen;
            }
        }
        return null;
    }

    /**
     * Retorna una copia defensiva de la lista de exámenes almacenados en memoria.
     * 
     * @return Nueva lista conteniendo los exámenes actuales.
     */
    @Override
    public List<ExamenSangre> listar() {
        return new ArrayList<>(examenes);
    }

    /**
     * Limpia la colección activa y la repuebla con los exámenes proporcionados.
     * 
     * @param nuevosExamenes Lista de exámenes a establecer en memoria.
     */
    @Override
    public void reemplazar(List<ExamenSangre> nuevosExamenes) {
        examenes.clear();
        if (nuevosExamenes != null) {
            examenes.addAll(nuevosExamenes);
        }
    }
}
