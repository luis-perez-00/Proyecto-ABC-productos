package com.guatex.bodega.controlador;

import com.guatex.bodega.datos.DProductos;
import com.guatex.bodega.util.DataBase;
import com.guatex.bodega.vista.Menu;

/**
 * Programa para la creación de un archivo y manipulación de los datos dentro de
 * este archivo
 *
 * @author lperez
 * @since 2024-03-20
 */
public class Inicio {

    public static void main(String[] args) {

        // Constantes para mostrar en consola el tipo de operacion que se quiere efectuar
        final String EN_BUSQUEDA = "busqueda";
        final String EN_ELIMINAR = "eliminacion";
        final String EN_EDITAR = "modificacion";

        // Atributos
        int eleccionUsuario = 7;
        int porIdONombre;

        // Instancias de clases
        DataBase dataBase = new DataBase();
        DProductos datosDeProductos = new DProductos(dataBase.getInstanciaDB());
        Menu menu = new Menu();
        Acciones accion = new Acciones();

        // Validando la existencia de la DB
        if (!dataBase.existeDB()) {
            dataBase.crearDB();
            System.out.println("Archivo DB.gtx creado");
        }

        // Bucle encargado de la ejecución continua del programa hasta que el usuario lo finalice u ocurra una excepción
        while (eleccionUsuario != 6) {
            // Despliege del menu de acciones disponibles del usuario y la captura del valor seleccionado
            eleccionUsuario = menu.seleccionOperacion();
            // Evalua el caso correspondiente al valor seleccionado por el usuario
            switch (eleccionUsuario) {
                case 1: // Crear producto
                    accion.productoAgregar(datosDeProductos);
                    break;
                case 2: // Buscar producto
                    porIdONombre = menu.seleccionFormaDeOperar(EN_BUSQUEDA);
                    menu.desplegarEncabezado(porIdONombre);
                    accion.productoBuscar(datosDeProductos, porIdONombre);
                    accion.productoListarDatos(datosDeProductos, porIdONombre);
                    break;
                case 3: // Modificar producto
                    porIdONombre = menu.seleccionFormaDeOperar(EN_EDITAR);
                    menu.desplegarEncabezado(porIdONombre);
                    accion.productoBuscar(datosDeProductos, porIdONombre);
                    accion.productoActualizar(datosDeProductos, porIdONombre);
                    break;
                case 4: // Eliminar producto
                    porIdONombre = menu.seleccionFormaDeOperar(EN_ELIMINAR);
                    menu.desplegarEncabezado(porIdONombre);
                    accion.productoBuscar(datosDeProductos, porIdONombre);
                    accion.productoEliminar(datosDeProductos, porIdONombre);
                    break;
                case 5: // Listar productos
                    menu.encabezadoDeListar();
                    accion.productosListar(datosDeProductos);
                    break;
                case 6: // Salir
                    dataBase.actualizarDB(datosDeProductos.getLinkedListDatosProductos());
                    menu.msjAlSalir();
                    break;
                default:
                    // En caso de no seleccionar una opcion contemplada, se imprime en consola un mensaje por defecto
                    menu.msjPorDefecto();
            }
        }

    }
}
