package co.edu.poli.Examen2.dominio.costo;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;
import co.edu.poli.Examen2.dominio.modelo.TipoSangre;

/**
 * Estrategia que aplica un recargo porcentual condicionado al tipo de sangre específico del paciente (Patrón Strategy).
 * 
 * <p>Permite gravar tipos sanguíneos particulares (por ejemplo AB u O) debido a dificultades
 * en la obtención de reactivos específicos o consideraciones técnicas del laboratorio.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class CostoRecargoTipoSangre implements EstrategiaCosto {

    /** Tipo de sangre objetivo que activará el recargo. */
    public TipoSangre tipoSangre;

    /** Fracción decimal del porcentaje de recargo aplicable. */
    public double porcentaje;

    /**
     * Crea una nueva estrategia asociando el tipo de sangre y la tasa de recargo.
     * 
     * @param tipoSangre Grupo sanguíneo sujeto a recargo.
     * @param porcentaje Fracción decimal del recargo (ej. 0.15 para 15%).
     */
    public CostoRecargoTipoSangre(TipoSangre tipoSangre, double porcentaje) {
        this.tipoSangre = tipoSangre;
        this.porcentaje = porcentaje;
    }

    /**
     * Aplica el recargo configurado si el tipo de sangre del examen coincide con el objetivo.
     * 
     * @param examen Examen evaluado.
     * @return Costo final con recargo si coincide el tipo; caso contrario, el costo base.
     */
    @Override
    public double calcular(ExamenSangre examen) {
        if (examen.tipoSangre == tipoSangre) {
            return Math.round(examen.costo * (1 + porcentaje) * 100.0) / 100.0;
        }
        return examen.costo;
    }
}
