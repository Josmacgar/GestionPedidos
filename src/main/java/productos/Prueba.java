/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import clientes.Clientes;

/**
 *
 * @author aguil
 */
public class Prueba {

    public static void main(String[] args) {

        //articulo
        Articulos articulo = new Articulos();
//        articulo.leerArticulo("articulos.csv");
        for (Articulos prenda : articulo.leerArticulo("articulos.csv")) {
            System.out.println(prenda);
        }
        System.out.println("----------Servicios");
        // servicios
        Servicios servicio = new Servicios();
//        servicio.leerServicio("servicios.csv");
        for (Servicios ser : servicio.leerServicio("servicios.csv")) {
            System.out.println(ser);
        }

        System.out.println("----------Clientes");
        // clientes
        Clientes cliente = new Clientes();
//        servicio.leerServicio("servicios.csv");
        for (Clientes cli : cliente.leerCliente("clientes.csv")) {
            System.out.println(cli);
        }

    }

}
