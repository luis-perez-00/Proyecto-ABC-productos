package com.guatex.bodega.datos;

import com.guatex.bodega.entidad.EProducto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Clase encargada de interactuar directamente con la LinkedList que alamacena
 * todos los datos del archivo DB.gtx
 *
 * @author lperez
 */
public final class DProductos {

    // Instancia de la estructura de datos LinkedList
    private LinkedList<String> linkedListDatosDeProductos = new LinkedList<>();

    /**
     * Constructor de clase que carga los datos del archivo DB.gtx al LinkedList
     * linkedListDatosDeProductos
     *
     * @param instanciaArchivo instancia del archivo que contine la ruta donde
     * se encuentra el archivo DB.gtx
     */
    public DProductos(File instanciaArchivo) {
        this.linkedListDatosDeProductos = leerContenidoDB(instanciaArchivo);
    }

    /**
     * Método get que retorna la LinkedList con los datos del archivo DB.gtx
     *
     * @return la LinkedList con todos los datos leidos y cargados del archivo
     * DB.gtx
     */
    public LinkedList<String> getLinkedListDatosProductos() {
        return linkedListDatosDeProductos;
    }

    /**
     * Método encargado de leer linea por linea el contenido del archivo DB.gtx
     *
     *
     * @param instanciaArchivo instancia del archivo que contine la ruta donde
     * se encuentra el archivo DB.gtx
     * @return la LinkedList con todos los datos cargados del archivo DB.gtx
     */
    public LinkedList<String> leerContenidoDB(File instanciaArchivo) {

        LinkedList<String> lineasDelArchivo = new LinkedList<>();

        try {

            FileInputStream fileInput = new FileInputStream(instanciaArchivo);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInput));
            String lineas;
            while ((lineas = reader.readLine()) != null) {
                lineasDelArchivo.add(lineas);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado \n" + e);
        } catch (IOException e) {
            System.err.println("No se puede leer el archivo\n" + e);
        }
        return lineasDelArchivo;
    }

    /**
     * Método para agregar un nuevo producto de forma agregada a la LinkedList
     * existente con los datos cargado del archivo DB.gtx.
     *
     * @param datosNuevos una instancia de la entidad productos que contendrá
     * los valores de un nuevo producto a agregar
     */
    public void agregarDatos(EProducto datosNuevos) {
        getLinkedListDatosProductos().add(datosNuevos.getId());
        getLinkedListDatosProductos().add(datosNuevos.getNombre());
        getLinkedListDatosProductos().add(datosNuevos.getDescripcion());
        getLinkedListDatosProductos().add(String.valueOf(datosNuevos.getPrecioCompra()));
        getLinkedListDatosProductos().add(String.valueOf(datosNuevos.getPrecioVenta()));
        getLinkedListDatosProductos().add(String.valueOf(datosNuevos.getCantidad()));
        //System.out.println(getLinkedListDatosProductos());
        System.out.println("Producto agregado");
    }

    /**
     * Método encargado de actualizar únicamente aquellos datos que se puedan
     * actualizar de un producto.
     *
     * @param datosActualizados una instancia de la entidad productos que
     * contendrá los valores nuevos de un producto existente
     * @param indiceInicial indica el indice donde el método debe comenzar a
     * actualizar los datos de un producto
     */
    public void actualizarDatos(EProducto datosActualizados, int indiceInicial) {
        getLinkedListDatosProductos().set(indiceInicial, datosActualizados.getDescripcion());
        getLinkedListDatosProductos().set(++indiceInicial, String.valueOf(datosActualizados.getPrecioCompra()));
        getLinkedListDatosProductos().set(++indiceInicial, String.valueOf(datosActualizados.getPrecioVenta()));
        getLinkedListDatosProductos().set(++indiceInicial, String.valueOf(datosActualizados.getCantidad()));
        //System.out.println(getLinkedListDatosProductos());
        System.out.println("Producto modificado");
    }

    /**
     * Método encargado de eliminar un producto en su totalidad.
     *
     * @param desdeEsteIndice idica desde que indice debe comenzar a eliminar
     * el producto: desde el indice donde esta el ID
     * @param cantidadLineasPorProducto indica la cantidad de lineas que tiene
     * un producto en su totalidad para asegurase de eliminar un único producto
     * nada más
     */
    public void eliminarDatos(int desdeEsteIndice, int cantidadLineasPorProducto) {
        for (int i = 1; i <= cantidadLineasPorProducto; i++) {
            getLinkedListDatosProductos().remove(desdeEsteIndice);
        }
        System.out.println("El producto fue eliminado");
    }

}
