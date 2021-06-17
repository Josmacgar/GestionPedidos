/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import Almacen.Empresa;
import Almacen.Pedidos;
import clientes.Clientes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import productos.Articulos;
import productos.Productos;
import productos.Servicios;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.StringTokenizer;
import productos.ArticulosCantidad;
import productos.ServiciosCantidad;

/**
 *
 * @author Jose Antonio
 */
public class Prueba {

    //main
    public static void main(String[] args) throws IOException {
        menu();
    }

    //metodo que muestra el menu y ejecuta otros metodos
    //del programa dependiendo de la seleccion
    public static void menu() throws IOException { //original
        Empresa empresa1 = new Empresa();
        //clientes    
        Clientes cliente = new Clientes();
        ArrayList<Clientes> listaClientes = cliente.leerCliente("clientes.csv");
        empresa1.setListaClientes(listaClientes);

        //productos
        Articulos articulo = new Articulos();
        Servicios servicio = new Servicios();
        ArrayList<Articulos> listaArticulos = articulo.leerArticulo("articulos.csv");
        ArrayList<Servicios> listaServicios = servicio.leerServicio("servicios.csv");
        //for para meter los articulos en la lista de productos        
        for (Productos producto : listaArticulos) {
            empresa1.getListaProductos().add(producto);
        }
        //for para meter los servicios en la lista de productos
        for (Productos producto : listaServicios) {
            empresa1.getListaProductos().add(producto);
        }

        Scanner teclado = new Scanner(System.in);
        // do while para el menu
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
                    clientes(empresa1);
                    break;
                case 2:
                    productos(empresa1);
                    break;
                case 3:
                    pedidos(empresa1);
                    break;
                case 4:
                    imprimirPedido(empresa1);
                    break;
                case 5:
                    generarBackup(empresa1);
                    break;
                case 6:
                    restaurarCopia(empresa1);
                    break;

            }
        } while (menu != 7);

    }//menu

    //metodo que realiza las operaciones de consultar,modificar,añadir y borrar Clientes
    public static void clientes(Empresa empresa1) {
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
                Clientes.mostrarCliente(empresa1);
                break;
            case 2:
                Clientes.modificarCliente(empresa1);
                break;
            case 3:
                Clientes.añadirCliente(empresa1);

                break;
            case 4:
                Clientes.borrarClientes(empresa1);
                break;
        }
    }

    //metodo que realiza las operaciones de consultar,modificar,añadir y borrar productos
    public static void productos(Empresa empresa1) {
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
                Productos.mostrarProductos(empresa1);
                break;
            case 2:
                Productos.modificarProducto(empresa1);
                break;
            case 3:
                Productos.añadirProducto(empresa1);
                break;

            case 4:
                Productos.borrarProducto(empresa1);
                break;
        }
    }

    //metodo que realiza las operaciones de consultar,modificar,añadir y borrar Pedidos
    public static void pedidos(Empresa empresa1) {
        //pedidos
        Scanner teclado = new Scanner(System.in);
        Pedidos pedido = new Pedidos();
        Empresa empresa = new Empresa();
        int menuPedidos = 0;
        //do while para filtrar las operaciones
        do {
            System.out.println("¿Que operacion desea realizar?\n"
                    + "1.Consultar\n2.Modificar\n3.Añadir\n4.Borrar");
            menuPedidos = teclado.nextInt();
        } while (menuPedidos < 1 || menuPedidos > 4);

        //switch para realizar las operaciones elegidas anteriormente
        switch (menuPedidos) {
            case 1:
                Pedidos.mostrarPedidos(empresa1);
                break;
            case 2:
                Pedidos.modificarPedido(empresa1, pedido);
                break;
            case 3:
                Pedidos.añadirPedido(empresa1, pedido, empresa);
                break;
            case 4:
                Pedidos.borrarPedido(empresa1);
                break;
        }
    }

    //metodo que recibe una empresa e imrime los pedidos en un txt
    public static void imprimirPedido(Empresa empresa1) {

        // Fichero a crear. Ruta relativa a la carpeta raíz del proyecto
        String idFichero = "imprimirPedido.txt";
        String tmp;

        // Si se utiliza el constructor FileWriter(idFichero, true) entonces se anexa información
        // al final del fichero idFichero
        // Estructura try-with-resources. Instancia el objeto con el fichero a escribir
        // y se encarga de cerrar el recurso "flujo" una vez finalizadas las operaciones
        try ( BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {
            for (Pedidos listaPedido : empresa1.getListaPedidos()) {
                flujo.write(listaPedido.toString());
            }
            // Metodo fluh() guarda cambios en disco 
            flujo.flush();
            System.out.println("Fichero " + idFichero + " creado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    //metodo que recibe una empresa y genera una copia de seguridad en JSON de los clientes
    //productos y pedidos
    public static void generarBackup(Empresa empresa1) throws IOException {
        //metodo que crea un directorio con la fecha y la hora actual
        crearCarpeta();
        ObjectMapper mapeador = new ObjectMapper();

        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        //crearcarpeta devuelve la ruta del directorio creado
        mapeador.writeValue(new File("./backup/" + crearCarpeta() + "/backupPedidos.json"), empresa1.getListaPedidos());
        mapeador.writeValue(new File("./backup/" + crearCarpeta() + "/backupClientes.json"), empresa1.getListaClientes());
        mapeador.writeValue(new File("./backup/" + crearCarpeta() + "/backupProductos.json"), empresa1.getListaProductos());
    }

    //metodo que crea un directorio con la fecha y la hora actual
    public static String crearCarpeta() {
        //datos para la fecha y la hora
        LocalDateTime locaDate = LocalDateTime.now();
        int horas = locaDate.getHour();
        int minutos = locaDate.getMinute();
        //String que se le pasa al crear el directorio
        String datos = String.valueOf(LocalDate.now() + "-" + String.valueOf(horas) + String.valueOf(minutos));
        //se crea el directorio
        File directorios = new File("./backup/" + datos);
        if (!directorios.exists()) {
            if (directorios.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear el directorio");
            }
        }
        return datos;
    }

    //metodo que restaura las copias de seguridad de una empresa pasada como parametro
    public static void restaurarCopia(Empresa empresa1) throws IOException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ha elegido restaurar copia de seguridad");
        mostrarCarpetas();
        ObjectMapper mapeadorLectura = new ObjectMapper();
        System.out.println("Elige una copia de seguridad para restaurar");

        String copiaEleccion = teclado.nextLine();
        //se restauran las copias de seguridad de pedidos,clientes y productos
        empresa1.getListaPedidos().clear();
        Pedidos.restarurarCopiaClientes(empresa1, copiaEleccion);
        Clientes.restarurarCopiaClientes(empresa1, copiaEleccion);
//        Productos.restarurarCopiaproductos(empresa1, copiaEleccion);
    }


    //metodo que muestra los directorios de la carpeta backup
    public static void mostrarCarpetas() {
        File carpeta = new File("./backup");
        String[] listado = carpeta.list();
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
            return;
        } else {
            for (int i = 0; i < listado.length; i++) {
                System.out.println(listado[i]);
            }
        }
    }
}
