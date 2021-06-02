/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Almacen;

import java.time.LocalDate;

/**
 *
 * @author aguil
 */
public class Prueba {
    public static void main(String[] args) {
        Pedidos pedido = new Pedidos();
        pedido.crearNumeroPedido();
        System.out.println(pedido.toString());
        
       
    }
}
