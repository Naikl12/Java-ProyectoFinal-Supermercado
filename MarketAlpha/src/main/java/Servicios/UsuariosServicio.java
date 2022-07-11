package main.java.Servicios;


import main.java.Modelos.Usuario;

import java.util.Scanner;


public class UsuariosServicio {
    Scanner sc = new Scanner(System.in);
    Usuario cliente = new Usuario();

    public Usuario Nuevo(){
        PintaMenuNU();
        return cliente;
    }

    public Usuario Ingreso(int tipoIngreso){

        if (tipoIngreso == 1){
            System.out.println("*********  Log-In de Usuario   ********");
        }else{
            System.out.println("*********  Log-In de ADMINISTRADOR   ********");
        }

        System.out.print("** Ingrese Nombre:  ");
        cliente.setNombre(sc.next());
        System.out.print("** Ingrese su contraseña:  ");
        cliente.setPass(sc.next());
        System.out.println("-----------------");
        return cliente;
    }








    public Usuario IngresoAdm(){
        System.out.println("*********  Log-In de Administrador   ********");
        System.out.print("** Ingrese Nombre:  ");
        cliente.setNombre(sc.next());
        System.out.print("** Ingrese su contraseña:  ");
        cliente.setPass(sc.next());
        cliente.setEsAdmin(2); //// establecemos el campo en 2 porque es un usuario Administrador
        return cliente;
    }

    private void PintaMenuNU(){     ///////// Registro de Usuario ////////////////////////
        System.out.println("*********   Registro de Nuevo Usuario   ********");
        System.out.print("** Ingrese Nombre:  ");
        cliente.setNombre(sc.next());
        System.out.print("** Ingrese Apellido:  ");
        cliente.setApellido(sc.next());
        System.out.print("** Ingrese DNI:  ");
        cliente.setDni(sc.nextInt());
        System.out.print("** Ingrese Correo:  ");
        cliente.setCorreo(sc.next());
        System.out.print("** Ingrese Domicilio:  ");
        cliente.setDomicilio(sc.next());
        System.out.print("** Ingrese Contraseña:  ");
        cliente.setPass(sc.next());

        /// agregamos los datos para crear un cliente que se pueda ingresar en la base de datos

        cliente.setEsFrecuente(1); // valor 1 para "noEsfrecuente" , 2 para "SiesFrecuente"
        cliente.setEsAdmin(1); // valor 1 para "ClienteComun" , 2 para Administrador



    }
}
