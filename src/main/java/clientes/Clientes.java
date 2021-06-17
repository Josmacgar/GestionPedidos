/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;

import Almacen.Empresa;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class Clientes {

    //atributos
    private String nombre;
    private String apellidos;
    private String nifCliente;
    private String direccion;
    private ArrayList<Clientes> listaClientes;
    private static Scanner teclado = new Scanner(System.in);

    //constructores
    public Clientes() {
        listaClientes = new ArrayList<>();
    }

    public Clientes(String nombre, String apellidos, String nifCliente, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nifCliente = nifCliente;
        this.direccion = direccion;
    }

    //getters, setters y toString
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
        return "nombre:" + nombre + ", apellidos:" + apellidos + ", nifCliente:" + nifCliente + ", direccion:" + direccion;
    }

    //metodo que lee el fichero que se le pasa como parametro y devuelve una lista
    //de Clientes
    public ArrayList<Clientes> leerCliente(String idFichero) {

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
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return listaClientes;
    }

    //metodo que muestra los clientes de la empresa
    public static void mostrarCliente(Empresa empresa1) {
        System.out.println("Ha elegido consultar");
        //for para imprimir los clientes
        for (Clientes c : empresa1.getListaClientes()) {
            System.out.println(c);
        }
    }

    //metodo que modifica los clientes de la empresa
    public static void modificarCliente(Empresa empresa1) {
        System.out.println("Ha elegido modificar");
        //for para imprimir los clientes
        for (Clientes c : empresa1.getListaClientes()) {
            System.out.println(c);
        }
        System.out.println("¿Que clientes de los anteriores quiere "
                + "modificar?");
        String nifCliente = teclado.nextLine();

        int comprobacion = 0;
        //for para modificar el cliente
        for (Clientes c : empresa1.getListaClientes()) {
            //if para comprobar el nombre del cliente
            if (nifCliente.equals(c.getNifCliente())) {
                //se modifica el nombre, apellidos y direccion
                System.out.println("Introduce el nombre:");
                String nombre = teclado.nextLine();
                c.setNombre(nombre);

                System.out.println("Introduce los apellidos:");
                String apellidos = teclado.nextLine();
                c.setApellidos(apellidos);

                System.out.println("Introduce el nif:");
                String nif = teclado.nextLine();
                c.setNifCliente(nif);

                System.out.println("Introduce la direccion:");
                String direccion = teclado.nextLine();
                c.setDireccion(direccion);
                comprobacion = 1;
            }
        }
        if (comprobacion == 0) {
            System.out.println("No se ha encontrado el cliente");
        }
    }

    //metodo que añade clientes a la empresa
    public static void añadirCliente(Empresa empresa1) {
        System.out.println("Ha elegido añadir");
        System.out.println("Introduce el nombre:");
        String nombre = teclado.nextLine();
        System.out.println("Introduce los apellidos:");
        String apellidos = teclado.nextLine();
        System.out.println("Introduce el nif:");
        String nif = teclado.nextLine();
        System.out.println("Introduce la direccion:");
        String direccion = teclado.nextLine();

        int nifExist = 0;
        //for para comprobar si el dni a meter ya existe
        for (Clientes cli : empresa1.getListaClientes()) {
            if (nif.equals(cli.getNifCliente())) {
                nifExist = 1;
            }
        }
        //if para meter el cliente en caso de que el nif no exista
        if (nifExist == 0) {
            empresa1.getListaClientes().add(new Clientes(nombre, apellidos, nif, direccion));
            System.out.println("Se ha añadido el cliente");
        } else {
            System.out.println("No se ha añadido el cliente");
        }
    }

    //meodo que borra clientes de la empresa
    public static void borrarClientes(Empresa empresa1) {
        System.out.println("Ha elegido borrar");
        for (Clientes cli : empresa1.getListaClientes()) {
            System.out.println(cli);
        }
        System.out.println("Escribe el nif del cliente que "
                + "desea borrar");
        String nifBorrar = teclado.nextLine();

        int numeroBorrar = 0;
        int existCliente = 0;
        //for que recorre la lista de clientes y si es igual actualiza la
        //variable igualandola a la posicion de la lista para luego eliminarla
        for (int i = 0; i < empresa1.getListaClientes().size(); i++) {
            if (nifBorrar.equals(empresa1.getListaClientes().get(i).getNifCliente())) {
                numeroBorrar = i;
                existCliente = 1;
            }
        }
        empresa1.getListaClientes().remove(numeroBorrar);
        if (existCliente == 1) {
            System.out.println("se ha eliminado el cliente");
        } else {
            System.out.println("El cliente no existe");
        }
    }

    //metodo que restarura la copia de seguridad de los clientes de la empresa
    public static void restarurarCopiaClientes(Empresa empresa1, String copiaEleccion) throws IOException {
        ObjectMapper mapeadorLectura = new ObjectMapper();
        String datos = "./backup/" + copiaEleccion + "/backupClientes.json";
        System.out.println(datos);
        empresa1.setListaClientes(mapeadorLectura.readValue(new File(datos),
                mapeadorLectura.getTypeFactory().constructCollectionType(ArrayList.class, Clientes.class)));
    }
}
