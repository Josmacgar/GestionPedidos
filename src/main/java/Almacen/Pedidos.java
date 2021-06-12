/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Almacen;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import productos.ProductoNombreCantidad;
import productos.Productos;

/**
 *
 * @author aguil
 */
public class Pedidos {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaPedido;
    private String numeroPedido;
    private String nomCliente;
    private Empresa empresa;
    private String tipoPago;
    private ArrayList<ProductoNombreCantidad> lista = new ArrayList<>();
    private String dirCliente;

    public void setDirCliente(String dirCliente) {
        this.dirCliente = dirCliente;
    }

    public Pedidos() {
    }

    public Pedidos(LocalDate fechaPedido, String numeroPedido) {
        this.fechaPedido = fechaPedido;
        this.numeroPedido = numeroPedido;
    }

    public String crearNumeroPedido() {
        Random random = new Random();
        int aleatorio = random.nextInt(9999 - 1000 + 1) + 1000;
        String numPedido = String.valueOf(aleatorio) + "/" + LocalDate.now().getYear();
        return numPedido;
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

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public ArrayList<ProductoNombreCantidad> getLista() {
        return lista;
    }

    public void setLista(ArrayList<ProductoNombreCantidad> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return empresa + "\n" + "Fecha: " + fechaPedido + "\n" + "Cliente: " + nomCliente + "\t\t"
                + "Nº " + numeroPedido + "\n" + "Direccion del cliente: " + dirCliente + "\n"
                + lista + "\n" + "Forma de pago: " + tipoPago+"\n--------------------------";

    }

    public String imprimirLista() {
        String prueba ="nombre\tcantidad";
        if (!lista.isEmpty()) {
            for (ProductoNombreCantidad productoNombreCantidad : lista) {
               prueba+= productoNombreCantidad.getNombre()+productoNombreCantidad.getCantidad();
            }
        }else{
            return null;
        }
        return prueba;
    }
    //return "Pedidos{" + "fechaPedido=" + fechaPedido + ", numeroPedido=" + numeroPedido + ", nomCliente=" + nomCliente + ", empresa=" + empresa + ", tipoPago=" + tipoPago + ", dirCliente=" + dirCliente + ", lista=" + lista + '}';

}
