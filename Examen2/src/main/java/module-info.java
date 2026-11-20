module co.edu.poli.Examen2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.poli.Examen2.infraestructura.ui to javafx.fxml;

    exports co.edu.poli.Examen2.vista;
    exports co.edu.poli.Examen2.dominio.modelo;
    exports co.edu.poli.Examen2.aplicacion.puerto.entrada;
    exports co.edu.poli.Examen2.aplicacion.puerto.salida;
    exports co.edu.poli.Examen2.aplicacion.servicio;
    exports co.edu.poli.Examen2.infraestructura.ui;
    exports co.edu.poli.Examen2.infraestructura.persistencia;
}
