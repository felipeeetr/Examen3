package co.edu.poli.Examen2.dominio.costo;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Define el contrato de la familia de algoritmos para el cálculo del costo final de un examen (Patrón Strategy).
 * 
 * <p>Permite desacoplar las reglas comerciales de cálculo de precios del servicio de aplicación
 * y del modelo de dominio, facilitando la adición de nuevas políticas tarifarias sin alterar el código existente.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public interface EstrategiaCosto {

    /**
     * Calcula el costo final del examen de sangre en función de sus características.
     * 
     * @param examen Examen de sangre base cuyo costo será evaluado.
     * @return Costo final calculado tras aplicar las políticas correspondientes.
     */
    double calcular(ExamenSangre examen);
}
