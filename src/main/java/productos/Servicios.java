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
public class Servicios extends Productos {

    private String nombre;
    private int horas;
    private LocalDate fechaComienzo;
    private LocalDate fechaFin;
    private ArrayList<Servicios> listaServicios;

    public Servicios() {
        listaServicios = new ArrayList<>();
    }

    public Servicios(String nombre, int horas, LocalDate fechaComienzo, LocalDate fechaFin, String idProducto, double precio) {
        super(idProducto, precio);
        this.nombre = nombre;
        this.horas = horas;
        this.fechaComienzo = fechaComienzo;
        this.fechaFin = fechaFin;
        listaServicios = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public LocalDate getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(LocalDate fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return  "-Servicios-->" + "nombre:" + nombre + ", horas:" + horas + ", fechaComienzo:" + fechaComienzo + ", fechaFin:" + fechaFin + ","+super.toString();
    }

    public ArrayList<Servicios> leerServicio(String idFichero) {

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;


        // Inicialización del flujo "datosFichero" en función del archivo llamado "idFichero"
        // Estructura try-with-resources. Permite cerrar los recursos una vez finalizadas
        // las operaciones con el archivo
        try (Scanner datosFichero = new Scanner(new File(idFichero), "ISO-8859-1")) {
            // hasNextLine devuelve true mientras haya líneas por leer
            while (datosFichero.hasNextLine()) {
                // Guarda la línea completa en un String
                linea = datosFichero.nextLine();
                // Se guarda en el array de String cada elemento de la
                // línea en función del carácter separador de campos del fichero CSV
                Servicios tmp = new Servicios();
                tokens = linea.split(";");
                tmp.setIdProducto(tokens[0]);
                tmp.setPrecio(Integer.parseInt(tokens[1]));
                tmp.setNombre(tokens[2]);
                tmp.setHoras(Integer.parseInt(tokens[3]));
                tmp.setFechaComienzo(LocalDate.parse(tokens[4], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                tmp.setFechaFin(LocalDate.parse(tokens[5], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                listaServicios.add(tmp);
//                for (String string : tokens) {
//                    System.out.print(string + "\t");
//                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return listaServicios;
    }
}
