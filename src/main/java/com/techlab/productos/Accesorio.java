package com.techlab.productos;


public class Accesorio extends Producto {


    public Accesorio(String nombre, double precio, int cantidadEnStock) {
        super(nombre, precio, cantidadEnStock);
    }


    @Override
    public double calcularPrecioFinal() {
        return getPrecio();
    }

    @Override
    public void aplicarDescuento(double porcentaje) {
        double nuevoPrecio = getPrecio() - (getPrecio() * porcentaje / 100);
        setPrecio(nuevoPrecio);
    }



    @Override
    public String toString() {
        return "Accesorio{nombre='" + getNombre() +
                "', precio=$" + String.format("%.2f", getPrecio()) +
                ", stock=" + getCantidadEnStock() + "}";
    }
}

