package com.guatex.bodega.controlador;

import com.guatex.bodega.datos.DProductos;
import com.guatex.bodega.entidad.EProducto;
import com.guatex.bodega.formularios.FProducto;
import java.util.Scanner;

/**
 * Clase encargada de efectuar la lectura, escritura, edición y eliminación de
 * datos en el LinkedList donde se alamcenan los datos extraidos posteriormente
 * almacenados en el archivo DB.gtx.
 *
 * @author lperez
 * @see DProductos
 * @see EProducto
 * @see FProducto
 */
public class Acciones {

    // Constantes
    private final boolean ES_ACTUALIZAR = true;
    private final int CANTIDAD_LINEAS_POR_PRODUCTO = 6;
    private final int BUSCAR_POR_ID = 2;
    private final int BUSCAR_POR_NOMBRE = 1;
    private final int VALOR_NO_ENCONTRADO = -1;

    // Atributos
    private int indiceValorBuscado = VALOR_NO_ENCONTRADO;

    // Instancias de clases
    FProducto productoForm = new FProducto();
    Scanner sc = new Scanner(System.in);

    /**
     * Método get para el retorno del valor del indice del valor que se esta
     * buscando en el LinkedList
     *
     * @return el indice del valor que se esta buscando en el LinkedList, si el
     * valor es -1 indica que no se encontro el valor
     */
    public int getIndiceValorBuscado() {
        return indiceValorBuscado;
    }

    /**
     * Método set para asignar el valor del <b>indice del valor buscado</b> en
     * el LinkedList
     *
     * @param indiceValorBuscado el nuevo valor a asignar al indiceValorBuscado
     */
    public void setIndiceValorBuscado(int indiceValorBuscado) {
        this.indiceValorBuscado = indiceValorBuscado;
    }

    /**
     * Método encargado de listar todos los productos encontrados en la
     * LinkedList cargada desde la clase DProductos
     *
     * @param productosDatos instancia de tipo Dproductos para obtener la
     * LinkedList y así obtener los datos de los productos e imprimirlos en
     * consola
     */
    public void productosListar(DProductos productosDatos) {
        int cantidadLineas = productosDatos.getLinkedListDatosProductos().size();
        System.out.println("Cantidad de lineas en el archivo: " + cantidadLineas);
        int contadorLineas = 0;

        if (cantidadLineas == 0) {
            System.out.println("----------- No hay productos ----------- ");
        } else if (cantidadLineas != 0) {
            System.out.println("----------- Producto 1 ----------- ");
        }

        int productoNo = 2; // inicia en 2 porque el producto 1 (si existe solo 1) ya se ha mostrado
        for (int i = 1; i <= cantidadLineas; i++) {
            System.out.println(productosDatos.getLinkedListDatosProductos().get(i - 1));
            contadorLineas++;
            if (contadorLineas % CANTIDAD_LINEAS_POR_PRODUCTO == 0 && (contadorLineas < cantidadLineas - 1)) {
                System.out.println("----------- Producto " + productoNo + " ----------- ");
                productoNo++;
            }
        }
        int totalProductos = contadorLineas / 6;
        System.out.println("------------------------------------"
                + "\nEl total de productos es de: " + totalProductos);
    }

    /**
     * Método encargado de listar la información de un único producto que el
     * usuario haya seleccionado por medio del nombre o del id del producto
     *
     * @param datoProducto instancia de tipo Dproductos para obtener la
     * LinkedList y así obtener los datos de un producto especifico e
     * imprimirlos en consola
     * @param tipoSeleccion utilizado para determinar si el tipo de selección
     * realizada por el usuario fue por el nombre para modificar el valor del
     * indice del producto a imprimir
     */
    public void productoListarDatos(DProductos datoProducto, int tipoSeleccion) {
        int indice = getIndiceValorBuscado();
        if (getIndiceValorBuscado() == VALOR_NO_ENCONTRADO) {
            System.out.println("No hay información del producto");
        } else {
            // Si el indice del producto a listar apunta al nombre, se disminuye en uno para apuntar al indice del ID del producto
            if (tipoSeleccion == BUSCAR_POR_NOMBRE) {
                indice--;
            }
            // Se recorren todas las líneas del producto que contienen toda su información
            for (int i = 0; i < CANTIDAD_LINEAS_POR_PRODUCTO; i++) {
                System.out.println(datoProducto.getLinkedListDatosProductos().get((indice + i)));
            }
        }
    }

    /**
     * Método encargado de agregar productos al LinkedList.
     * <br>
     * Realiza las comprobaciones de id únicos y nombre únicos para proceder con
     * la operación.
     *
     * @param productosDatos instancia de tipo Dproductos para obtener la
     * LinkedList y así actualizarla con los datos ingresados por el usuario
     */
    public void productoAgregar(DProductos productosDatos) {
        EProducto datosAgregar = productoForm.formNuevoProducto(!ES_ACTUALIZAR);

        boolean existeID = existeProducto(productosDatos, datosAgregar.getId());
        boolean existeNombre = existeProducto(productosDatos, datosAgregar.getNombre());

        if (!existeID && !existeNombre) {
            productosDatos.agregarDatos(datosAgregar);
            System.out.println("Operación exitosa");
        }

        if (existeID) {
            System.out.println("El ID ingresado ya existe");
        } else if (existeNombre) {
            System.out.println("El nombre ingresado ya existe");
        }
    }

    /**
     * Método encargado de actualizar un producto seleccionado por medio del
     * nombre o id.
     *
     * @param productosDatos instancia de tipo Dproductos para obtener la
     * LinkedList y así actualizarla con los datos ingresados por el usuario
     * @param porIdONombre utilizado para determinar si el tipo de selección
     * realizada por el usuario fue por el nombre para modificar el valor del
     * indice del producto a modificar
     */
    public void productoActualizar(DProductos productosDatos, int porIdONombre) {
        int indiceInicial = getIndiceValorBuscado();

        if (getIndiceValorBuscado() == VALOR_NO_ENCONTRADO) {
            System.out.println("La entrada no coincide con ningun valor");
        } else if (getIndiceValorBuscado() != VALOR_NO_ENCONTRADO) {
            // Si el indice del producto a actualizar apunta al nombre, se disminuye en uno para apuntar al indice del ID del producto
            if (porIdONombre == BUSCAR_POR_NOMBRE) {
                indiceInicial--;
            }
            // Para evitar la modificación del ID y el nombre se ingresan datos a partir del segundo indice (descripción del producto)
            indiceInicial = indiceInicial + 2;
            productosDatos.actualizarDatos(productoForm.formNuevoProducto(ES_ACTUALIZAR), indiceInicial);
        }
    }

    /**
     * Método encargado de eliminar un producto seleccionado por medio del
     * nombre o id.
     *
     * @param productosDatos instancia de tipo Dproductos para obtener la
     * LinkedList y así eliminar el producto seleccionado por el usuario
     * @param porIdONombre utilizado para determinar si el tipo de selección
     * realizada por el usuario fue por el nombre para modificar el valor del
     * indice del producto a eliminar
     */
    public void productoEliminar(DProductos productosDatos, int porIdONombre) {
        int indiceInicial = getIndiceValorBuscado();

        if (getIndiceValorBuscado() == VALOR_NO_ENCONTRADO) {
            System.out.println("La entrada no coincide con ningun valor");
        } else if (getIndiceValorBuscado() != VALOR_NO_ENCONTRADO) {
            // Si el indice del producto a eliminar apunta al nombre, se disminuye en uno para apuntar al indice del ID del producto
            if (porIdONombre == BUSCAR_POR_NOMBRE) {
                indiceInicial--;
            }
            productosDatos.eliminarDatos(indiceInicial, CANTIDAD_LINEAS_POR_PRODUCTO);
        }
    }

    /**
     * Método encargado de buscar un producto por medio del nombre o id.
     *
     * @param productosDatos instancia de tipo Dproductos para obtener la
     * LinkedList y así bucar el producto seleccionado por el usuario
     * @param porIdONombre utilizado para determinar si el tipo de selección
     * realizada por el usuario fue por el nombre o por el id buscar el valor
     * ingresado
     */
    public void productoBuscar(DProductos productosDatos, int porIdONombre) {
        if (getIndiceValorBuscado() != VALOR_NO_ENCONTRADO) {
            setIndiceValorBuscado(VALOR_NO_ENCONTRADO);
        }

        // Para evitar errores de entrada de datos capturo el valor a buscar aquí directamente
        String valorABuscar = sc.nextLine();

        int cantidadDatos = productosDatos.getLinkedListDatosProductos().size();
        int primerIndice = 0;
        int indiceInicioElemento = 12;
        boolean nombreEnPrimerElemento = (porIdONombre == BUSCAR_POR_NOMBRE
                && productosDatos.getLinkedListDatosProductos().get(primerIndice + 1).equals(valorABuscar));
        boolean idEnPrimerElemento = (porIdONombre == BUSCAR_POR_ID
                && productosDatos.getLinkedListDatosProductos().get(primerIndice).equals(valorABuscar));

        // Validando si el producto buscado es el primero y si es así imprimir en consola
        if (cantidadDatos == 0) {
        } else {
            if (idEnPrimerElemento) {
                setIndiceValorBuscado(primerIndice);
                System.out.println("ID encontrado en el primer producto");
                primerIndice++;
            } else if (nombreEnPrimerElemento) {
                setIndiceValorBuscado(primerIndice + 1);
                System.out.println("Nombre encontrado en el primer producto");
                primerIndice++;
            }
        }

        // Buscando en la totalidad del tamaño del LinkedList
        if (porIdONombre == BUSCAR_POR_ID && cantidadDatos > 11 && primerIndice == 0) {
            for (int i = 7; i < cantidadDatos; i++) {
                if (indiceInicioElemento % 6 == 0
                        && productosDatos.getLinkedListDatosProductos().get(i - 1).equals(valorABuscar)) {
                    setIndiceValorBuscado(i - 1);
                    break;
                }
            }
        } else if (porIdONombre == BUSCAR_POR_NOMBRE && cantidadDatos > 11 && primerIndice == 0) {
            for (int i = 6; i < cantidadDatos; i++) {
                if (i % 6 == 0
                        && productosDatos.getLinkedListDatosProductos().get(++i).equals(valorABuscar)) {
                    setIndiceValorBuscado(i);
                    break;
                }
            }
        }

        // Mensaje por defecto si no coincide el valor ingresado con ningun valor en la LinkedList y sus criterios
        if (getIndiceValorBuscado() == VALOR_NO_ENCONTRADO) {
            System.out.println("La entrada no coincide con ningun valor");
        }
    }

    /**
     * Método encargado de verificar la existencia de un producto haciendo uso
     * de un valor ingresado por el usuario
     *
     * @param productosDatos instancia de tipo Dproductos para obtener la
     * LinkedList y así bucar el producto seleccionado por el usuario
     * @param dato valor por el cual buscar en la LinkedList y comprobar si
     * existe tal valor
     * @return (true = existe el producto | false = no existe el producto)
     */
    public boolean existeProducto(DProductos productosDatos, String dato) {
        int cantidadDatos = productosDatos.getLinkedListDatosProductos().size();

        if (cantidadDatos != 0) {
            if (productosDatos.getLinkedListDatosProductos().get(0).equals(dato)
                    || productosDatos.getLinkedListDatosProductos().get(1).equals(dato)) {
                return true;
            }
        }

        for (int i = 1; i < cantidadDatos; i++) {
            if (i % CANTIDAD_LINEAS_POR_PRODUCTO == 0 /*|| i % (CANTIDAD_LINEAS_POR_PRODUCTO + 1) == 0*/) {
                if (productosDatos.getLinkedListDatosProductos().get(i).equals(dato)) {
                    return true;
                } else if (productosDatos.getLinkedListDatosProductos().get(++i).equals(dato)) {
                    return true;
                }
                --i;
            }
        }
        return false;
    }

}
