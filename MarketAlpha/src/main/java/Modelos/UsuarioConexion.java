package main.java.Modelos;

public class UsuarioConexion {
    private boolean estado;
    private int idUsuario;

    public UsuarioConexion() {
    }

    public UsuarioConexion(boolean estado, int idUsuario) {
        this.estado = estado;
        this.idUsuario = idUsuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
