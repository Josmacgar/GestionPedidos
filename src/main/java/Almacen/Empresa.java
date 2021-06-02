/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Almacen;

import productos.Productos;
import java.util.ArrayList;

/**
 *
 * @author aguil
 */
public class Empresa {

    private final String NIF;
    private final String NOMBREEMPRESA;
    ArrayList<String> listaClientes;
    ArrayList<Productos> listaProductos;
    ArrayList<Pedidos> listaPedidos;

    public Empresa() {
        this.NIF = "46587744";
        this.NOMBREEMPRESA = "machoFish";
        this.listaClientes = new ArrayList<>();
        this.listaProductos = new ArrayList<>();
        this.listaPedidos = new ArrayList<>();
    }
    

}
