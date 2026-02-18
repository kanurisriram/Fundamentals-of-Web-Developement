import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/fwd_system",
            "root",
            ""   // password if set
        );
    }
}
