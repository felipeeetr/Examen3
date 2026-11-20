package co.edu.poli.Examen2.dominio.modelo;

import java.io.Serializable;

/**
 * Entidad principal de dominio que representa un examen de sangre de laboratorio.
 * 
 * <p>Contiene la información del paciente, identificación del examen, costo base
 * y características serológicas (tipo de sangre y factor RH). Implementa {@link Serializable}
 * para permitir su persistencia desacoplada.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class ExamenSangre implements Serializable {

    /** Identificador de versión para la serialización binaria de la entidad. */
    public static final long serialVersionUID = 1L;

    /** Código identificador único del examen (ej. EXM-001). */
    public String codigo;

    /** Nombre completo del paciente a quien se le realiza el examen. */
    public String nombrePaciente;

    /** Costo económico del examen en dólares o moneda local. */
    public double costo;

    /** Clasificación del grupo sanguíneo del paciente (A, B, AB, O). */
    public TipoSangre tipoSangre;

    /** Clasificación del factor Rhesus del paciente (POSITIVO o NEGATIVO). */
    public FactorRH factorRH;

    /**
     * Construye una nueva instancia de examen de sangre con todos sus atributos requeridos.
     * 
     * @param codigo Identificador alfanumérico único del examen.
     * @param nombrePaciente Nombre del paciente evaluado.
     * @param costo Costo económico del procedimiento médico.
     * @param tipoSangre Clasificación ABO del tipo de sangre.
     * @param factorRH Factor Rhesus (Positivo o Negativo).
     */
    public ExamenSangre(String codigo, String nombrePaciente, double costo, TipoSangre tipoSangre, FactorRH factorRH) {
        this.codigo = codigo;
        this.nombrePaciente = nombrePaciente;
        this.costo = costo;
        this.tipoSangre = tipoSangre;
        this.factorRH = factorRH;
    }

    /**
     * Valida las reglas de negocio e invariantes del examen de sangre.
     * 
     * <p>Comprueba que el código y el nombre no sean nulos ni vacíos, que el costo sea
     * estrictamente superior a cero y que los atributos de tipo de sangre y factor RH no sean nulos.</p>
     * 
     * @throws IllegalArgumentException si alguno de los atributos no cumple las condiciones de validez.
     */
    public void validar() {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío");
        }
        if (nombrePaciente == null || nombrePaciente.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del paciente no puede estar vacío");
        }
        if (costo <= 0) {
            throw new IllegalArgumentException("El costo debe ser mayor a 0");
        }
        if (tipoSangre == null) {
            throw new IllegalArgumentException("El tipo de sangre no puede ser nulo");
        }
        if (factorRH == null) {
            throw new IllegalArgumentException("El factor RH no puede ser nulo");
        }
    }

    /**
     * Retorna una representación formateada del examen de sangre.
     * 
     * <p>El formato estandarizado generado es: {@code "CODIGO | PACIENTE | COSTO | TIPO(+/-)"},
     * donde el factor positivo se mapea a '+' y el negativo a '-'.</p>
     * 
     * @return Cadena con el formato legible del examen.
     */
    @Override
    public String toString() {
        String signoRh = (factorRH == FactorRH.POSITIVO) ? "+" : "-";
        return codigo + " | " + nombrePaciente + " | " + costo + " | " + tipoSangre + signoRh;
    }
}
