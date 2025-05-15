package com.techlab.productos;


public class Cafe extends Producto {


    public Cafe(String nombre, double precio, int cantidadEnStock) {
        super(nombre, precio, cantidadEnStock);
    }


    @Override
    public double calcularPrecioFinal() {
        return getPrecio() * 1.21;  // Aplica un IVA del 21%
    }

    @Override
    public void aplicarDescuento(double porcentaje) {
        double nuevoPrecio = getPrecio() - (getPrecio() * porcentaje / 100);
        setPrecio(nuevoPrecio);  // Aplica el descuento modificando el precio base
    }



    @Override
    public String toString() {
        return "Cafe{nombre='" + getNombre() +
                "', precio base=$" + String.format("%.2f", getPrecio()) +
                ", stock=" + getCantidadEnStock() +
                ", precio final con IVA=$" + String.format("%.2f", calcularPrecioFinal()) + "}";
    }
}
