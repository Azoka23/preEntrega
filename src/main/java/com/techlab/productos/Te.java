package com.techlab.productos;


public class Te extends Producto {


    public Te(String nombre, double precio, int cantidadEnStock) {//Llama al constructor de Producto
        super(nombre, precio, cantidadEnStock);
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecio()*1.21; // Usa el precio de Producto y aplica un iva del 21%
    }

    //@Override
    public void aplicarDescuento(double porcentaje) {
        double nuevoPrecio = getPrecio() - (getPrecio() * porcentaje / 100);
        setPrecio(nuevoPrecio);  // Actualiza el precio con el descuento
    }
}
