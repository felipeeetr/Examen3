package co.edu.poli.Examen2.aplicacion.puerto.salida;

/**
 * Excepción en tiempo de ejecución lanzada ante fallos en los adaptadores de persistencia.
 * 
 * <p>Permite a los adaptadores de salida envolver excepciones comprobadas como {@link java.io.IOException}
 * o {@link ClassNotFoundException} sin contaminar las interfaces de puerto ni el dominio con dependencias técnicas.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class PersistenciaException extends RuntimeException {

    /**
     * Construye una nueva excepción con el mensaje explicativo y la causa original del fallo.
     * 
     * @param mensaje Detalle del error de persistencia.
     * @param causa Excepción original subyacente.
     */
    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
