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
public class ArticulosCantidad {

    private Articulos articulo;
    private int cantidad;

    public ArticulosCantidad() {
    }

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
        return "Productos\n" + articulo.getNombre() + "--> " +cantidad+"unidades"+"--> "
                + articulo.getPrecio() + "€."+ "\n--------------------------------------------\n";
                
    }

}
