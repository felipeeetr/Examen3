package co.edu.poli.Examen2.aplicacion.puerto.entrada;

import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Puerto de entrada primario para el caso de uso de registro y guardado de exámenes de sangre.
 * 
 * <p>Expone la operación de negocio requerida por los adaptadores de entrada (interfaces de usuario o APIs)
 * para almacenar un examen en el sistema.</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public interface GuardarExamenUseCase {

    /**
     * Procesa, valida y guarda un examen de sangre en el sistema.
     * 
     * @param examen Examen de sangre a registrar.
     * @throws IllegalArgumentException si los datos del examen son inválidos.
     * @throws IllegalStateException si ya existe un examen con el mismo código.
     */
    void guardar(ExamenSangre examen);
}
