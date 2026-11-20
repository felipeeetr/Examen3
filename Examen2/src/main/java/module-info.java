/**
 * Módulo principal del sistema de gestión de exámenes de sangre.
 * 
 * <p>Configura los módulos requeridos de JavaFX ({@code javafx.controls} y {@code javafx.fxml}),
 * abre el paquete de la interfaz de usuario para permitir la inyección reflectiva de FXML,
 * y exporta todos los paquetes de dominio, aplicación e infraestructura.</p>
 */
module co.edu.poli.Examen2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.poli.Examen2.infraestructura.ui to javafx.fxml;

    exports co.edu.poli.Examen2.vista;
    exports co.edu.poli.Examen2.dominio.modelo;
    exports co.edu.poli.Examen2.dominio.constructor;
    exports co.edu.poli.Examen2.dominio.costo;
    exports co.edu.poli.Examen2.aplicacion.puerto.entrada;
    exports co.edu.poli.Examen2.aplicacion.puerto.salida;
    exports co.edu.poli.Examen2.aplicacion.servicio;
    exports co.edu.poli.Examen2.aplicacion.fachada;
    exports co.edu.poli.Examen2.infraestructura.ui;
    exports co.edu.poli.Examen2.infraestructura.persistencia;
}
