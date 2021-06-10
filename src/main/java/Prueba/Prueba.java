/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import Almacen.Pedidos;
import clientes.Clientes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import productos.Articulos;
import productos.Productos;
import productos.Servicios;

/**
 *
 * @author Jose Antonio
 */
public class Prueba {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() { //original
        //clientes
        Clientes cliente = new Clientes();
        ArrayList<Clientes> listaClientes = cliente.leerCliente("clientes.csv");
        //productos
        Articulos articulo = new Articulos();
        Servicios servicio = new Servicios();
        ArrayList<Articulos> listaArticulos = articulo.leerArticulo("articulos.csv");
        ArrayList<Servicios> listaServicios = servicio.leerServicio("servicios.csv");
        ArrayList<Productos> listaProductos = new ArrayList<>();
        //for para meter los articulos en la lista de productos        
        for (Productos producto : listaArticulos) {
            listaProductos.add(producto);
        }
        //for para meter los servicios en la lista de productos
        for (Productos producto : listaServicios) {
            listaProductos.add(producto);
        }
        //Pedidos
        ArrayList<Pedidos> listaPedidos = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        int menu = 0;
        do {
            do {
                System.out.println("\n");
                System.out.println("1.Consultar/modificar/añadir/borrar clientes\n"
                        + "2.Consultar/modificar/añadir/borrar productos\n"
                        + "3.Consultar/modificar/añadir/borrar pedidos\n"
                        + "4.Imprimir pedido\n"
                        + "5.Generar copia de seguridad\n"
                        + "6.Restaurar copia de seguridad\n"
                        + "7.Salir");
                System.out.println("Introduzca el numero del menu:");
                menu = teclado.nextInt();
            } while (menu < 1 || menu > 7);

            //switch para entrar en el menu que se ha seleccionado anteriormente
            switch (menu) {
                case 1:
                    clientes(listaClientes);
                    break;
                case 2:
                    productos(listaProductos);
                    break;
            }
        } while (menu != 7);

    }//menu

    public static void clientes(ArrayList<Clientes> listaClientes) {
        //Clientes
        Scanner teclado = new Scanner(System.in);

        int menuClientes = 0;
        //do while para filtrar las operaciones
        do {
            System.out.println("¿Que operacion desea realizar?\n"
                    + "1.Consultar\n2.Modificar\n3.Añadir\n4.Borrar");
            menuClientes = teclado.nextInt();
        } while (menuClientes < 1 || menuClientes > 4);

        //switch para realizar las operaciones elegidas anteriormente
        switch (menuClientes) {
            case 1:
                System.out.println("Ha elegido consultar");
                //for para imprimir los clientes
                for (Clientes c : listaClientes) {
                    System.out.println(c);
                }
                break;
            case 2:
                System.out.println("Ha elegido modificar");
                //for para imprimir los clientes
                for (Clientes c : listaClientes) {
                    System.out.println(c);
                }
                System.out.println("¿Que clientes de los anteriores quiere "
                        + "modificar?");
                teclado.nextLine();
                String nifCliente = teclado.nextLine();

                int comprobacion = 0;
                //for para modificar el cliente
                for (Clientes c : listaClientes) {
                    //if para comprobar el nombre del cliente
                    if (nifCliente.equals(c.getNifCliente())) {
                        //se modifica el nombre, apellidos y direccion
                        System.out.println("Introduce el nombre:");
                        String nombre = teclado.nextLine();
                        c.setNombre(nombre);

                        System.out.println("Introduce los apellidos:");
                        String apellidos = teclado.nextLine();
                        c.setApellidos(apellidos);

                        System.out.println("Introduce el nif:");
                        String nif = teclado.nextLine();
                        c.setNifCliente(nif);

                        System.out.println("Introduce la direccion:");
                        String direccion = teclado.nextLine();
                        c.setDireccion(direccion);
                        comprobacion = 1;
                    }
                }
                if (comprobacion == 0) {
                    System.out.println("No se ha encontrado el cliente");
                }
                break;
            case 3:
                System.out.println("Ha elegido añadir");
                teclado.nextLine();
                System.out.println("Introduce el nombre:");
                String nombre = teclado.nextLine();
                System.out.println("Introduce los apellidos:");
                String apellidos = teclado.nextLine();
                System.out.println("Introduce el nif:");
                String nif = teclado.nextLine();
                System.out.println("Introduce la direccion:");
                String direccion = teclado.nextLine();

                int nifExist = 0;
                //for para comprobar si el dni a meter ya existe
                for (Clientes cli : listaClientes) {
                    if (nif.equals(cli.getNifCliente())) {
                        nifExist = 1;
                    }
                }
                //if para meter el cliente en caso de que el nif no exista
                if (nifExist == 0) {
                    listaClientes.add(new Clientes(nombre, apellidos, nif, direccion));
                    System.out.println("Se ha añadido el cliente");
                } else {
                    System.out.println("No se ha añadido el cliente");
                }

                break;
            case 4:
                System.out.println("Ha elegido borrar");
                System.out.println("Escribe el nif del cliente que "
                        + "desea borrar");
                teclado.nextLine();
                String nifrBorrar = teclado.nextLine();
                int existecli = 0;
                for (Clientes cli : listaClientes) {
                    if (nifrBorrar.equals(cli.getNifCliente())) {
                        listaClientes.remove(cli);
                        existecli = 1;
                    }
                }
                if (existecli == 1) {
                    System.out.println("se ha eliminado el cliente");
                } else {
                    System.out.println("El cliente no existe");
                }

                break;
        }
    }

    public static void productos(ArrayList<Productos> listaProductos) {
        Scanner teclado = new Scanner(System.in);

        int menuProductos = 0;
        //do while para filtrar las operaciones
        do {
            System.out.println("¿Que operacion desea realizar?\n"
                    + "1.Consultar\n2.Modificar\n3.Añadir\n4.Borrar");
            menuProductos = teclado.nextInt();
        } while (menuProductos < 1 || menuProductos > 4);

        //switch para realizar las operaciones elegidas anteriormente
        switch (menuProductos) {
            case 1:
                System.out.println("Ha elegido consultar");
                //for para imprimir los productos
                for (Productos p : listaProductos) {
                    System.out.println(p);
                }
                break;
            case 2:
                System.out.println("Ha elegido modificar");
                //for para imprimir los productos
                for (Productos c : listaProductos) {
                    System.out.println(c);
                }
                System.out.println("¿Que producto de los anteriores quiere "
                        + "modificar? 'ID'");
                teclado.nextLine();
                String idProducto = teclado.nextLine();

                int comprobacion = 0;
                //for para modificar el cliente
                for (Productos p : listaProductos) {
                    //if para comprobar el id del producto
                    if (idProducto.equals(p.getIdProducto())) {
                        //if para saber si es articulo
                        if (p instanceof Articulos) {
//                            teclado.nextLine();
                            System.out.println("Introduce el nombre:");
                            String nombre = teclado.nextLine();
                            ((Articulos) p).setNombre(nombre);

                            System.out.println("Introduce el peso:");
                            int peso = teclado.nextInt();
                            ((Articulos) p).setPeso(peso);

                            System.out.println("Introduce la fecha de fabricacion:");
                            teclado.nextLine();
                            String fechaFabricacion = teclado.nextLine();
                            LocalDate fecha = LocalDate.parse(fechaFabricacion);
                            ((Articulos) p).setFechaFabricacion(fecha);
                            comprobacion = 1;

                            System.out.println("Introduce el precio:");
                            int precio = teclado.nextInt();
                            p.setPrecio(precio);
                            //if para saber si es servicio
                        } else if (p instanceof Servicios) {
                            System.out.println("Introduce el nombre:");
                            String nombre = teclado.nextLine();
                            ((Servicios) p).setNombre(nombre);

                            System.out.println("Introduce las horas:");
                            int horas = teclado.nextInt();
                            ((Servicios) p).setHoras(horas);

                            System.out.println("Introduce la fecha de comienzo:");
                            String fechaComienzo = teclado.nextLine();
                            LocalDate fechaC = LocalDate.parse(fechaComienzo);
                            ((Servicios) p).setFechaComienzo(fechaC);

                            System.out.println("Introduce la fecha de fin:");
                            String fechaFin = teclado.nextLine();
                            LocalDate fechaF = LocalDate.parse(fechaFin);
                            ((Servicios) p).setFechaFin(fechaF);

                            System.out.println("Introduce el precio:");
                            int precio = teclado.nextInt();
                            p.setPrecio(precio);
                            comprobacion = 1;
                        } else {
                            System.out.println("ERROR");
                        }
                    }
                }
                if (comprobacion == 0) {
                    System.out.println("No se ha encontrado el producto");
                }
                break;
            case 3:
                System.out.println("Ha elegido añadir");
                System.out.println("¿Que producto quiere añadir?\n1.Articulo\n2.Servicio");
                teclado.nextLine();
                int añadirProducto = teclado.nextInt();

                switch (añadirProducto) {
                    case 1:
                        teclado.nextLine();
                        System.out.println("Introduce el nombre:");
                        String nombre = teclado.nextLine();

                        System.out.println("Introduce el peso:");
                        int peso = teclado.nextInt();

                        System.out.println("Introduce la fecha de fabricacion:");
                        teclado.nextLine();
                        String fechaFabricacion = teclado.nextLine();
                        LocalDate fecha = LocalDate.parse(fechaFabricacion);

                        System.out.println("Introduzca el id del producto");
                        String id = teclado.nextLine();

                        System.out.println("Introduzca el precio del producto");
                        double precio = teclado.nextDouble();
                        listaProductos.add(new Articulos(nombre, peso, fecha, id, precio));
                        break;
                    case 2:
                        System.out.println("Introduce el nombre:");
                        String nombreServicio = teclado.nextLine();

                        System.out.println("Introduce las horas:");
                        int horas = teclado.nextInt();

                        System.out.println("Introduce la fecha de comienzo:");
                        String fechaComienzo = teclado.nextLine();
                        LocalDate fechaC = LocalDate.parse(fechaComienzo);

                        System.out.println("Introduce la fecha de fin:");
                        String fechaFin = teclado.nextLine();
                        LocalDate fechaF = LocalDate.parse(fechaFin);

                        System.out.println("Introduzca el id del producto");
                        String idServicio = teclado.nextLine();

                        System.out.println("Introduzca el precio del producto");
                        double precioServicio = teclado.nextDouble();

                        listaProductos.add(new Servicios(nombreServicio, horas, fechaC, fechaF, idServicio, precioServicio));
                        break;
                }
                break;

            case 4:
                System.out.println("Ha elegido borrar");
                System.out.println("Escribe el id del producto que "
                        + "desea borrar");
                teclado.nextLine();
                String idBorrar = teclado.nextLine();
                int existecli = 0;
                for (Productos p : listaProductos) {
                    if (idBorrar.equals(p.getIdProducto())) {
                        listaProductos.remove(p);
                        existecli = 1;
                    }
                }
                if (existecli == 1) {
                    System.out.println("se ha eliminado el producto");
                } else {
                    System.out.println("El producto no existe");
                }

                break;
        }
    }
    
//    public static void pedidos(ArrayList<Pedidos> listaPedidos) {
//        //pedidos
//        Scanner teclado = new Scanner(System.in);
//
//        int menuPedidos = 0;
//        //do while para filtrar las operaciones
//        do {
//            System.out.println("¿Que operacion desea realizar?\n"
//                    + "1.Consultar\n2.Modificar\n3.Añadir\n4.Borrar");
//            menuPedidos = teclado.nextInt();
//        } while (menuPedidos < 1 || menuPedidos > 4);
//
//        //switch para realizar las operaciones elegidas anteriormente
//        switch (menuPedidos) {
//            case 1:
//                System.out.println("Ha elegido consultar");
//                //for para imprimir los pedidos
//                for (Pedidos p : listaPedidos) {
//                    System.out.println(p);
//                }
//                break;
//            case 2:
//                System.out.println("Ha elegido modificar");
//                //for para imprimir los pedidos
//                for (Pedidos p : listaPedidos) {
//                    System.out.println(p);
//                }
//                System.out.println("¿Que pedido de los anteriores quiere "
//                        + "modificar?");
//                teclado.nextLine();
//                String numeroPedido = teclado.nextLine();
//
//                int comprobacion = 0;
//                //for para modificar el pedido
//                for (Pedidos p : listaPedidos) {
//                    //if para comprobar el numero del pedido
//                    if (numeroPedido.equals(p.getNifCliente())) {
//                        //se modifica el nombre, apellidos y direccion
//                        System.out.println("Introduce el nombre:");
//                        String nombre = teclado.nextLine();
//                        p.setNombre(nombre);
//
//                        System.out.println("Introduce los apellidos:");
//                        String apellidos = teclado.nextLine();
//                        p.setApellidos(apellidos);
//
//                        System.out.println("Introduce el nif:");
//                        String nif = teclado.nextLine();
//                        p.setNifCliente(nif);
//
//                        System.out.println("Introduce la direccion:");
//                        String direccion = teclado.nextLine();
//                        p.setDireccion(direccion);
//                        comprobacion = 1;
//                    }
//                }
//                if (comprobacion == 0) {
//                    System.out.println("No se ha encontrado el cliente");
//                }
//                break;
//            case 3:
//                System.out.println("Ha elegido añadir");
//                teclado.nextLine();
//                System.out.println("Introduce el nombre:");
//                String nombre = teclado.nextLine();
//                System.out.println("Introduce los apellidos:");
//                String apellidos = teclado.nextLine();
//                System.out.println("Introduce el nif:");
//                String nif = teclado.nextLine();
//                System.out.println("Introduce la direccion:");
//                String direccion = teclado.nextLine();
//
//                int nifExist = 0;
//                //for para comprobar si el dni a meter ya existe
//                for (Clientes cli : listaPedidos) {
//                    if (nif.equals(cli.getNifCliente())) {
//                        nifExist = 1;
//                    }
//                }
//                //if para meter el cliente en caso de que el nif no exista
//                if (nifExist == 0) {
//                    listaPedidos.add(new Clientes(nombre, apellidos, nif, direccion));
//                    System.out.println("Se ha añadido el cliente");
//                } else {
//                    System.out.println("No se ha añadido el cliente");
//                }
//
//                break;
//            case 4:
//                System.out.println("Ha elegido borrar");
//                System.out.println("Escribe el nif del cliente que "
//                        + "desea borrar");
//                teclado.nextLine();
//                String nifrBorrar = teclado.nextLine();
//                int existecli = 0;
//                for (Clientes cli : listaPedidos) {
//                    if (nifrBorrar.equals(cli.getNifCliente())) {
//                        listaPedidos.remove(cli);
//                        existecli = 1;
//                    }
//                }
//                if (existecli == 1) {
//                    System.out.println("se ha eliminado el cliente");
//                } else {
//                    System.out.println("El cliente no existe");
//                }
//
//                break;
//        }
//    }
}
