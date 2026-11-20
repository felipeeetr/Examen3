package co.edu.poli.Examen2.aplicacion.puerto.salida;

/** Excepción lanzada al ocurrir fallas durante la persistencia de datos. */
public class PersistenciaException extends RuntimeException {
    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

