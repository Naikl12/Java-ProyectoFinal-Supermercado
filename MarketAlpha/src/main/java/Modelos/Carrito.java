package main.java.Modelos;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Carrito {
    private int id;
    private int idCliente;
    private Date fecha;
    private int idArticulo;
    private int cantidad;
    private Double subtotal;
    private int idFp;
    private String estado;
    private double preciounit;

    //LocalDate ld = LocalDate.now();  //Creo que con esta funcion guarda el formato fecha en BBDD



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public int getIdFp() {
        return idFp;
    }

    public void setIdFp(int idFp) {
        this.idFp = idFp;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPreciounit() {
        return preciounit;
    }

    public void setPreciounit(double preciounit) {
        this.preciounit = preciounit;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", fecha=" + fecha +
                ", idArticulo=" + idArticulo +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", idFp=" + idFp +
                ", estado='" + estado + '\'' +

                '}';
    }


}
