package com.guatex.bodega.entidad;

/**
 * Clase que define los datos que constituyen cada producto
 * <br>
 * Contiene los métodos set y get correspondientes para acceder a cada valor y
 * manipularlo fuera de esta clase
 *
 * @author lperez
 */
public class EProducto {

    // Atributos que constituyen cada producto
    private String id;
    private String nombre;
    private String descripcion;
    private double precioCompra;
    private double precioVenta;
    private int cantidad;

    /**
     * Método get para retornar el valor del <b>ID</b> de un producto
     *
     * @return valor <b>String id</b> del producto
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Método set para asignar el valor <b>ID</b> a un producto
     *
     * @param id identificador único del producto
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Método get para retornar el valor del <b>NOMBRE</b> de un producto
     *
     * @return valor <b>String nombre</b> del producto
     *
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método set para asignar el valor <b>NOMBRE</b> a un producto
     *
     * @param nombre nombre único del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método get para retornar el valor de la <b>DESCRIPCION</b> de un producto
     *
     * @return valor <b>String descripcion</b> del producto
     *
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método set para asignar el valor de la <b>DESCRIPCION</b> a un producto
     *
     * @param descripcion descripción breve o detallada de un producto
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Método get para retornar el valor del <b>PRECIO DE COMPRA</b> de un
     * producto
     *
     * @return valor <b>double precioCompra</b> del producto
     *
     */
    public double getPrecioCompra() {
        return precioCompra;
    }

    /**
     * Método set para asignar el valor de <b>PRECIO COMPRA</b> a un producto
     * 
     * @param precioCompra valor por el cual cual se va a comprar el producto
     */
    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    /**
     * Método get para retornar el valor del <b>PRECIO DE VENTA</b> de un
     * producto
     *
     * @return valor <b>double precioVenta</b> del producto
     *
     */
    public double getPrecioVenta() {
        return precioVenta;
    }

    /**
     * Método set para asignar el valor del <b>PRECIO VENTA</b> a un producto
     * 
     * @param precioVenta valor por el cual se va a vernder el producto
     */
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    /**
     * Método get para retornar el valor de la <b>CANTIDAD</b> de un producto
     *
     * @return valor <b>int cantidad</b> del producto
     *
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Método set para asignar el valor de la <b>CANTIDAD</b> a un producto
     * 
     * @param cantidad valor que define la cantidad existente del producto
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    /**
     * Método sobreescrito para imprimiir en consola todos los valores de un producto
     */
    public String toString() {
        return "----------------------------------"
                + "\nId del producto: " + getId()
                + "\nNombre del Producto: " + getNombre()
                + "\nDescripcion del producto: " + getDescripcion()
                + "\nPrecio de compra: " + String.valueOf(getPrecioCompra())
                + "\nPrecio de venta: " + String.valueOf(getPrecioVenta())
                + "\nCantidad: " + String.valueOf(getCantidad());
    }

}
