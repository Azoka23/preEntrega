package com.techlab.productos;

/**
 * Clase abstracta base para representar un producto genérico.
 * Contiene nombre, precio, cantidad en stock y un contador de productos creados.
 */
public abstract class Producto {
    private String nombre;
    private double precio;
    private int cantidadEnStock;
    private static int contadorProductos = 0;


    public Producto(String nombre, double precio, int cantidadEnStock) {
        this.nombre = nombre;
        this.precio = precio;
        setCantidadEnStock(cantidadEnStock);
        contadorProductos++;


    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public double getPrecio() {
        return precio;
    }




    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        }
    }


    public int getCantidadEnStock() {
        return cantidadEnStock;
    }


    public void setCantidadEnStock(int cantidadEnStock) {
        if (cantidadEnStock >= 0) {
            this.cantidadEnStock = cantidadEnStock;
        } else {
            System.out.println("No se puede asignar stock negativo.");
        }
    }

    /**
     * Obtiene el total de productos creados.

     */
    public static int getContadorProductos() {
        return contadorProductos;
    }


    public abstract double calcularPrecioFinal();

    public abstract void aplicarDescuento(double porcentaje);


    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio base: $" + String.format("%.2f", precio));
        System.out.println("Stock: " + cantidadEnStock);
    }


    @Override
    public String toString() {
        return "Producto{nombre='" + nombre +
                "', precio base=$" + String.format("%.2f", precio) +
                ", stock=" + cantidadEnStock +
                ", precio final=$" + String.format("%.2f", calcularPrecioFinal()) + "}";
    }
}
