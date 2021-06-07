/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

/**
 *
 * @author aguil
 */
public class Productos {

    private int idProducto;
    private int precio;

    public Productos() {
    }

    public Productos(int idProducto, int precio) {
        this.idProducto = idProducto;
        this.precio = precio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Productos{" + "idProducto=" + idProducto + ", precio=" + precio + '}';
    }

}
