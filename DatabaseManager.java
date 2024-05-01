import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost/task_manager";
    private static final String DB_USER = "root"; // Utilisateur par défaut de MySQL
    private static final String DB_PASSWORD = ""; // Mot de passe par défaut de MySQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Méthodes pour les opérations CRUD
}
