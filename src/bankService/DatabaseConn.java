package bankService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConn {
    private static final String URL = "jdbc:postgresql://localhost:5432/bank";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456789";

    private static Connection instance;

    // Constructor privado para evitar instanciación
    private DatabaseConn() { }

    // Método para obtener la instancia única de conexión
    public static Connection getConnection() throws SQLException {
        if (instance == null || instance.isClosed()) {
            synchronized (DatabaseConn.class) {
                if (instance == null || instance.isClosed()) {
                    instance = DriverManager.getConnection(URL, USER, PASSWORD);
                }
            }
        }
        return instance;
    }
}
