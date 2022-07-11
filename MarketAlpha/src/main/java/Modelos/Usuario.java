package main.java.Modelos;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private int dni;
    private String domicilio;
    private String correo;
    private int esFrecuente;
    private int esAdmin;
    private String pass;


    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", correo='" + correo + '\'' +
                ", esFrecuente='" + esFrecuente + '\'' +
                ", esAdmin='" + esAdmin + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }


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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEsFrecuente() {
        return esFrecuente;
    }

    public void setEsFrecuente(int esFrecuente) {
        this.esFrecuente = esFrecuente;
    }

    public int getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(int esAdmin) {
        this.esAdmin = esAdmin;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}


