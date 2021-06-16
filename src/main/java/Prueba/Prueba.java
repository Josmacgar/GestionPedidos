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
                System.out.println("Ha elegido consultar");
                //for para imprimir los clientes
                for (Clientes c : empresa1.getListaClientes()) {
                    System.out.println(c);
                }
                break;
            case 2:
                System.out.println("Ha elegido modificar");
                //for para imprimir los clientes
                for (Clientes c : empresa1.getListaClientes()) {
                    System.out.println(c);
                }
                System.out.println("¿Que clientes de los anteriores quiere "
                        + "modificar?");
                teclado.nextLine();
                String nifCliente = teclado.nextLine();

                int comprobacion = 0;
                //for para modificar el cliente
                for (Clientes c : empresa1.getListaClientes()) {
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
                for (Clientes cli : empresa1.getListaClientes()) {
                    if (nif.equals(cli.getNifCliente())) {
                        nifExist = 1;
                    }
                }
                //if para meter el cliente en caso de que el nif no exista
                if (nifExist == 0) {
                    empresa1.getListaClientes().add(new Clientes(nombre, apellidos, nif, direccion));
                    System.out.println("Se ha añadido el cliente");
                } else {
                    System.out.println("No se ha añadido el cliente");
                }

                break;
            case 4:
                System.out.println("Ha elegido borrar");
                for (Clientes cli : empresa1.getListaClientes()) {
                    System.out.println(cli);
                }
                System.out.println("Escribe el nif del cliente que "
                        + "desea borrar");
                teclado.nextLine();
                String nifBorrar = teclado.nextLine();

                int numeroBorrar = 0;
                int existCliente = 0;
                //for que recorre la lista de clientes y si es igual actualiza la
                //variable igualandola a la posicion de la lista para luego eliminarla
                for (int i = 0; i < empresa1.getListaClientes().size(); i++) {
                    if (nifBorrar.equals(empresa1.getListaClientes().get(i).getNifCliente())) {
                        numeroBorrar = i;
                        existCliente = 1;
                    }
                }
                empresa1.getListaClientes().remove(numeroBorrar);
                if (existCliente == 1) {
                    System.out.println("se ha eliminado el cliente");
                } else {
                    System.out.println("El cliente no existe");
                }

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
                System.out.println("Ha elegido consultar");
                //for para imprimir los productos
                for (Productos p : empresa1.getListaProductos()) {
                    System.out.println(p);
                }
                break;
            case 2:
                System.out.println("Ha elegido modificar");
                //for para imprimir los productos
                for (Productos c : empresa1.getListaProductos()) {
                    System.out.println(c);
                }
                System.out.println("¿Que producto de los anteriores quiere "
                        + "modificar? 'ID'");
                teclado.nextLine();
                String idProducto = teclado.nextLine();

                int comprobacion = 0;
                //for para modificar el cliente
                for (Productos p : empresa1.getListaProductos()) {
                    //if para comprobar el id del producto
                    if (idProducto.equals(p.getIdProducto())) {
                        //if para saber si es articulo
                        if (p instanceof Articulos) {
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
                //switch para elegir las operaciones
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
                        empresa1.getListaProductos().add(new Articulos(nombre, peso, fecha, id, precio));
                        break;
                    case 2:
                        System.out.println("Introduce el nombre:");
                        teclado.nextLine();
                        String nombreServicio = teclado.nextLine();

                        System.out.println("Introduce las horas:");
                        int horas = teclado.nextInt();

                        System.out.println("Introduce la fecha de comienzo:");
                        teclado.nextLine();
                        String fechaComienzo = teclado.nextLine();
                        LocalDate fechaC = LocalDate.parse(fechaComienzo);

                        System.out.println("Introduce la fecha de fin:");
                        String fechaFin = teclado.nextLine();
                        LocalDate fechaF = LocalDate.parse(fechaFin);

                        System.out.println("Introduzca el id del producto");
                        String idServicio = teclado.nextLine();

                        System.out.println("Introduzca el precio del producto");
                        double precioServicio = teclado.nextDouble();

                        empresa1.getListaProductos().add(new Servicios(nombreServicio, horas, fechaC, fechaF, idServicio, precioServicio));
                        break;
                }
                break;

            case 4:
                System.out.println("Ha elegido borrar");
                for (Productos pro : empresa1.getListaProductos()) {
                    System.out.println(pro);
                }
                System.out.println("Escribe el id del producto que "
                        + "desea borrar");
                teclado.nextLine();
                String idBorrar = teclado.nextLine();
                //for que recorre la lista de clientes y si es igual actualiza la
                //variable igualandola a la posicion de la lista para luego eliminarla
                int numeroBorrar = 0;
                int existProducto = 0;
                for (int i = 0; i < empresa1.getListaProductos().size(); i++) {
                    if (idBorrar.equals(empresa1.getListaProductos().get(i).getIdProducto())) {
                        numeroBorrar = i;
                        existProducto = 1;
                    }
                }
                empresa1.getListaProductos().remove(numeroBorrar);
                if (existProducto == 1) {
                    System.out.println("se ha eliminado el producto");
                } else {
                    System.out.println("El producto no existe");
                }

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
                System.out.println("Ha elegido consultar");
                //for para imprimir los pedidos
                for (Pedidos p : empresa1.getListaPedidos()) {
                    System.out.println(p);
                }
                break;
            case 2:
                System.out.println("Ha elegido modificar");
                //for para imprimir los pedidos
                for (Pedidos p : empresa1.getListaPedidos()) {
                    System.out.println(p);
                }
                System.out.println("¿Que pedido de los anteriores quiere "
                        + "modificar?");
                teclado.nextLine();
                String numeroPedido = teclado.nextLine();

                int comprobacion = 0;
                //for para modificar el pedido
                for (Pedidos p : empresa1.getListaPedidos()) {
                    //if para comprobar el numero del pedido
                    if (numeroPedido.equals(p.getNumeroPedido())) {
                        //se modifica el nombre,tipo de pago y la lista de productos
                        for (Clientes cli : empresa1.getListaClientes()) {
                            System.out.println(cli);
                        }
                        System.out.println("Introduce el nif del cliente:");
                        String nifCli = teclado.nextLine();
                        for (Clientes c : empresa1.getListaClientes()) {
                            //if para comprobar el nombre del cliente
                            if (nifCli.equals(c.getNifCliente())) {
                                p.setNomCliente(c.getNombre() + "," + c.getApellidos());
                                p.setDirCliente(c.getDireccion());
                            }
                        }

                        //se modifica el tipo de pago
                        String tipoPago;
                        do {
                            System.out.println("Introduce el tipo de pago");
                            tipoPago = teclado.nextLine();
                        } while (!tipoPago.equalsIgnoreCase("tarjeta") && !tipoPago.equalsIgnoreCase("transferencia"));
                        pedido.setTipoPago(tipoPago);

                        //modificar lista de productos
//                        p.getLista().forEach(System.out::println);
                    }
                }
                if (comprobacion == 0) {
                    System.out.println("No se ha encontrado el cliente");
                }
                break;
            case 3:
                System.out.println("Ha elegido añadir");
                teclado.nextLine();
                //se le asigna un numero de pedido
                pedido.setNumeroPedido(pedido.crearNumeroPedido());
                //se le asigna la empresa
                pedido.setEmpresa(empresa);
                //se muestran todos los clientes
                for (Clientes cli : empresa1.getListaClientes()) {
                    System.out.println(cli);
                }
                System.out.println("Elige uno de los clientes anteriores");
                String nifCliente = teclado.nextLine();

                //for para comprobar si el dni ya existe
                for (Clientes cli : empresa1.getListaClientes()) {
                    if (nifCliente.equals(cli.getNifCliente())) {
                        pedido.setNomCliente(cli.getNombre() + "," + cli.getApellidos());
                        pedido.setDirCliente(cli.getDireccion());
                    }
                }
                //se le pone la fecha de hoy al pedido
                pedido.setFechaPedido(LocalDate.now());
                //se configura el tipo de pago
                String tipoPago;
                do {
                    System.out.println("Introduce el tipo de pago");
                    tipoPago = teclado.nextLine();
                } while (!tipoPago.equalsIgnoreCase("tarjeta") && !tipoPago.equalsIgnoreCase("transferencia"));
                pedido.setTipoPago(tipoPago);
                //se selecciona los articulos que se quieran añadir al pedido
                for (Productos producto : empresa1.getListaProductos()) {
                    System.out.println(producto);
                }
                //listas para añadir los productos
                int salir = 0;
                ArrayList<ArticulosCantidad> articuloPedido = new ArrayList<>();
                ArrayList<ServiciosCantidad> servicioPedido = new ArrayList<>();

                do {
                    salir = 0;
                    System.out.println("Elige un producto de la lista por su id");
                    String producto = teclado.nextLine();
                    //for para añadir productos dependiendo si son artiulos o servicios
                    int comprobar = 0;
                    for (Productos p : empresa1.getListaProductos()) {
                        if (p instanceof Articulos) {
                            if (p.getIdProducto().equals(producto)) {
                                ArticulosCantidad articuloP = new ArticulosCantidad();
                                articuloP.setArticulo((Articulos) p);
                                System.out.println("Indica la cantidad: ");
                                int cantidad = teclado.nextInt();
                                articuloP.setCantidad(cantidad);
                                articuloPedido.add(articuloP);
                            } else {
                                comprobar = 1;
                            }
                        } else if (p instanceof Servicios) {
                            if (p.getIdProducto().equals(producto)) {
                                ServiciosCantidad servicioP = new ServiciosCantidad();
                                servicioP.setServicio((Servicios) p);
                                System.out.println("Indica la cantidad: ");
                                int cantidad = teclado.nextInt();
                                servicioP.setCantidad(cantidad);
                                servicioPedido.add(servicioP);
                            } else {
                                comprobar = 1;
                            }
                        }
                    }
                    if (comprobar == 1) {
                        System.out.println("Producto no encontrado");
                    }
                    System.out.println("¿Añadir otro producto? si/no");
                    teclado.nextLine();
                    String salida = teclado.nextLine();
                    if (salida.equalsIgnoreCase("Si")) {
                        salir = 1;
                    }
                } while (salir == 1);
                pedido.setArticuloCantidad(articuloPedido);
                pedido.setServicioCantidad(servicioPedido);
                empresa1.getListaPedidos().add(pedido);
                break;
            case 4:
                System.out.println("Ha elegido borrar");
                for (Pedidos p : empresa1.getListaPedidos()) {
                    System.out.println(p);
                }
                System.out.println("Escribe el numero del pedido que "
                        + "desea borrar");
                teclado.nextLine();
                String pedidoBorrar = teclado.nextLine();
                int numeroBorrar = 0;
                int existPedido = 0;
                //for que recorre la lista de clientes y si es igual actualiza la
                //variable igualandola a la posicion de la lista para luego eliminarla
                for (int i = 0; i < empresa1.getListaPedidos().size(); i++) {
                    if (pedidoBorrar.equals(empresa1.getListaPedidos().get(i).getNumeroPedido())) {

                        numeroBorrar = i;
                        existPedido = 1;
                    }
                }
                empresa1.getListaPedidos().remove(numeroBorrar);

                if (existPedido == 1) {
                    System.out.println("se ha eliminado el pedido");
                } else {
                    System.out.println("El pedido no existe");
                }

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
        empresa1.getListaPedidos().clear();
        String datos = "./backup/" + copiaEleccion + "/backupPedidos.json";
        System.out.println(datos);

        empresa1.setListaPedidos(mapeadorLectura.readValue(new File(datos),
                mapeadorLectura.getTypeFactory().constructCollectionType(ArrayList.class, Pedidos.class)));
        restarurarCopiaClientes(empresa1, copiaEleccion);
//        restarurarCopiaproductos(empresa1, copiaEleccion);
    }

    //metodo que restarura la copia de seguridad de los clientes de la empresa
    public static void restarurarCopiaClientes(Empresa empresa1, String copiaEleccion) throws IOException {
        ObjectMapper mapeadorLectura = new ObjectMapper();
        String datos = "./backup/" + copiaEleccion + "/backupClientes.json";
        System.out.println(datos);
        empresa1.setListaClientes(mapeadorLectura.readValue(new File(datos),
                mapeadorLectura.getTypeFactory().constructCollectionType(ArrayList.class, Clientes.class)));
    }

    //metodo que restarura la copia de seguridad de los productos de la empresa
    public static void restarurarCopiaproductos(Empresa empresa1, String copiaEleccion) throws IOException {
        ObjectMapper mapeadorLectura = new ObjectMapper();
        String datos = "./backup/" + copiaEleccion + "/backupProductos.json";
        System.out.println(datos);
        empresa1.setListaProductos(mapeadorLectura.readValue(new File(datos),
                mapeadorLectura.getTypeFactory().constructCollectionType(ArrayList.class, Productos.class)));
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
