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
import productos.ArticulosCantidad;
import productos.Productos;
import productos.ServiciosCantidad;

/**
 *
 * @author aguil
 */
public class Pedidos {
    //atributos

    //sirve para dar formato en las fechas en los ficheros JSON
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaPedido;
    private String numeroPedido;
    private String nomCliente;
    private Empresa empresa;
    private String tipoPago;
    private ArrayList<ArticulosCantidad> articuloCantidad = new ArrayList<>();
    private ArrayList<ServiciosCantidad> servicioCantidad = new ArrayList<>();
    private String dirCliente;

    //constructores
    public Pedidos() {
    }

    public Pedidos(LocalDate fechaPedido, String numeroPedido) {
        this.fechaPedido = fechaPedido;
        this.numeroPedido = numeroPedido;
    }

    //metodo que crea un numero de pedido del tipo: 1234/2021
    public String crearNumeroPedido() {
        Random random = new Random();
        int aleatorio = random.nextInt(9999 - 1000 + 1) + 1000;
        String numPedido = String.valueOf(aleatorio) + "/" + LocalDate.now().getYear();
        return numPedido;
    }

    //getters, setters y toString
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

    public ArrayList<ArticulosCantidad> getArticuloCantidad() {
        return articuloCantidad;
    }

    public void setArticuloCantidad(ArrayList<ArticulosCantidad> articuloCantidad) {
        this.articuloCantidad = articuloCantidad;
    }

    public ArrayList<ServiciosCantidad> getServicioCantidad() {
        return servicioCantidad;
    }

    public void setServicioCantidad(ArrayList<ServiciosCantidad> servicioCantidad) {
        this.servicioCantidad = servicioCantidad;
    }

    public String getDirCliente() {
        return dirCliente;
    }

    public void setDirCliente(String dirCliente) {
        this.dirCliente = dirCliente;
    }

    @Override
    public String toString() {
        return empresa + "\n" + "Fecha: " + fechaPedido + "\n" + "Cliente: " + nomCliente + "\t\t"
                + "Nº " + numeroPedido + "\n" + "Direccion del cliente: " + dirCliente + "\n"
                + articuloCantidad + servicioCantidad + "\n" + "Forma de pago: " + tipoPago + "\n--------------------------";

    }

}
