/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Almacen;

import clientes.Clientes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import productos.Articulos;
import productos.ArticulosCantidad;
import productos.Productos;
import productos.Servicios;
import productos.ServiciosCantidad;

/**
 *
 * @author aguil
 */
public class Pedidos {
    //atributos

    //sirve para dar formato en las fechas en los ficheros JSON
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaPedido;
    private String numeroPedido;
    private String nomCliente;
    private Empresa empresa;
    private String tipoPago;
    private ArrayList<ArticulosCantidad> articuloCantidad = new ArrayList<>();
    private ArrayList<ServiciosCantidad> servicioCantidad = new ArrayList<>();
    private String dirCliente;
    private static Scanner teclado = new Scanner(System.in);

    //constructores
    public Pedidos() {
    }

    public Pedidos(LocalDate fechaPedido, String numeroPedido) {
        this.fechaPedido = fechaPedido;
        this.numeroPedido = numeroPedido;
    }

    //metodo que crea un numero de pedido del tipo: 1234/2021
    public String crearNumeroPedido() {
        Random random = new Random();
        int aleatorio = random.nextInt(9999 - 1000 + 1) + 1000;
        String numPedido = String.valueOf(aleatorio) + "/" + LocalDate.now().getYear();
        return numPedido;
    }

    //getters, setters y toString
    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public ArrayList<ArticulosCantidad> getArticuloCantidad() {
        return articuloCantidad;
    }

    public void setArticuloCantidad(ArrayList<ArticulosCantidad> articuloCantidad) {
        this.articuloCantidad = articuloCantidad;
    }

    public ArrayList<ServiciosCantidad> getServicioCantidad() {
        return servicioCantidad;
    }

    public void setServicioCantidad(ArrayList<ServiciosCantidad> servicioCantidad) {
        this.servicioCantidad = servicioCantidad;
    }

    public String getDirCliente() {
        return dirCliente;
    }

    public void setDirCliente(String dirCliente) {
        this.dirCliente = dirCliente;
    }

    @Override
    public String toString() {
        return empresa + "\n" + "Fecha: " + fechaPedido + "\n" + "Cliente: " + nomCliente + "\t\t"
                + "Nº " + numeroPedido + "\n" + "Direccion del cliente: " + dirCliente + "\n"
                + articuloCantidad + servicioCantidad + "\n" + "Forma de pago: " + tipoPago + "\n--------------------------";

    }

    //metodo que borra un pedido de la empresa
    public static void mostrarPedidos(Empresa empresa1) {
        System.out.println("Ha elegido consultar");
        //for para imprimir los pedidos
        for (Pedidos p : empresa1.getListaPedidos()) {
            System.out.println(p);
        }
    }

    //metodo que modifica un pedido de la empresa
    public static void modificarPedido(Empresa empresa1, Pedidos pedido) {
        System.out.println("Ha elegido modificar");
        //for para imprimir los pedidos
        for (Pedidos p : empresa1.getListaPedidos()) {
            System.out.println(p);
        }
        System.out.println("¿Que pedido de los anteriores quiere "
                + "modificar?");
        String numeroPedido = teclado.nextLine();

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
                p.setTipoPago(tipoPago);

                //modificar lista de productos
                int respuesta = 0;
                //do while para que la respuesta sea 1 o 2
                //esto realiza la accion de añadir o eliminar productos al pedido
                do {
                    System.out.println("¿Que desea realizar\n1.Añadir productos\n2.Eliminar"
                            + "Productos\n3.Salir");
                    respuesta = teclado.nextInt();

                    //switch para añadir o eliminar productos del pedido
                    switch (respuesta) {
                        case 1:
                            //se selecciona los articulos que se quieran añadir al pedido
                            for (Productos producto : empresa1.getListaProductos()) {
                                System.out.println(producto);
                            }
                            //listas para añadir los productos
                            int salir = 0;
                            ArrayList<ArticulosCantidad> articuloPedido = p.getArticuloCantidad();
                            ArrayList<ServiciosCantidad> servicioPedido = p.getServicioCantidad();

                            do {
                                salir = 0;
                                System.out.println("Elige un producto de la lista por su id");
                                teclado.nextLine();
                                String producto = teclado.nextLine();
                                //for para añadir productos dependiendo si son artiulos o servicios
                                int comprobar = 0;
                                for (Productos pr : empresa1.getListaProductos()) {
                                    if (pr instanceof Articulos) {
                                        if (pr.getIdProducto().equals(producto)) {
                                            ArticulosCantidad articuloP = new ArticulosCantidad();
                                            articuloP.setArticulo((Articulos) pr);
                                            System.out.println("Indica la cantidad: ");
                                            int cantidad = teclado.nextInt();
                                            articuloP.setCantidad(cantidad);
                                            articuloPedido.add(articuloP);
                                        } else {
                                            comprobar = 1;
                                        }
                                    } else if (pr instanceof Servicios) {
                                        if (pr.getIdProducto().equals(producto)) {
                                            ServiciosCantidad servicioP = new ServiciosCantidad();
                                            servicioP.setServicio((Servicios) pr);
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
//                                teclado.nextLine();
                                String salida = teclado.nextLine();
                                if (salida.equalsIgnoreCase("Si")) {
                                    salir = 1;
                                }
                            } while (salir == 1);
                            p.setArticuloCantidad(articuloPedido);
                            p.setServicioCantidad(servicioPedido);
                            break;
                        case 2:
                            p.getArticuloCantidad().clear();
                            p.getServicioCantidad().clear();
                            System.out.println("Lista borrada\npara añadir productos"
                                    + "vaya al menu 2");
                            break;
                    }
                } while (respuesta != 3);

            }
        }
    }

    //metodo que añade un pedido a la empresa
    public static void añadirPedido(Empresa empresa1, Pedidos pedido, Empresa empresa) {
        System.out.println("Ha elegido añadir");
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
    }

    //metodo que borra un pedido de la empresa
    public static void borrarPedido(Empresa empresa1) {
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
    }

    //metodo que restarura la copia de seguridad de los clientes de la empresa
    public static void restarurarCopiaPedidos(Empresa empresa1, String copiaEleccion) throws IOException {
        ObjectMapper mapeadorLectura = new ObjectMapper();
        String datos = "./backup/" + copiaEleccion + "/backupPedidos.json";
        System.out.println(datos);
        empresa1.setListaPedidos(mapeadorLectura.readValue(new File(datos),
                mapeadorLectura.getTypeFactory().constructCollectionType(ArrayList.class, Pedidos.class)));
    }

}
