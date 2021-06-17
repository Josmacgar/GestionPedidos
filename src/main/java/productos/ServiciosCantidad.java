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
public class ServiciosCantidad extends Productos {

    private Servicios servicio;
    private int cantidad;

    public ServiciosCantidad() {
    }

    public ServiciosCantidad(Servicios servicio, int cantidad) {
        this.servicio = servicio;
        this.cantidad = cantidad;
    }

    public ServiciosCantidad(Servicios servicio, int cantidad, String idProducto, double precio) {
        super(idProducto, precio);
        this.servicio = servicio;
        this.cantidad = cantidad;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Servicios\n" + servicio.getNombre() + "--> " + cantidad + " unidades" + "--> "
                + servicio.getPrecio() + "€./unidad" + "\n--------------------------------------------\n";
    }

}
