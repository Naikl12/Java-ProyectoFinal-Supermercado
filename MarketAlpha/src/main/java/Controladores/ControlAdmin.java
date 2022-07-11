package main.java.Controladores;
import java.util.List;
import java.util.Objects;

import main.java.DAO.ArticulosDao;
import main.java.DAO.CarritoDao;
import main.java.DAO.UsuariosDao;
import main.java.Modelos.Articulo;
import main.java.Modelos.Carrito;
import main.java.Modelos.Usuario;

import java.sql.SQLException;
import java.util.Scanner;

public class ControlAdmin {
   Scanner sc = new Scanner(System.in);
   int x;
   boolean salida = false;
   Articulo articulo = new Articulo();
   ArticulosDao ad = new ArticulosDao();
   CarritoDao carrito = new CarritoDao();

        public void inicio() throws SQLException {
            while (!salida) {
                pintaMenu();

                if (x ==1){
                    articulo = menuCargaArticulo();
                    if (ad.guardarArticulo(articulo, x)){
                        System.out.println(articulo.toString());
                        System.out.println(" se guardo el articulo con EXITO....");
                    }else System.out.println("ERROR al guardar.");



                }
                if (x == 2){
                    articulo = menuBuscaArticulo();
                    if(Objects.nonNull(articulo)){
                        System.out.println("Articulo encontrado...");
                        System.out.println(articulo.toString());
                        Articulo artModificado = menuModificaArticulo(articulo);
                        if (ad.guardarArticulo(artModificado, x)){
                            System.out.println(artModificado.toString());
                            System.out.println(" Producto ACTUALIZADO con total y absoluto EXITO....");
                        }else System.out.println("ERROR al guardar.");


                    }else{
                        System.out.println("No se encontro el Articulo...");
                    }

                }
                
                if (x == 3) {
                	Usuario clienteEncontrado = new Usuario();
                	UsuariosDao cliente = new UsuariosDao();
                	List<Carrito> lista = carrito.ListaCarritos();
                	if(!lista.isEmpty()) {
                		System.out.println("---------------------------------------------------------");
                        for (Carrito list: lista) {
                        	if(list.getEstado().equals("Pagado")) {
                        		clienteEncontrado = cliente.encontrarUsuario(list.getId());
                        		System.out.println(clienteEncontrado.toString());
                                System.out.println(list.toString());
                                System.out.println("--    ---      --     --    --   ---     ---   --    -- ");
                        	}
                        	
                        }
                        System.out.println("-----------------------------------------------------------");
                	}else System.out.println("No hay carrito para mostrar");
                	
                }
                
                if (x == 4) {
                	int usuarioElegido;
                	
                	Usuario clienteEncontrado = new Usuario();
                	UsuariosDao cliente = new UsuariosDao();
                	List<Carrito> lista = carrito.ListaCarritos();
                	if(!lista.isEmpty()) {
                		System.out.println("---------------------------------------------------------");
                        for (Carrito list: lista) {
                        	if(list != null) {
                        		
                            	clienteEncontrado = cliente.encontrarUsuario(list.getId());
                            	System.out.println(clienteEncontrado);
                                System.out.println("--    ---      --     --    --   ---     ---   --    -- ");
                        		
                        	}
                        	
                        	
                        }
                        System.out.println("-----------------------------------------------------------");
                	}else System.out.println("No hay usuarios para mostrar");               	
                	
                	System.out.println("Elije un usuario por su ID");
                	usuarioElegido = sc.nextInt();
                	
                	clienteEncontrado = cliente.encontrarUsuario(usuarioElegido);
                	System.out.println(clienteEncontrado.toString());
                }
                
                

                if (x == 6){
                    List<Articulo> l = ad.listaProductos();
                    if(!l.isEmpty()){
                        System.out.println("aca deberia ir una lista de productos");

                        System.out.println("---------------------------------------------------------");
                        for (Articulo list: l) {
                            System.out.println(list.toString());
                            System.out.println("--    ---      --     --    --   ---     ---   --    -- ");
                        }
                        System.out.println("-----------------------------------------------------------");
                        
                        
                    }else System.out.println("No hay productos para mostrar");
                }
                if (x == 7) salida = true;

            }



        }
        public void pintaMenu(){
            System.out.println(" ***************************  Menu ADMINISTRADOR   ******************** ");
            System.out.println("");
            System.out.println("1 - Cargar Producto");
            System.out.println("2 - Modificar Producto");
            System.out.println("3 - Ver Usuarios que realizaron compras");
            System.out.println("4 - Ver Listado de Producto por Usuario");
            System.out.println("5 - Ver Usuarios que realizaron compras");
            System.out.println("6 - Ver Productos por Stock");
            System.out.println("7 - Salir");
            System.out.println("--------------------------------------------------" );
            System.out.print("Selecciones una Opcion: ");
            x = sc.nextInt();



        }

        private Articulo menuCargaArticulo(){
            System.out.println("");
            System.out.println("**********  Carga de Articulos **************");
            System.out.print("Ingrese nombre del Articulo: ");
            sc.nextLine();
            articulo.setNombre(sc.nextLine());
            System.out.println("Rubros(1-Bebidas | 2-Limpieza | 3-Indumentaria | 4-Golosinas | 5-Farmacia | 6-Comestibles");
            System.out.print("Ingrese un codigo de Rubro: ");
            articulo.setIdRubro(sc.nextInt());
            System.out.print("Ingrese Cantidad (Stock): ");
            articulo.setStock(sc.nextInt());
            System.out.print("Ingrese precio Unitario: " );
            articulo.setPrecio(sc.nextDouble());
            return articulo;
        }

        private Articulo menuBuscaArticulo() throws SQLException {
            System.out.println("");
            System.out.println("***** Ingrese el ID del Articulo a Modificar ***");
            System.out.print("Ingrese id; ");
            int id = sc.nextInt();
            return articulo = ad.buscaArticulo(id);

        }

        private Articulo menuModificaArticulo(Articulo art){
            System.out.println("");
            System.out.println("-------------- Modifique los valores correspondientes-----");
            System.out.print("Stock actual ->"+ art.getStock()  + " nuevo Stock;  ");
            art.setStock(sc.nextInt());
            System.out.print("Precio actual -> "+ art.getPrecio()+ " nuevo Precio: ");
            art.setPrecio(sc.nextDouble());
            System.out.println("---------------------------------");
            return art;
        }




}


