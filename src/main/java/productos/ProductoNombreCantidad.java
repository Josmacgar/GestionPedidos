/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import java.util.ArrayList;

/**
 *
 * @author aguil
 */
public class ProductoNombreCantidad {

    private String nombre;
    private int cantidad;
    private ArrayList<ProductoNombreCantidad> listaProductoCantidad;
    public ProductoNombreCantidad() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "nombre:" + nombre + "\t cantidad:" + cantidad;
    }

}
