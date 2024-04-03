package com.guatex.bodega.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Clase encargada de manipular directamente el archivo DB.gtx, comprueba la
 * existencia del archivo, crea el archivo y actualiza el archivo.
 * <br>
 * Ruta en la cual se realizan estas acciones:
 * <br>
 * <b>
 *  C:\Users\USUARIO_ACTUAL\Documents\DB.gtx
 * </b>
 *
 * @author lperez
 *
 */
public class DataBase {

    // Constantes
    private final String SEPARADOR = File.separator;
    private final String RAIZ = "C:";
    /* 
        Otra forma de obtener el nombre de usuario que tiene la sesión iniciada:
        private final String USUARIO_ACTUAL = new com.sun.security.auth.module.NTSystem().getName();
     */
    private final String USUARIO_ACTUAL = System.getProperty("user.name");
    private final String RUTADB = RAIZ
            + SEPARADOR + "Users"
            + SEPARADOR + USUARIO_ACTUAL
            + SEPARADOR + "Documents"
            + SEPARADOR + "DB.gtx";
    
    
    private File instanciaDB = null;

    /**
     * Método para comprobar la existencia del archivo DB.gtx:
     * <br>
     * (true = existe | false = no existe).
     *
     * @return boolean para indicar la existencia del archivo.
     */
    public boolean existeDB() {
        boolean existeDB;
        instanciaDB = new File(RUTADB);
        existeDB = instanciaDB.exists();
        return existeDB;
    }

    /**
     * Método para la creación del archivo DB.gtx
     * <br>
     * Escribe en el archivo un valor "" que es equivalente a crear un archivo vacío.
     *
     * @see FileWriter
     * 
     */
    public void crearDB() {
        try (FileWriter archivo = new FileWriter(instanciaDB)) {
            archivo.write("");
            //System.out.println("Base de datos Creada");
        } catch (FileNotFoundException fileNotFound) {
            System.err.println("No fue posible crear el archivo: ruta no encontrada \n" + fileNotFound);
        } catch (IOException IOerror) {
            System.err.println("No fue posible crear el archivo \n" + IOerror);
        }
    }

    /**
     * Método para eliminar el archivo DB.gtx y reescribirlo con datos nuevos.
     * <br>
     * Elimina el archivo con los datos antiguos y crea otro en la misma ruta añadiendo los datos nuevos.
     *
     * @param datosNuevos   parametro que contiene los datos nuevos que se añadirán en el nuevo archivo
     * @see                 FileWriter
     * @see                 LinkedList
     * 
     */
    public void actualizarDB(LinkedList<String> datosNuevos) {
        eliminarDB();
        int cantidadLineas = datosNuevos.size();
        int contador = 0;
        try (FileWriter archivo = new FileWriter(instanciaDB)) {
            archivo.write(datosNuevos.get(contador));
            ++contador;
            while (contador < cantidadLineas) {
                archivo.write("\n" + datosNuevos.get(contador));
                contador++;
            }
            System.out.println("Datos guardados");
            datosNuevos.clear();
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("No fue posible crear el archivo: ruta no encontrada \n" + fileNotFound);
        } catch (IndexOutOfBoundsException fueraDeIndice) {
            System.out.println("No existe ningun valor para guardar en la DB");
        } catch (IOException IOerror) {
            System.out.println("No fue posible crear el archivo \n" + IOerror);
        }
    }

    /**
     * Método   para eliminar el archivo DB.gtx
     * 
     */
    public void eliminarDB() {
        instanciaDB.delete();
    }

    /**
     * Método get para retornar la instancia del archivo DB.gtx (intancia de clase File)
     *
     * @return  Instancia de clase File que contiene distintos datos del archivo DB.gtx entre estos la ruta del archivo
     * @see     File 
     */
    public File getInstanciaDB() {
        return instanciaDB = new File(RUTADB);
    }

}
