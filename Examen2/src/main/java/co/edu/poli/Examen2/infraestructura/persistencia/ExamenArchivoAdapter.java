package co.edu.poli.Examen2.infraestructura.persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import co.edu.poli.Examen2.aplicacion.puerto.salida.DeserializarExamenesPort;
import co.edu.poli.Examen2.aplicacion.puerto.salida.PersistenciaException;
import co.edu.poli.Examen2.aplicacion.puerto.salida.SerializarExamenesPort;
import co.edu.poli.Examen2.dominio.modelo.ExamenSangre;

/**
 * Adaptador de salida para la persistencia binaria de exámenes mediante serialización de objetos Java en disco.
 * 
 * <p>Implementa {@link SerializarExamenesPort} y {@link DeserializarExamenesPort} utilizando flujos
 * {@link ObjectOutputStream} y {@link ObjectInputStream} con gestión automática de recursos (try-with-resources).</p>
 * 
 * @author Sistema de Laboratorio Clínico
 * @version 1.0
 */
public class ExamenArchivoAdapter implements SerializarExamenesPort, DeserializarExamenesPort {

    /** Ruta del archivo físico de almacenamiento binario (ej. "examenes.ser"). */
    public String ruta;

    /**
     * Construye el adaptador asociándolo a la ruta de archivo especificada.
     * 
     * @param ruta Nombre o ruta absoluta/relativa del archivo binario.
     */
    public ExamenArchivoAdapter(String ruta) {
        this.ruta = ruta;
    }

    /**
     * Serializa y escribe la lista de exámenes en el archivo binario configurado.
     * 
     * @param examenes Lista de exámenes a escribir.
     * @throws PersistenciaException si ocurre algún error de entrada/salida.
     */
    @Override
    public void escribir(List<ExamenSangre> examenes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(examenes);
        } catch (IOException e) {
            throw new PersistenciaException("Error al serializar exámenes en el archivo: " + ruta, e);
        }
    }

    /**
     * Lee y deserializa la lista de exámenes desde el archivo binario configurado.
     * 
     * @return Lista de exámenes leídos del archivo.
     * @throws PersistenciaException si el archivo no existe, no puede leerse o la clase no coincide.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<ExamenSangre> leer() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (List<ExamenSangre>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new PersistenciaException("Error al deserializar exámenes desde el archivo: " + ruta, e);
        }
    }
}
