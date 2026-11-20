package co.edu.poli.Examen2.dominio.constructor;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;
import co.edu.poli.Examen2.dominio.modelo.FactorRH;
import co.edu.poli.Examen2.dominio.modelo.TipoSangre;

/**
 * Constructor fluido para la creación validada de objetos {@link ExamenSangre} (Patrón Builder).
 * 
 * <p>Encapsula la lógica de conversión de tipos de datos de presentación (cadenas de texto)
 * hacia los tipos de dominio requeridos y garantiza la ejecución de las validaciones de negocio
 * en el momento de la construcción.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class ExamenSangreBuilder {

    /** Código del examen en proceso de construcción. */
    public String codigo;

    /** Nombre del paciente en proceso de construcción. */
    public String nombrePaciente;

    /** Costo numérico del examen en proceso de construcción. */
    public double costo;

    /** Grupo sanguíneo en proceso de construcción. */
    public TipoSangre tipoSangre;

    /** Factor RH en proceso de construcción. */
    public FactorRH factorRH;

    /**
     * Establece el código del examen de sangre.
     * 
     * @param codigo Código identificador.
     * @return La instancia actual de {@code ExamenSangreBuilder} para encadenamiento.
     */
    public ExamenSangreBuilder codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    /**
     * Establece el nombre completo del paciente.
     * 
     * @param nombrePaciente Nombre del paciente.
     * @return La instancia actual de {@code ExamenSangreBuilder} para encadenamiento.
     */
    public ExamenSangreBuilder nombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
        return this;
    }

    /**
     * Establece el costo del examen a partir de un valor numérico primitivo.
     * 
     * @param costo Costo económico del examen.
     * @return La instancia actual de {@code ExamenSangreBuilder} para encadenamiento.
     */
    public ExamenSangreBuilder costo(double costo) {
        this.costo = costo;
        return this;
    }

    /**
     * Convierte y establece el costo del examen a partir de una cadena de texto.
     * 
     * @param texto Cadena de texto con el costo numérico (ej. "150.00").
     * @return La instancia actual de {@code ExamenSangreBuilder} para encadenamiento.
     * @throws IllegalArgumentException si el texto es nulo o no representa un número double válido.
     */
    public ExamenSangreBuilder costoDesdeTexto(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("El costo debe ser un valor numérico válido");
        }
        try {
            this.costo = Double.parseDouble(texto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El costo debe ser un valor numérico válido");
        }
        return this;
    }

    /**
     * Establece el tipo de sangre del examen.
     * 
     * @param tipoSangre Instancia de {@link TipoSangre}.
     * @return La instancia actual de {@code ExamenSangreBuilder} para encadenamiento.
     */
    public ExamenSangreBuilder tipoSangre(TipoSangre tipoSangre) {
        this.tipoSangre = tipoSangre;
        return this;
    }

    /**
     * Convierte y establece el tipo de sangre a partir de su representación textual.
     * 
     * @param texto Cadena con el nombre del tipo de sangre (ej. "A", "B", "AB", "O").
     * @return La instancia actual de {@code ExamenSangreBuilder} para encadenamiento.
     * @throws IllegalArgumentException si el texto no corresponde a ningún valor válido de {@link TipoSangre}.
     */
    public ExamenSangreBuilder tipoSangreDesdeTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            this.tipoSangre = null;
        } else {
            try {
                this.tipoSangre = TipoSangre.valueOf(texto.trim());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Tipo de sangre no válido: " + texto);
            }
        }
        return this;
    }

    /**
     * Establece el factor RH del examen.
     * 
     * @param factorRH Instancia de {@link FactorRH}.
     * @return La instancia actual de {@code ExamenSangreBuilder} para encadenamiento.
     */
    public ExamenSangreBuilder factorRH(FactorRH factorRH) {
        this.factorRH = factorRH;
        return this;
    }

    /**
     * Instancia el objeto {@link ExamenSangre} con los valores acumulados y ejecuta su validación.
     * 
     * @return Una instancia válida y completamente construida de {@link ExamenSangre}.
     * @throws IllegalArgumentException si los atributos configurados violan las reglas de validación del modelo.
     */
    public ExamenSangre construir() {
        ExamenSangre examen = new ExamenSangre(codigo, nombrePaciente, costo, tipoSangre, factorRH);
        examen.validar();
        return examen;
    }
}
