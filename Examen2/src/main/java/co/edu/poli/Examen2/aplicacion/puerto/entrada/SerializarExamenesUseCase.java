package co.edu.poli.Examen2.aplicacion.puerto.entrada;

/**
 * Puerto de entrada primario para el caso de uso de serialización de exámenes hacia almacenamiento externo.
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public interface SerializarExamenesUseCase {

    /**
     * Serializa los exámenes actualmente disponibles en memoria hacia el medio de persistencia configurado.
     * 
     * @return Cantidad de exámenes que fueron serializados exitosamente.
     * @throws co.edu.poli.Examen2.aplicacion.puerto.salida.PersistenciaException si ocurre un error de E/S.
     */
    int serializar();
}
