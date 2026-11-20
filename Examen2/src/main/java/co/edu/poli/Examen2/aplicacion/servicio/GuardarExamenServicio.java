package co.edu.poli.Examen2.aplicacion.servicio;

import co.edu.poli.Examen2.aplicacion.puerto.entrada.GuardarExamenUseCase;
import co.edu.poli.Examen2.aplicacion.puerto.salida.BuscarExamenPort;
import co.edu.poli.Examen2.aplicacion.puerto.salida.GuardarExamenPort;
import co.edu.poli.Examen2.dominio.costo.CostoEstandar;
import co.edu.poli.Examen2.dominio.costo.EstrategiaCosto;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Servicio de aplicación que implementa el caso de uso para guardar exámenes de sangre.
 * 
 * <p>Orquesta la validación de negocio del examen, verifica la no duplicidad del código mediante
 * {@link BuscarExamenPort}, calcula el costo final delegando en {@link EstrategiaCosto} y
 * finalmente delega la persistencia en {@link GuardarExamenPort}.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class GuardarExamenServicio implements GuardarExamenUseCase {

    /** Puerto secundario para consultar la existencia de exámenes por código. */
    public BuscarExamenPort buscarExamenPort;

    /** Puerto secundario para persistir el examen. */
    public GuardarExamenPort guardarExamenPort;

    /** Estrategia inyectada para el cálculo dinámico del costo final. */
    public EstrategiaCosto estrategiaCosto;

    /**
     * Construye el servicio asignando la estrategia de costo estándar por defecto.
     * 
     * @param buscarExamenPort Puerto de búsqueda de exámenes.
     * @param guardarExamenPort Puerto de persistencia de exámenes.
     */
    public GuardarExamenServicio(BuscarExamenPort buscarExamenPort, GuardarExamenPort guardarExamenPort) {
        this(buscarExamenPort, guardarExamenPort, new CostoEstandar());
    }

    /**
     * Construye el servicio especificando una estrategia de costo personalizada.
     * 
     * @param buscarExamenPort Puerto de búsqueda de exámenes.
     * @param guardarExamenPort Puerto de persistencia de exámenes.
     * @param estrategiaCosto Estrategia para el cálculo del costo.
     */
    public GuardarExamenServicio(BuscarExamenPort buscarExamenPort, GuardarExamenPort guardarExamenPort, EstrategiaCosto estrategiaCosto) {
        this.buscarExamenPort = buscarExamenPort;
        this.guardarExamenPort = guardarExamenPort;
        this.estrategiaCosto = estrategiaCosto;
    }

    /**
     * Valida, verifica unicidad, calcula el costo final y guarda el examen de sangre.
     * 
     * @param examen Examen a ser procesado y registrado.
     * @throws IllegalArgumentException si los datos del examen fallan la validación del modelo.
     * @throws IllegalStateException si ya existe un examen registrado con el mismo código.
     */
    @Override
    public void guardar(ExamenSangre examen) {
        examen.validar();
        if (buscarExamenPort.buscarPorCodigo(examen.codigo) != null) {
            throw new IllegalStateException("El código ya existe");
        }
        examen.costo = estrategiaCosto.calcular(examen);
        guardarExamenPort.guardar(examen);
    }
}
