
package com.techlab.ui;

import com.techlab.view.Intro;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techlab.clientes.Cliente;
import com.techlab.clientes.ClienteService;
import com.techlab.productos.Producto;
import com.techlab.productos.ProductoService;
import com.techlab.productos.Te;
import com.techlab.productos.Cafe;
import com.techlab.productos.Accesorio;
import com.techlab.excepciones.ClienteNoEncontradoException;
import com.techlab.excepciones.ProductoNoEncontradoException;


public class Main {
    public static void main(String[] args) {
        // --- Creo clientes de prueba ---
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("12345678", "Juan Perez",  "juan@gmail.com"));
        clientes.add(new Cliente("87654321", "María Garcia", "maria@hotmail.com"));
        clientes.add(new Cliente("11223344", "Carlos Lopez", "carlos@yahoo.com"));

// --- Creo productos de prueba ---
        List<Producto> productos = new ArrayList<>();
        productos.add(new Cafe("Espresso", 1200.0, 50));
        productos.add(new Cafe("Latte", 1500.0, 30));
        productos.add(new Te("Te Verde", 800.0, 3));
        productos.add(new Te("Te Negro", 900.0, 5));
        productos.add(new Accesorio("Taza de ceramica", 500.0, 25));
        productos.add(new Accesorio("Filtro reutilizable", 300.0, 91));

// --- Inicializo servicios   con estas listas ---
        ClienteService clienteService = new ClienteService(clientes);
        ProductoService productoService = new ProductoService(productos);

        Intro.iniciar(clienteService, productoService);
    }
}

