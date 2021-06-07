/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author aguil
 */
public class Articulos extends Productos {

    private String nombre;
    private int peso;
    private LocalDate fechaFabricacion;
    private ArrayList<Articulos> listaArticulos;

    public Articulos() {
        listaArticulos = new ArrayList<>();
    }

    public Articulos(String nombre, int peso, LocalDate fechaFabricacion, ArrayList<Articulos> listaArticulos, int idProducto, int precio) {
        super(idProducto, precio);
        this.nombre = nombre;
        this.peso = peso;
        this.fechaFabricacion = fechaFabricacion;
        this.listaArticulos = listaArticulos;
    }

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
        return "Articulos{" + "nombre=" + nombre + ", peso=" + peso + ", fechaFabricacion=" + fechaFabricacion + ", listaArticulos=" + listaArticulos + '}';
    }

    public ArrayList<Articulos> leerArticulo(String idFichero) {

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;

        System.out.println("Leyendo el fichero: " + idFichero);

        // Inicialización del flujo "datosFichero" en función del archivo llamado "idFichero"
        // Estructura try-with-resources. Permite cerrar los recursos una vez finalizadas
        // las operaciones con el archivo
        try (Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {
            // hasNextLine devuelve true mientras haya líneas por leer
            while (datosFichero.hasNextLine()) {
                // Guarda la línea completa en un String
                linea = datosFichero.nextLine();
                // Se guarda en el array de String cada elemento de la
                // línea en función del carácter separador de campos del fichero CSV
                Articulos tmp = new Articulos();
                tokens = linea.split(";");
                tmp.setNombre(tokens[0]);
                tmp.setPeso(Integer.parseInt(tokens[1]));
                tmp.setFechaFabricacion(LocalDate.parse(tokens[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                listaArticulos.add(tmp);
//                for (String string : tokens) {
//                    System.out.print(string + "\t");
//                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return listaArticulos;
    }

}
