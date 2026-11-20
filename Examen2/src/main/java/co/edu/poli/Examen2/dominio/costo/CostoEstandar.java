package co.edu.poli.Examen2.dominio.costo;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Estrategia de costo estándar que no aplica ningún recargo adicional (Patrón Strategy).
 * 
 * <p>Retorna el valor numérico base registrado originalmente en el examen.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class CostoEstandar implements EstrategiaCosto {

    /**
     * Retorna el costo base del examen sin modificaciones.
     * 
     * @param examen Examen de sangre evaluado.
     * @return El costo original del examen.
     */
    @Override
    public double calcular(ExamenSangre examen) {
        return examen.costo;
    }
}
