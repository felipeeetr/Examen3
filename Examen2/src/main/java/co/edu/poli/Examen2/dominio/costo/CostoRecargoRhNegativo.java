package co.edu.poli.Examen2.dominio.costo;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;
import co.edu.poli.Examen2.dominio.modelo.FactorRH;

/**
 * Estrategia que aplica un recargo porcentual cuando el factor RH del paciente es negativo (Patrón Strategy).
 * 
 * <p>Si el factor RH es {@link FactorRH#NEGATIVO}, calcula el precio incrementado según el porcentaje
 * indicado y redondea el resultado a dos posiciones decimales.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class CostoRecargoRhNegativo implements EstrategiaCosto {

    /** Fracción decimal del porcentaje de recargo (ej. 0.20 para 20%). */
    public double porcentaje;

    /**
     * Crea una estrategia con el porcentaje de recargo especificado.
     * 
     * @param porcentaje Valor fraccionario del recargo (ej. 0.20 = 20%).
     */
    public CostoRecargoRhNegativo(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    /**
     * Aplica el recargo al examen si su factor RH es negativo.
     * 
     * @param examen Examen de sangre a evaluar.
     * @return Costo recalculado con el recargo aplicado o el costo original si el factor es positivo.
     */
    @Override
    public double calcular(ExamenSangre examen) {
        if (examen.factorRH == FactorRH.NEGATIVO) {
            return Math.round(examen.costo * (1 + porcentaje) * 100.0) / 100.0;
        }
        return examen.costo;
    }
}
