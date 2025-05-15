package com.techlab.productos;

import com.techlab.util.TextoUtil;
import com.techlab.excepciones.ProductoNoEncontradoException;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    // Lista que almacena los productos disponibles
    private List<Producto> productos;


    public ProductoService(List<Producto> productos) {
        this.productos = productos;
    }


    public Producto buscarProductoPorNombre(String nombre) throws ProductoNoEncontradoException {
        // Recorrer la lista de productos para buscar por nombre
        for (Producto p : productos) {
            // Comparación de nombres ignorando mayúsculas y minúsculas usando el método de la clase TextoUtil
            if (TextoUtil.iguales(p.getNombre(), nombre)) {
                return p;
            }
        }
        // Si no se encuentra el producto, se lanza una excepción con un mensaje adecuado
        throw new ProductoNoEncontradoException("Producto no encontrado: " + nombre);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProductoService {")
                .append("\n  productos=\n  [");

        // Se recorre la lista de productos para generar una representación legible
        for (Producto p : productos) {
            sb.append("\n    ").append(p.toString()).append(","); // Llamando al toString del producto
        }

        // Si la lista no está vacía, se elimina la coma final
        if (!productos.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append("\n  ]\n}");
        return sb.toString();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
    public void eliminarProducto(String nombre) throws ProductoNoEncontradoException {
        Producto producto = buscarProductoPorNombre(nombre);
        productos.remove(producto);
    }
    public void editarProducto(String nombre, String nuevoNombre, Double nuevoPrecio, Integer nuevoStock)
            throws ProductoNoEncontradoException {
        Producto producto = buscarProductoPorNombre(nombre);
        if (nuevoNombre != null && !nuevoNombre.isBlank()) {
            producto.setNombre(nuevoNombre);
        }
        if (nuevoPrecio != null && nuevoPrecio >= 0) {
            producto.setPrecio(nuevoPrecio);
        }
        if (nuevoStock != null && nuevoStock >= 0) {
            producto.setCantidadEnStock(nuevoStock);
        }
    }


    public List<Producto> listarProductos() {
        return productos;
    }
    public List<Te> listarProductosTe() {
        List<Te> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p instanceof Te) {
                resultado.add((Te) p);
            }
        }
        return resultado;
    }
    public List<Producto> listarProductosConStockBajo(int umbral) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCantidadEnStock() < umbral) {
                resultado.add(p);
            }
        }
        return resultado;
    }

}
