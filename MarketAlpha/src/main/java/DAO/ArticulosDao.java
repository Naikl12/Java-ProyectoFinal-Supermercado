package main.java.DAO;


import main.java.Conexion.Conexion;
import main.java.Modelos.Articulo;
import main.java.Modelos.Carrito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticulosDao {
    private Connection conn;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    public ArticulosDao(){}


    /// guarda y actualiza articulos desde el AdMinistrador ********************************************
    public boolean guardarArticulo(Articulo art, int tipoOperacion) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        conn = obtenerConexion();


        try {
            conn.setAutoCommit(false);


            if (tipoOperacion ==1) {
                sql = "INSERT INTO articulos (id, nombre, id_rubro, stock, precio) VALUES(?,?,?,?,?)";
            }else{
                sql = "UPDATE articulos SET nombre=?, id_rubro=?, stock=?, precio=? WHERE id=?";

            }
            statement = conn.prepareStatement(sql);
            if (tipoOperacion == 1){  //// si es 1 es un nuevo articulo +++++++++++
                statement.setInt(1, 0);
                statement.setString(2, art.getNombre());
                statement.setInt(3, art.getIdRubro());
                statement.setInt(4, art.getStock());
                statement.setDouble(5, art.getPrecio());

                // si el x es igual a 2 es porque viene de una modificacion *****************
            }else{
                statement.setString(1, art.getNombre());
                statement.setInt(2, art.getIdRubro());
                statement.setInt(3, art.getStock());
                statement.setDouble(4, art.getPrecio());
                statement.setInt(5, art.getId());
            }


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

    public Articulo buscaArticulo(int idBuscado) throws SQLException {
        ResultSet rs = null;
        Articulo art = new Articulo();
        String sql = null;
        estadoOperacion = false;
        conn = obtenerConexion();

        try {
            sql = "SELECT * FROM articulos WHERE id =?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idBuscado);
            rs = statement.executeQuery();
            if (rs.next()){
                art.setId(idBuscado);
                art.setNombre(rs.getString(2));
                art.setIdRubro(rs.getInt(3));
                art.setStock(rs.getInt(4));
                art.setPrecio(rs.getDouble(5));


            }else{
                art = null;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return art;


    }

    public List<Articulo> listaProductos() throws SQLException {
        ResultSet resultSet = null;
        List<Articulo> listaProductos= new ArrayList<>();

        String sql = null;
        estadoOperacion = false;
        conn = obtenerConexion();

        try {
            sql = "SELECT articulos.id, articulos.nombre, articulos.precio, articulos.stock, rubro.descripcion FROM articulos INNER JOIN rubro ON articulos.id_rubro = rubro.id";
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Articulo articulo=new Articulo();
                articulo.setId(resultSet.getInt(1));
                articulo.setNombre(resultSet.getString(2));
                articulo.setPrecio(resultSet.getDouble(3));
                articulo.setStock(resultSet.getInt(4));
                articulo.setRubro(resultSet.getString(5));

                listaProductos.add(articulo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaProductos;
    }

    // actualiza los productos despues de Cerrar la compra en el Carrito Usuario ********************
    public boolean actualizaDesdeCarrito(List<Articulo> listado  ) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        conn = obtenerConexion();
        System.out.println("entro listado");
        System.out.println(listado.get(1).toString());
        try {
            conn.setAutoCommit(false);
            for (Articulo art: listado) {


                sql = "UPDATE articulos SET  stock=? WHERE id=?";

                statement = conn.prepareStatement(sql);


                statement.setInt(1, art.getStock());

                statement.setInt(2, art.getId());
                estadoOperacion = statement.executeUpdate() > 0;
            }

            } catch (SQLException ex) {
               ex.printStackTrace();

        }
            conn.commit();
            statement.close();
            conn.close();


        return estadoOperacion;




    }



    // obtener conexion pool
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }
}
