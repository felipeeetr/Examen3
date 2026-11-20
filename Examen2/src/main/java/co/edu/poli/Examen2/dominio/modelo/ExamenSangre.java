package co.edu.poli.Examen2.dominio.modelo;

import java.io.Serializable;

/** Modelo de dominio que representa un examen de sangre. */
public class ExamenSangre implements Serializable {
    public static final long serialVersionUID = 1L;

    public String codigo;
    public String nombrePaciente;
    public double costo;
    public TipoSangre tipoSangre;
    public FactorRH factorRH;

    public ExamenSangre(String codigo, String nombrePaciente, double costo, TipoSangre tipoSangre, FactorRH factorRH) {
        this.codigo = codigo;
        this.nombrePaciente = nombrePaciente;
        this.costo = costo;
        this.tipoSangre = tipoSangre;
        this.factorRH = factorRH;
    }

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

    @Override
    public String toString() {
        String signoRh = (factorRH == FactorRH.POSITIVO) ? "+" : "-";
        return codigo + " | " + nombrePaciente + " | " + costo + " | " + tipoSangre + signoRh;
    }
}

