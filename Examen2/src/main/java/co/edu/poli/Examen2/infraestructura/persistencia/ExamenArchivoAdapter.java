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

/** Adaptador de salida para la persistencia de exámenes mediante serialización en archivo. */
public class ExamenArchivoAdapter implements SerializarExamenesPort, DeserializarExamenesPort {
    public String ruta;

    public ExamenArchivoAdapter(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public void escribir(List<ExamenSangre> examenes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(examenes);
        } catch (IOException e) {
            throw new PersistenciaException("Error al serializar exámenes en el archivo: " + ruta, e);
        }
    }

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

