/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Almacen;

import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author aguil
 */
public class Pedidos {

    private LocalDate fechaPedido;
    private String numeroPedido;

    public Pedidos() {
    }

    public Pedidos(LocalDate fechaPedido, String numeroPedido) {
        this.fechaPedido = fechaPedido;
        this.numeroPedido = numeroPedido;
    }

    public void crearNumeroPedido() {
        Random random = new Random();
        int aleatorio = random.nextInt(9999 - 1000 + 1) + 1000;
        this.numeroPedido = String.valueOf(aleatorio) + "/" + LocalDate.now().getYear();
    }

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

    @Override
    public String toString() {
        return "Pedidos{" + "fechaPedido=" + fechaPedido + ", numeroPedido=" + numeroPedido + '}';
    }



}
