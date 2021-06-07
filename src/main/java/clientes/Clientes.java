/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;

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
public class Clientes {

    private String nombre;
    private String apellidos;
    private String nifCliente;
    private String direccion;
    private ArrayList<Clientes> listaClientes;

    public Clientes() {
        listaClientes = new ArrayList<>();
    }

    public Clientes(String nombre, String apellidos, String nifCliente, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nifCliente = nifCliente;
        this.direccion = direccion;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNifCliente() {
        return nifCliente;
    }

    public void setNifCliente(String nifCliente) {
        this.nifCliente = nifCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Clientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public String toString() {
        return "Clientes{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", nifCliente=" + nifCliente + ", direccion=" + direccion +'}';
    }

   
    

    public  ArrayList<Clientes> leerCliente(String idFichero) {

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;

//        System.out.println("Leyendo el fichero: " + idFichero);

        // Inicialización del flujo "datosFichero" en función del archivo llamado "idFichero"
        // Estructura try-with-resources. Permite cerrar los recursos una vez finalizadas
        // las operaciones con el archivo
        try ( Scanner datosFichero = new Scanner(new File(idFichero), "ISO-8859-1")) {
            // hasNextLine devuelve true mientras haya líneas por leer
            while (datosFichero.hasNextLine()) {
                // Guarda la línea completa en un String
                linea = datosFichero.nextLine();
                // Se guarda en el array de String cada elemento de la
                // línea en función del carácter separador de campos del fichero CSV
                Clientes tmp = new Clientes();
                tokens = linea.split(";");
                tmp.setNombre(tokens[0]);
                tmp.setApellidos(tokens[1]);
                tmp.setNifCliente(tokens[2]);
                tmp.setDireccion(tokens[3]);
                listaClientes.add(tmp);
//                for (String string : tokens) {
//                    System.out.print(string + "\t");
//                }
//                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return listaClientes;
    }
}