/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Almacen;

import clientes.Clientes;
import productos.Productos;
import java.util.ArrayList;

/**
 *
 * @author aguil
 */
public class Empresa {

    private final String NIF;
    private final String NOMBREEMPRESA;
    private String direccionEmpresa;
    private int telefono;
    private ArrayList<Clientes> listaClientes;
    private ArrayList<Productos> listaProductos;
    private ArrayList<Pedidos> listaPedidos;

    public Empresa() {
        this.NIF = "46587744";
        this.NOMBREEMPRESA = "machoFish";
        this.direccionEmpresa = "Calvario Nº 12 ";
        this.telefono = 359411223;
        this.listaClientes = new ArrayList<>();
        this.listaProductos = new ArrayList<>();
        this.listaPedidos = new ArrayList<>();
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Clientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ArrayList<Pedidos> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(ArrayList<Pedidos> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }
    
    

    @Override
    public String toString() {
        return "--------------------------\n" + NOMBREEMPRESA + "\n" + direccionEmpresa + "\n"
                + telefono + "\n--------------------------";
    }

}
