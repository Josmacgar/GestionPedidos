/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import Almacen.Empresa;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author aguil
 */
public class Productos {

    //atributos
    private String idProducto;
    private double precio;
    private static Scanner teclado = new Scanner(System.in);

    //constructores
    public Productos() {
    }

    public Productos(String idProducto, double precio) {
        this.idProducto = idProducto;
        this.precio = precio;
    }

    //getters, setters y toString
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "idProducto:" + idProducto + ", precio:" + precio;
    }

    //metodo que muestra los productos
    public static void mostrarProductos(Empresa empresa1) {
        System.out.println("Ha elegido consultar");
        //for para imprimir los productos
        for (Productos p : empresa1.getListaProductos()) {
            System.out.println(p);
        }
    }

    //metodo que modifica el producto de la empresa que se le pasa como parametro
    public static void modificarProducto(Empresa empresa1) {
        System.out.println("Ha elegido modificar");
        //for para imprimir los productos
        for (Productos c : empresa1.getListaProductos()) {
            System.out.println(c);
        }
        System.out.println("¿Que producto de los anteriores quiere "
                + "modificar? 'ID'");
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
    }

    //metodo que añade un producto a la empresa que se le pasa como parametro
    public static void añadirProducto(Empresa empresa1) {
        System.out.println("Ha elegido añadir");
        System.out.println("¿Que producto quiere añadir?\n1.Articulo\n2.Servicio");
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
    }

    public static void borrarProducto(Empresa empresa1) {
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
    }

    //metodo que restarura la copia de seguridad de los productos de la empresa
    public static void restarurarCopiaproductos(Empresa empresa1, String copiaEleccion) throws IOException {
        ObjectMapper mapeadorLectura = new ObjectMapper();
        String datos = "./backup/" + copiaEleccion + "/backupProductos.json";
        System.out.println(datos);
        empresa1.setListaProductos(mapeadorLectura.readValue(new File(datos),
                mapeadorLectura.getTypeFactory().constructCollectionType(ArrayList.class, Productos.class)));
    }
}
