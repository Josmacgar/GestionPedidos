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
public class  ArticulosCantidad extends Productos {

    //atributos
    private Articulos articulo;
    private int cantidad;

    //constructor

    public ArticulosCantidad() {
    }

    public ArticulosCantidad(Articulos articulo, int cantidad, String idProducto, double precio) {
        super(idProducto, precio);
        this.articulo = articulo;
        this.cantidad = cantidad;
    }
    
    

    //getters, setters y toString
    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Productos\n" + articulo.getNombre() + "--> " + cantidad + " unidades" + "--> "
                + articulo.getPrecio() + "€./unidad" + "\n--------------------------------------------\n";

    }

}
