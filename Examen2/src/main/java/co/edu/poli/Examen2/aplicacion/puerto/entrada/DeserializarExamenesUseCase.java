package co.edu.poli.Examen2.aplicacion.puerto.entrada;

/**
 * Puerto de entrada primario para el caso de uso de deserialización y restauración de exámenes.
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public interface DeserializarExamenesUseCase {

    /**
     * Lee y reconstruye la lista de exámenes desde el almacenamiento secundario y la carga en memoria.
     * 
     * @return Cantidad de exámenes cargados y restaurados en el sistema.
     * @throws co.edu.poli.Examen2.aplicacion.puerto.salida.PersistenciaException si ocurre un fallo al leer el archivo.
     */
    int deserializar();
}
