/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import clientes.Clientes;
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

    public static void menu() {

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
                    clientes();
                    break;
                case 2:
                    productos();
                    break;
            }
        } while (menu != 7);

    }//menu

    public static void clientes() {
        //Clientes
        Clientes cliente = new Clientes();
        ArrayList<Clientes> listaClientes = cliente.leerCliente("clientes.csv");
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

    public static void productos() {
        //Articulos
        Articulos articulo = new Articulos();
        ArrayList<Articulos> listaArticulos = articulo.leerArticulo("articulos.csv");
        //Servicios
        Servicios servicio = new Servicios();
        ArrayList<Servicios> listaServicios = servicio.leerServicio("servicios.csv");
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
                //for para imprimir los clientes
                for (Productos p : listaProductos) {
                    System.out.println(c);
                }
                break;
            case 2:
                System.out.println("Ha elegido modificar");
                //for para imprimir los clientes
                for (Clientes c : listaProductos) {
                    System.out.println(c);
                }
                System.out.println("¿Que clientes de los anteriores quiere "
                        + "modificar?");
                teclado.nextLine();
                String nifCliente = teclado.nextLine();

                int comprobacion = 0;
                //for para modificar el cliente
                for (Clientes c : listaProductos) {
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
                for (Clientes cli : listaProductos) {
                    if (nif.equals(cli.getNifCliente())) {
                        nifExist = 1;
                    }
                }
                //if para meter el cliente en caso de que el nif no exista
                if (nifExist == 0) {
                    listaProductos.add(new Clientes(nombre, apellidos, nif, direccion));
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
                for (Clientes cli : listaProductos) {
                    if (nifrBorrar.equals(cli.getNifCliente())) {
                        listaProductos.remove(cli);
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
}
