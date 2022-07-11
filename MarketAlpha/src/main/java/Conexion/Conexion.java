package main.java.Conexion;
import java.sql.*;


public class Conexion {
    final String JdbDriver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/supermercado";
    static final String user = "root";
    static final String pass = "root";


    public void Conectando() {

        Connection conn = null;
        Statement st = null;
        //Class.forName(JdbDriver);
        try {
            System.out.println("Conectando a base de datos");
            conn = DriverManager.getConnection(url, user, pass);
            conn.close();

        }catch (SQLException se){
            System.out.println("no se pudo conectar..");
        }


    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
