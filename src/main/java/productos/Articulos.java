/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import Almacen.Empresa;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author aguil
 */
public class Articulos extends Productos {

    //atributos
    private String nombre;
    private int peso;
    //sirve para dar formato en las fechas en los ficheros JSON
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaFabricacion;
    private ArrayList<Articulos> listaArticulos;

    //constructores
    public Articulos() {
        listaArticulos = new ArrayList<>();
    }

    public Articulos(String nombre, int peso, LocalDate fechaFabricacion, String idProducto, double precio) {
        super(idProducto, precio);
        this.nombre = nombre;
        this.peso = peso;
        this.fechaFabricacion = fechaFabricacion;
        listaArticulos = new ArrayList<>();
    }

    //getters, setters y toString
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public ArrayList<Articulos> getArticulos() {
        return listaArticulos;
    }

    public void setArticulos(ArrayList<Articulos> articulos) {
        this.listaArticulos = articulos;
    }

    @Override
    public String toString() {
        return "-Articulos-->" + "nombre:" + nombre + ", peso:" + peso + ", fechaFabricacion:" + fechaFabricacion + ", " + super.toString();
    }

    //metodo que lee el fichero que se le pasa como parametro y devuelve una lista
    //de Articulos
    public ArrayList<Articulos> leerArticulo(String idFichero) {

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;

        // Inicialización del flujo "datosFichero" en función del archivo llamado "idFichero"
        // Estructura try-with-resources. Permite cerrar los recursos una vez finalizadas
        // las operaciones con el archivo
        try ( Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {
            // hasNextLine devuelve true mientras haya líneas por leer
            while (datosFichero.hasNextLine()) {
                // Guarda la línea completa en un String
                linea = datosFichero.nextLine();
                // Se guarda en el array de String cada elemento de la
                // línea en función del carácter separador de campos del fichero CSV
                Articulos tmp = new Articulos();
                tokens = linea.split(";");
                tmp.setIdProducto(tokens[0]);
                tmp.setPrecio(Integer.parseInt(tokens[1]));
                tmp.setNombre(tokens[2]);
                tmp.setPeso(Integer.parseInt(tokens[3]));
                tmp.setFechaFabricacion(LocalDate.parse(tokens[4], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                listaArticulos.add(tmp);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return listaArticulos;
    }

}
