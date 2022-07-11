package main.java.DAO;



import main.java.Conexion.Conexion;
import main.java.Modelos.Articulo;
import main.java.Modelos.Usuario;
import main.java.Modelos.UsuarioConexion;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosDao {
    private Connection conn;
    private PreparedStatement statement;
    private boolean estadoOperacion;
    UsuarioConexion usuc;

    public UsuariosDao() { }

    // Verifica si esxiste el Usuario Cliente en la BBDD  *****************************************
    public UsuarioConexion loginCLiente(Usuario u) throws SQLException {
        String sql = null;
        ResultSet rs = null;
        estadoOperacion = false;
        conn = obtenerConexion();


        try {
            sql = "SELECT * FROM usuarios WHERE nombre =? AND pass = ? AND es_admin = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, u.getNombre());
            statement.setString(2,u.getPass());
            statement.setInt(3,1);   // 1 para cliente

            rs = statement.executeQuery();
            if (rs.next()){
                estadoOperacion = true;
                usuc = new UsuarioConexion(estadoOperacion, rs.getInt(1));

            }else{
                estadoOperacion = false;
                usuc = new UsuarioConexion(estadoOperacion, 0);
            }



        }catch (SQLException e){
            e.printStackTrace();
        }
        statement.close();
        conn.close();


    return usuc;
    }

    // Verifica si existe el ADMINISTRADOR  en la BBDD  *****************************************
    public boolean loginAdmin(Usuario u) throws SQLException {
        String sql = null;
        ResultSet rs = null;
        estadoOperacion = false;
        conn = obtenerConexion();


        try {
            sql = "SELECT * FROM usuarios WHERE nombre =? AND pass = ? AND es_admin = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, u.getNombre());
            statement.setString(2,u.getPass());
            statement.setInt(3,2); //// 2 para administrador

            rs = statement.executeQuery();
            estadoOperacion = rs.next();



        }catch (SQLException e){
            e.printStackTrace();
        }
        statement.close();
        conn.close();


        return estadoOperacion;
    }




    //// guarda en la BBDD el usuario que se esta ingresando como nuevo
    public boolean guardarUsario(Usuario c) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        conn = obtenerConexion();


        try {
            conn.setAutoCommit(false);
            sql = "INSERT INTO usuarios (nombre, apellido, dni, domicilio, correo, pass, es_frecuente, es_admin) VALUES(?,?,?,?,?,?,?,?)";
            statement = conn.prepareStatement(sql);


            statement.setString(1, c.getNombre());
            statement.setString(2, c.getApellido());
            statement.setInt(3, c.getDni());
            statement.setString(4, c.getDomicilio());
            statement.setString(5, c.getCorreo());
            statement.setString(6, c.getPass());
            statement.setInt(7, c.getEsFrecuente());
            statement.setInt(8, c.getEsAdmin());

            estadoOperacion = statement.executeUpdate() > 0;

            conn.commit();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;


    }
    
    public Usuario encontrarUsuario(int idBuscado) throws SQLException{
    	
    	ResultSet rs = null;
        Usuario usuario = new Usuario();
        String sql = null;
        estadoOperacion = false;
        conn = obtenerConexion();

        try {
            sql = "SELECT * FROM usuarios WHERE id =?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idBuscado);
            rs = statement.executeQuery();
            if (rs.next()){
            	usuario.setId(idBuscado);
            	usuario.setNombre(rs.getString(2));
            	usuario.setApellido(rs.getString(3));
            	usuario.setDni(rs.getInt(4));
            	usuario.setDomicilio(rs.getString(5));
            	usuario.setCorreo(rs.getString(6));
            	usuario.setEsFrecuente(rs.getInt(7));             
                

            }else{
                usuario = null;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return usuario;
    	
    	
    }
    
    // obtener conexion pool
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }
}
