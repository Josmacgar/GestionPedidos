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

    //atributos
    private String idProducto;
    private double precio;

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

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "idProducto:" + idProducto + ", precio:" + precio;
    }

}
