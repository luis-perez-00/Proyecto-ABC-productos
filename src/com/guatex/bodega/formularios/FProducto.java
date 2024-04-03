package com.guatex.bodega.formularios;

import com.guatex.bodega.entidad.EProducto;
import java.util.Scanner;

/**
 * Clase encargada de la entrada de datos correspondientes a un producto a
 * insertar o a editar u otras acciones en relación a la inserción de datos.
 *
 * @author lperez
 */
public class FProducto {

    // Atributos para almacenar los valores para la clase EProductos
    private String productoId;
    private String productoNombre;
    private String productoDesc;
    private double productoPrecioCompra;
    private double productoPrecioVenta;

    // Otros atributos
    private int productoCantidad;
    private int cantidadAgregados = 0;
    private int cantidadEditados = 0;

    // Instancias de clases
    Scanner sc = new Scanner(System.in);
    EProducto producto = new EProducto();

    /**
     * Método encargado del la inserción de los datos correspondientes de un
     * producto
     * <br>
     * Maneja sus propias excepciones y utiliza la clase EProducto para ingresar
     * datos de un producto
     * <br>
     * Se reutiliza para la inserción de datos de un producto a actualizar
     *
     * @param esActualizar parámetro utilizado para distintas validaciones
     * dentro del método
     * @return una instancia de la entidad producto que contiene los datos
     * ingresados por el usuario
     * @see Eproducto
     */
    public EProducto formNuevoProducto(boolean esActualizar) {

        if (!esActualizar) {
            System.out.println("Ingrese el ID del producto (Debe ser un valor único):");
            if (cantidadAgregados > 0 || (!esActualizar && cantidadEditados > 0)) {
                sc.nextLine(); // Limpieza buffer (memoria reservada para capturar los datos entrantes de los perifericos
            }
            productoId = sc.nextLine();
            producto.setId(productoId);
            cantidadAgregados++;
        }

        if (!esActualizar) {
            System.out.println("Ingrese el nombre del producto:");
            if (esActualizar && cantidadAgregados > 0 || (esActualizar && cantidadEditados > 0)) {
                sc.nextLine(); // Limpieza del buffer
            }
            productoNombre = sc.nextLine();
            producto.setNombre(productoNombre);
        }

        System.out.println("Ingrese la descripción del producto:");
        if (esActualizar && cantidadAgregados > 0 || (esActualizar && cantidadEditados > 0)) {
            sc.nextLine(); // Limpieza del buffer
        }
        productoDesc = sc.nextLine();
        producto.setDescripcion(productoDesc);
        if (cantidadAgregados == 0) {
            cantidadEditados++;
        }

        System.out.println("Ingrese el precio de compra del producto:");
        // Controlando la excepcion ocurrida si se ingresa un valor distinto a un double
        while (true) {
            try {
                productoPrecioCompra = sc.nextDouble();
                break;
            } catch (java.util.InputMismatchException e) {
                sc.nextLine();
                System.out.println("¡Entrada invalida! \n" + "Ingrese el dato nuevamente:");
            }
        }
        producto.setPrecioCompra(productoPrecioCompra);

        System.out.println("Ingrese el precio de venta del producto:");
        // Controlando la excepcion ocurrida si se ingresa un valor distinto a un double
        while (true) {
            try {
                productoPrecioVenta = sc.nextDouble();
                break;
            } catch (java.util.InputMismatchException e) {
                sc.nextLine();
                System.out.println("¡Entrada invalida! \n" + "Ingrese el dato nuevamente:");
            }
        }
        producto.setPrecioVenta(productoPrecioVenta);

        System.out.println("Ingrese la cantidad del producto:");
        // Controlando la excepcion ocurrida si se ingresa un valor distinto a un int
        while (true) {
            try {
                productoCantidad = sc.nextInt();
                break;
            } catch (java.util.InputMismatchException e) {
                sc.nextLine();
                System.out.println("¡Entrada invalida! \n" + "Ingrese el dato nuevamente:");
            }
        }
        producto.setCantidad(productoCantidad);

        return producto;
    }
}
