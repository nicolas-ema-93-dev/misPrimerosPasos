package viborita;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class BaseDeDatos {

    // Esto crea automáticamente un archivo llamado "puntajes.db" en la carpeta de tu proyecto
    private static final String URL = "jdbc:sqlite:puntajes.db";

    // Método para inicializar la base de datos y crear la tabla si no existe
    public static void inicializarBD() {
        String sql = "CREATE TABLE IF NOT EXISTS puntajes (\n"
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "    nombre TEXT NOT NULL,\n"
                + "    puntaje INTEGER NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Base de datos y tabla 'puntajes' listas.");
        } catch (SQLException e) {
            System.out.println("Error al crear la base de datos: " + e.getMessage());
        }
    }

    // Método para guardar un nuevo puntaje
    public static void guardarPuntaje(String nombre, int puntaje) {
        String sql = "INSERT INTO puntajes(nombre, puntaje) VALUES(?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, puntaje);
            pstmt.executeUpdate();
            System.out.println("¡Puntaje guardado con éxito!");
        } catch (SQLException e) {
            System.out.println("Error al guardar el puntaje: " + e.getMessage());
        }

    }

    public static List<PuntajeRegistro> mostrarTopPuntajes() {
        List<PuntajeRegistro> lista = new ArrayList<>();
        String sql = "SELECT nombre, puntaje FROM puntajes ORDER BY puntaje DESC LIMIT 5";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- TOP 5 MEJORES PUNTAJES ---");
            int puesto = 1;
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int puntaje = rs.getInt("puntaje");
                lista.add(new PuntajeRegistro(nombre,puntaje));
                puesto++;
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los puntajes: " + e.getMessage());
        }
        return lista;
    }
}