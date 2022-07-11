package main.java.Modelos;

import java.util.Objects;

public class Articulo {
    private int id;
    private String nombre;
    private int idRubro;
    private int stock;
    private double precio;

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    private String rubro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(int idRubro) {
        this.idRubro = idRubro;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return  id +
                ", nombre ->'" + nombre + '\'' +
                ", Rubro->" + rubro +
                ", stock->" + stock +
                ", precio->" + precio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Articulo articulo = (Articulo) o;
        return id == articulo.id && idRubro == articulo.idRubro && stock == articulo.stock && Double.compare(articulo.precio, precio) == 0 && Objects.equals(nombre, articulo.nombre) && Objects.equals(rubro, articulo.rubro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, idRubro, stock, precio, rubro);
    }
}
