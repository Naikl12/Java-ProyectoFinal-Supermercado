package main.java.Servicios;



/*Proyecto Final Mil Programadores : SuperMercado
autores; Naira Leal, Juan Serrano, Enzo Valverde*/


import main.java.Controladores.ControlAdmin;
import main.java.Controladores.ControlUsuarios;
import main.java.DAO.UsuariosDao;
import main.java.Modelos.Usuario;
import main.java.Modelos.UsuarioConexion;

import java.sql.SQLException;
import java.util.Scanner;




public class Inicio {
    String opcion;
    Scanner op = new Scanner(System.in);
    boolean salida = false;
    Usuario cn;
    ControlAdmin ca = new ControlAdmin();
    ControlUsuarios cu = new ControlUsuarios();
    UsuarioConexion uc = new UsuarioConexion();

    public Inicio() throws SQLException {
    }


    public void comenzando() throws SQLException {
        while(!salida){
            PintaMenu();

            if(EsNumero(opcion)){
                int num = Integer.parseInt(opcion);
                UsuariosServicio cs = new UsuariosServicio();  //// creamos un objeto de servicio para acceder a todos los menues
                UsuariosDao cd = new UsuariosDao(); // creamos un objeto usuarioDao para las operaciones con la BBDD


                ///////// opcion de Cliente regular
                if (num == 1){
                    cn = cs.Ingreso(num);
                    uc = cd.loginCLiente(cn);

                    if(uc.isEstado()){
                        System.out.println("BIENVENIDO------------------");
                        System.out.println("----------------------");
                        System.out.println("");
                        cu.inicio(uc.getIdUsuario());   /// ejecuta el metodo INICIO del ControladorUsuario



                    }else{
                        System.out.println("Usuario o contraseña incorrectos...");
                        System.out.println("----------------------");
                        System.out.println("");
                    }


                }

                ///////// opcion de Administrador
                if (num == 2 ){
                    cn = cs.Ingreso(num);
                    if(cd.loginAdmin(cn)){
                        System.out.println("BIENVENIDO !!! ***************");
                        System.out.println("----------------------");
                        System.out.println("");
                        ca.inicio();   //// Ejecuta el Inicio del controladorAdmin





                    }else{
                        System.out.println("Usuario o contraseña incorrectos...");
                        System.out.println("----------------------");
                        System.out.println("");
                    }
                }

                ///////// Crear nuevo Cliente Regular
                if (num == 3){

                    cn = cs.Nuevo();

                    if (cd.guardarUsario(cn)){
                        System.out.println("Cliente Guardado con Exito");
                    }else{
                        System.out.println("No se pudo guardar el Cliente");
                    }

                    System.out.println(cn.toString());
                }

                /// Salida del menu Principal
                if (num == 4){
                    salida = true;
                }

            }
        }

    }

    private void PintaMenu() {
        System.out.println("******************");
        System.out.println("Bienvenido a SuPerMarket ALPHA");
        System.out.println("******************");
        System.out.println("1 - Ingreso de Usuario");
        System.out.println("2 - Ingreso de Administrador");
        System.out.println("3 - Registro Nuevo Usuario");
        System.out.println("4 - Salida");
        System.out.println("");
        System.out.println("");
        System.out.print("Ingrese su opcion: ");
        opcion = op.next();

    }




    private static boolean EsNumero(String x){
        try{
            Integer.parseInt(x);
            return true;
        }catch (NumberFormatException nfe) {
            return false;
        }

    }

}
