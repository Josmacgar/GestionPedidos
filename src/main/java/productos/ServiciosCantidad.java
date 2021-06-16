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
public class ServiciosCantidad {

    private Servicios servicio;
    private int cantidad;

    public ServiciosCantidad() {
    }

    public Servicios getServicio() {
        return servicio;
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
        return "Servicios\n" + servicio.getNombre() + "--> " +cantidad+"unidades"+"--> "
                + servicio.getPrecio() + "€./unidad"+ "\n--------------------------------------------\n";
    }

}
