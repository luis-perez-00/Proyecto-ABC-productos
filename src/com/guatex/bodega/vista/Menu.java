package com.guatex.bodega.vista;

import java.util.Scanner;

/**
 * Clase encargada de imprimir en consola mensajes correspondientes a distintos
 * casos ocurridos en la ejecución del programa.
 * <br>
 * Proporciona métodos para el retorno y captura de datos para la toma de
 * decisiones presentadas al usuario.
 *
 * @author lperez
 */
public class Menu {

    // Constantes
    private final int SELECCION_POR_NOMBRE = 1;
    private final int SELECCION_POR_ID = 2;

    // Atributos
    private int seleccion;

    // Intancias de clases
    Scanner sc = new Scanner(System.in);

    /**
     * Método a cargo de listar las opciones disponibles para el usuario,
     * capturar el valor ingresado, controlar las excepciones y retornar el
     * valor ingresado si es uno valido.
     *
     * @return el valor correspondiente a la decisión del usuario
     */
    public int seleccionOperacion() {
        System.out.println("------------ Bienvenido ------------ \nSelecciona una opción:");
        System.out.println(
                "1) Insertar \n2) Buscar \n3) Modificar \n4) Eliminar \n5) Listar \n6) Salir"
        );
        // Controlando la excepcion ocurrida si se ingresa un valor distinto a un int
        while (true) {
            try {
                seleccion = sc.nextInt();
                break;
            } catch (java.util.InputMismatchException e) {
                sc.nextLine();
                System.out.println("¡Entrada invalida! \n" + "Ingrese el dato nuevamente:");
            }
        }
        return seleccion;
    }

    /**
     * Método a cargo de listar las opciones disponibles para realizar un
     * determinado tipo de operación definida en el parámetro tipoOperacion.
     *
     * @param tipoOperacion paramétro utilizado para imprimir en consola el tipo
     * de operación que se esta llevando a cabo
     * @return el valor correspondiente a la decisión del usuario
     */
    public int seleccionFormaDeOperar(String tipoOperacion) {
        System.out.println("------------ Selecciona la forma para realizar la " + tipoOperacion + " del producto ------------ ");
        System.out.println(
                "1) Nombre del producto \n2) Id"
        );
        // Controlando la excepcion ocurrida si se ingresa un valor distinto a un int
        while (true) {
            try {
                seleccion = sc.nextInt();
                break;
            } catch (java.util.InputMismatchException e) {
                sc.nextLine();
                System.out.println("¡Entrada invalida! \n" + "Ingrese el dato nuevamente:");
            }
        }
        if (seleccion > 0 && seleccion < 3) {
            return seleccion;
        }
        return 1;
    }

    /**
     * Método encargado de imprimir en consola el encabezado correspondiente a
     * la opción introducida por el usuario
     *
     * @param porIdONombre utilizado para imprimir el encabezado de la acción
     * correspondiente a la elección del usuario
     */
    public void desplegarEncabezado(int porIdONombre) {
        if (porIdONombre == SELECCION_POR_ID) {
            encabezadoAccionPorId();
        } else if (porIdONombre == SELECCION_POR_NOMBRE) {
            encabezadoAccionPorNombre();
        }
    }

    /**
     * Método encargado de imprimir en consola el resultado de una operación
     * realizada
     *
     * @param operacionExitosa valor utilizado para indicar si una operación fue
     * exitosa o fallida
     */
    public void desplegarEstatusOperacion(boolean operacionExitosa) {
        if (operacionExitosa) {
            msjOperacionExitosa();
        } else if (!operacionExitosa) {
            msjOperacionFallida();
        }
    }

    /**
     * Método encargado de imprimir en consola el encabezado de una operación de
     * tipo inserción de id para marcar el inicio de la introducción de datos
     * por parte del usuario
     */
    public void encabezadoAccionPorId() {
        System.out.println("------------ Introduce el ID del producto: ------------ ");
    }

    /**
     * Método encargado de imprimir en consola el encabezado de una operación de
     * tipo inserción de nombre del producto para marcar el inicio de la
     * introducción de datos por parte del usuario
     */
    public void encabezadoAccionPorNombre() {
        System.out.println("------------ Introduce el nombre del producto: ------------ ");
    }

    /**
     * Método encargado de imprimir en consola el encabezado de una operación de
     * tipo inserción de datos del producto para marcar el inicio de la
     * introducción de datos por parte del usuario
     */
    public void encabezadoDeAgregar() {
        System.out.println("------------ Introduce los datos del producto: ------------ ");
    }

    /**
     * Método encargado de imprimir en consola el encabezado de una operación de
     * tipo inserción de datos para modificar un producto para marcar el inicio
     * de la introducción de datos por parte del usuario
     */
    public void encabezadoDeModificar() {
        System.out.println("------------ Introduce los datos a modificar: ------------ ");
    }

    /**
     * Método encargado de imprimir en consola el encabezado de una operación de
     * tipo listar los datos de los productos
     */
    public void encabezadoDeListar() {
        System.out.println("------------ Lista de productos: ------------ ");
    }

    /**
     * Método encargado de imprimir en consola un mensaje por defecto
     */
    public void msjPorDefecto() {
        System.out.println("Opción invalida");
    }

    /**
     * Método encargado de imprimir en consola un mensaje de depedida hacia el
     * usuario que finaliza el programa
     */
    public void msjAlSalir() {
        System.out.println("Hasta pronto :D");
    }

    /**
     * Método encargado de imprimir en consola un mensaje de operación exitosa
     */
    public void msjOperacionExitosa() {
        System.out.println("----------------------- " + "\nOperacion exitosa");
    }

    /**
     * Método encargado de imprimir en consola un mensaje de operación fallida
     */
    public void msjOperacionFallida() {
        System.out.println("----------------------- " + "\nOperacion fallida");
    }

}
