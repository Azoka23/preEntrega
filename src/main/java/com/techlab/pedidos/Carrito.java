package com.techlab.pedidos;

import com.techlab.productos.Producto;
import java.util.ArrayList;
import java.util.Scanner;

public class Carrito {
    private ArrayList<ItemCarrito> items;
    private Scanner scanner = new Scanner(System.in);

    public Carrito() {
        items = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (producto.getCantidadEnStock() >= cantidad) {
            producto.setCantidadEnStock(producto.getCantidadEnStock() - cantidad);
        } else {
            int disponibles = producto.getCantidadEnStock();
            System.out.println("No hay suficiente stock. Solo quedan " + disponibles + " unidades. ¿Desea agregarlas todas? (s/n)");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                cantidad = disponibles;
                producto.setCantidadEnStock(0);
            } else {
                System.out.println("No se agregaron unidades al carrito.");
                return;
            }
        }

        for (ItemCarrito item : items) {
            if (item.getProducto().equals(producto)) {
                item.agregarCantidad(cantidad);
                System.out.println("Se agregaron " + cantidad + " unidades más de " + producto.getNombre());
                return;
            }
        }

        items.add(new ItemCarrito(producto, cantidad));
        System.out.println("Se agregaron " + cantidad + " unidades de " + producto.getNombre());
    }

    public void mostrarProductos() {
        if (items.isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }

        System.out.println("Productos en el carrito:");
        for (ItemCarrito item : items) {
            System.out.println(item);
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void aplicarDescuento(double porcentaje) {
        for (ItemCarrito item : items) {
            item.getProducto().aplicarDescuento(porcentaje);
        }
    }


    public void vaciarCarrito() {
        items.clear();
    }

    public int cantidadProductos() {
        int total = 0;
        for (ItemCarrito item : items) {
            total += item.getCantidad();
        }
        return total;
    }

    public boolean eliminarProducto(String nombre) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getNombre().equalsIgnoreCase(nombre)) {
                items.remove(item);
                return true;
            }
        }
        return false;
    }

    public Producto buscarProducto(String nombre) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getNombre().equalsIgnoreCase(nombre)) {
                return item.getProducto();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Carrito con productos:\n");
        for (ItemCarrito item : items) {
            sb.append("- ").append(item.getProducto().getNombre())
                    .append(" x").append(item.getCantidad())
                    .append(" ($").append(String.format("%.2f", item.getSubtotal()))
                    .append(")\n");
        }
        sb.append("Total: $").append(String.format("%.2f", calcularTotal()));
        return sb.toString();
    }
}
