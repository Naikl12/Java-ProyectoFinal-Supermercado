package main.java.Controladores;

import main.java.DAO.ArticulosDao;
import main.java.DAO.CarritoDao;
import main.java.Modelos.Articulo;
import main.java.Modelos.Carrito;

import java.lang.reflect.GenericArrayType;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ControlUsuarios {
    Scanner sc = new Scanner(System.in);
    int x;
    String srtCarrito = new String();
    boolean salida = false;
    ArticulosDao ad = new ArticulosDao();
    List<Articulo> l = ad.listaProductos();

    public ControlUsuarios() throws SQLException {
    }

    public void inicio(int xid) throws SQLException {

        while (!salida){

            pintaMenu();

            if (x ==1){
                boolean finCarrito = false;
                while (!finCarrito) {



                    if(elegirProducto(xid)){
                        finCarrito=true;
                    }





                }


            }


            if (x == 2) salida = true;
        }


    }


    public void pintaMenu(){
        System.out.println(" ***************************  Menu CLIENTE   ******************** ");
        System.out.println("");
        System.out.println("1 - Iniciar Carrito de Compras");
        System.out.println("2 - Salir");
        System.out.println("--------------------------------------------------" );
        System.out.print("Selecciones una Opcion: ");
        x = sc.nextInt();



    }


    private void mostrarLista() throws SQLException {

        if(!l.isEmpty()){
            System.out.println("aca deberia ir una lista de productos");

            System.out.println("---------------------------------------------------------");
            for (Articulo list: l) {
                if (!(list.getStock()==0)) {
                    System.out.println(list.toString());
                    System.out.println("--    ---      --     --    --   ---     ---   --    -- ");
                }
            }
            System.out.println("-----------------------------------------------------------");


        }else System.out.println("No hay productos para mostrar");
    }


    private boolean elegirProducto(int clienteId) throws SQLException {

        int indice = 0;
        int vfp = 0;
        Carrito car;
        List<Carrito> carritoTotal = new ArrayList<>();
        boolean sigue = false;


        while(!sigue) {
            boolean idOk = false;
            boolean resta = false;
            car = new Carrito();
            mostrarLista();
            while (!idOk) {
                System.out.print("Elija el ID del Producto :");
                int x1 = sc.nextInt();
                for (int i = 0; i <= l.size() - 1; i++) {
                    if (l.get(i).getId() == x1) {
                        indice = i;
                        idOk = true;
                        break;
                    }
                    if (i == l.size() - 1) {
                        System.out.println("NO esta el producto en la lista...");
                    }
                }

            }


            while (!resta) {
                System.out.print("Selecciones Cantidad del Producto : ");
                int x2 = sc.nextInt();
                if (x2 > l.get(indice).getStock()) {
                    System.out.println("NO alcanza pue... Pedi menos..");
                } else {
                    l.get(indice).setStock(l.get(indice).getStock() - x2);
                    car.setId(0);
                    car.setCantidad(x2);
                    car.setSubtotal(x2 * (l.get(indice).getPrecio()));
                    car.setIdArticulo(l.get(indice).getId());
                    car.setIdCliente(clienteId);
                    car.setPreciounit(l.get(indice).getPrecio());
                    carritoTotal.add(car);
                    System.out.println(carritoTotal.size());
                    resta = true;
                }
            }


            System.out.print("Finalizar Carrito?? (s/n)  ");
            srtCarrito = sc.next();
            if (srtCarrito.equalsIgnoreCase("s")) {
                //System.out.println(carritoTotal.size());

                vfp = eligeFP();
                System.out.println(vfp);
                CarritoDao cdao = new CarritoDao();
                System.out.println(clienteId);
                if (cdao.guardarCarrito(carritoTotal, clienteId, vfp)){
                    System.out.println("Se guardo el carrito con EXITO en BBDD");
                }
                if(ad.actualizaDesdeCarrito(l)){
                    System.out.println("Se Actualizo el Stock con exito.....");
                }



                sigue = true;
            }
        }

        return true;
    }

    private int eligeFP(){
        boolean band= false;
        while(!band) {
            System.out.println("Elija una forma de Pago ...!!");
            System.out.println("1-Debito   | 2-Credito Visa  | 3- Credito Mastercard | 4 - Que dios se lo pague ");
            System.out.print("Ingrese una Opcion: ");
            x = sc.nextInt();
            if (x == 1 || x == 2 || x == 3 || x == 4) band = true;
        }

        return x;
    }

}



