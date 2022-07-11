package main.java.DAO;
import main.java.Conexion.Conexion;
import main.java.Modelos.Articulo;
import main.java.Modelos.Carrito;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarritoDao {
    private Connection conn;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    public boolean guardarCarrito(List<Carrito> carritoListado, int clienteid, int clientefp) throws SQLException {
        String sql = null;
        double sub;
        estadoOperacion = false;
        conn = obtenerConexion();
        java.util.Date fecha1 = new java.util.Date();
        java.sql.Date fecha2;
        long fechamiliseg;
        try {
            conn.setAutoCommit(false);

            for (Carrito c: carritoListado) {

                /// id 	id_cliente 	fecha 	id_articulo 	cantidad 	subtotal 	id_fp 	estado
                sql = "INSERT INTO carritocompras (id, id_cliente, fecha , id_articulo, cantidad, subtotal, id_fp, estado) VALUES(?,?,?,?,?,?,?,?)";

                statement = conn.prepareStatement(sql);

                statement.setInt(1, 0);
                statement.setInt(2,clienteid );
                fechamiliseg = fecha1.getTime();
                fecha2 = new java.sql.Date(fechamiliseg);


                statement.setDate(3, (Date) fecha2);
                statement.setInt(4, c.getIdArticulo());
                statement.setInt(5, c.getCantidad());
                sub = c.getCantidad() * c.getPreciounit();
                statement.setDouble(6,sub);
                statement.setInt(7, clientefp);
                if(clientefp == 1 || clientefp ==2 || clientefp == 3){
                    statement.setString(8, "Pagado");
                }else{
                    statement.setString(8, "MOROSO");
                }

                estadoOperacion = statement.executeUpdate() > 0;
                conn.commit();
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }




        return estadoOperacion;
    }
    
    public List<Carrito> ListaCarritos() throws SQLException {
    	ResultSet resultSet = null;
        List<Carrito> listaCarritos= new ArrayList<>();
    	
        String sql = null;
        estadoOperacion = false;
        conn = obtenerConexion();
        
        try {
            sql = "select carritocompras.id, carritocompras.id_cliente, carritocompras.fecha, carritocompras.subtotal, carritocompras.estado from carritocompras";
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Carrito carrito=new Carrito();
                carrito.setId(resultSet.getInt(1));
                carrito.setIdCliente(resultSet.getInt(2));
                carrito.setFecha(resultSet.getDate(3));
                carrito.setSubtotal(resultSet.getDouble(4));
                carrito.setEstado(resultSet.getString(5));

                listaCarritos.add(carrito);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    	
    	return listaCarritos;
    }



    // obtener conexion pool
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }
}
