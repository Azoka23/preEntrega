package com.techlab.view;

import com.techlab.clientes.Cliente;
import com.techlab.clientes.ClienteService;
import com.techlab.pedidos.Carrito;
import com.techlab.productos.*;
import com.techlab.excepciones.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Intro {

    private static final Scanner scanner = new Scanner(System.in);

    private static ClienteService clienteService;
    private static ProductoService productoService;

    public static void iniciar(ClienteService clienteService, ProductoService productoService) {
        Intro.clienteService = clienteService;
        Intro.productoService = productoService;


        while (true) {
            //System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("========================");
            System.out.println("=== MENÚ PRINCIPAL ===");
            System.out.println("========================");
            System.out.println("1. Gestión de Clientes");
            System.out.println("-----------------------");
            System.out.println("2. Gestión de Productos");
            System.out.println("-----------------------");
            System.out.println("0. Salir");
            System.out.println("-----------------------");
            System.out.print("Seleccione una opción: ");


            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    menuGestionClientes(clienteService);
                    break;
                case "2":
                    menuGestionProductos(productoService);
                    break;
                case "0":
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }



    private static void menuGestionClientes(ClienteService clienteService) {
        System.out.print("\nIngrese DNI del cliente: ");
        String dni = scanner.nextLine();

        Cliente cliente = null;
        boolean clienteEncontrado = false;

        try {
            cliente = clienteService.buscarClientePorDni(dni);
            System.out.println("\nCliente encontrado:");
            System.out.println(cliente);
            clienteEncontrado = true;
        } catch (ClienteNoEncontradoException e) {
            System.out.println("Cliente no encontrado.");
            System.out.print("¿Desea registrarlo ahora? (s/n): ");
            String respuesta = scanner.nextLine();

            if (respuesta.equalsIgnoreCase("s")) {
                System.out.println("Registrando cliente...");
                System.out.print("Nombre y Apellido: ");
                String nombre = scanner.nextLine();
                System.out.print("Ingrese email: ");
                String email = scanner.nextLine();
                while (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    System.out.println("Email inválido. Intente nuevamente:");
                    email = scanner.nextLine();
                }
                cliente = new Cliente(dni, nombre, email);
                cliente.setEmail(email);
                clienteService.agregarCliente(cliente);
                System.out.println("Cliente registrado correctamente.");
                clienteEncontrado = true;
            } else {
                System.out.println("Registro cancelado.");
            }
        }

        if (clienteEncontrado) {
            System.out.println("\n¿Desea iniciar un pedido? (s/n)");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                Carrito carrito = new Carrito();
                menuCarrito(carrito);
            }
        }
    }




    private static void menuCarrito(Carrito carrito) {
        while (true) {
            System.out.println("\n=== GESTIÓN DE CARRITO ===");
            System.out.println("1. Agregar producto");
            System.out.println("-----------------------");
            System.out.println("2. Mostrar carrito");
            System.out.println("-----------------------");
            System.out.println("3. Calcular total");
            System.out.println("-----------------------");
            System.out.println("0. Finalizar pedido");
            System.out.println("-----------------------");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {




                case "1":
                    boolean agregarOtro = true;
                    while (agregarOtro) {
                        System.out.print("Ingrese nombre del producto: ");
                        String nombreProducto = scanner.nextLine();
                        System.out.print("¿Cuántas unidades desea agregar? ");
                        int cantidad = Integer.parseInt(scanner.nextLine());
                        try {
                            Producto producto = productoService.buscarProductoPorNombre(nombreProducto);
                            carrito.agregarProducto(producto, cantidad);
                            System.out.println("Se agregaron " + cantidad + " unidades de " + producto.getNombre());
                        } catch (ProductoNoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }

                        System.out.print("¿Desea agregar otro producto? (s/n): ");
                        String respuesta = scanner.nextLine();
                        if (!respuesta.equalsIgnoreCase("s")) {
                            agregarOtro = false;
                        }
                    }
                    break;

                case "2":
                    carrito.mostrarProductos();
                    break;

                case "3":
                    double total = carrito.calcularTotal();
                    System.out.println("Total del carrito: $" + total);

                    System.out.print("¿Desea aplicar un descuento? (s/n): ");
                    String respuesta = scanner.nextLine();

                    if (respuesta.equalsIgnoreCase("s")) {
                        System.out.print("Ingrese porcentaje de descuento (ej: 10 para 10%): ");
                        double porcentaje = Double.parseDouble(scanner.nextLine());

                        carrito.aplicarDescuento(porcentaje);

                        double totalConDescuento = carrito.calcularTotal();
                        System.out.println("Total con descuento aplicado: $" + totalConDescuento);
                    } else {
                        System.out.println("No se aplicó descuento.");
                    }
                    break;

                case "0":
                    System.out.println("Pedido finalizado.");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }


    private static void menuGestionProductos(ProductoService productoService) {
        while (true) {
            System.out.println("\n=== GESTIÓN DE PRODUCTOS ===");
            System.out.println("1. Listar total de productos");
            System.out.println("-----------------------");
            System.out.println("2. Listar productos con bajo stock");
            System.out.println("-----------------------");
            System.out.println("3. Agregar producto");
            System.out.println("-----------------------");
            System.out.println("4. Eliminar producto");
            System.out.println("-----------------------");
            System.out.println("5. Editar producto");
            System.out.println("-----------------------");
            System.out.println("0. Volver");
            System.out.println("-----------------------");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    List<Producto> productos = productoService.listarProductos();
                    System.out.println("\nNombre | Precio | Stock | Tipo");
                    for (Producto p : productos) {
                        String tipo = p.getClass().getSimpleName();
                        System.out.printf("%s | %.2f | %d | %s%n", p.getNombre(), p.getPrecio(), p.getCantidadEnStock(), tipo);
                    }
                    break;

                case "2":
                    productos = productoService.listarProductosConStockBajo(10);
                    System.out.println("\nNombre | Precio | Stock | Tipo");
                    for (Producto p : productos) {
                        String tipo = p.getClass().getSimpleName();
                        System.out.printf("%s | %.2f | %d | %s%n", p.getNombre(), p.getPrecio(), p.getCantidadEnStock(), tipo);
                    }
                    break;
                case "3":
                    System.out.print("Tipo (Cafe/Te/Accesorio): ");
                    String tipo = scanner.nextLine().toLowerCase();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = Double.parseDouble(scanner.nextLine());
                    System.out.print("Stock: ");
                    int stock = Integer.parseInt(scanner.nextLine());

                    Producto nuevo = null;
                    switch (tipo) {
                        case "cafe":
                            nuevo = new Cafe(nombre, precio, stock);
                            break;
                        case "te":
                            nuevo = new Te(nombre, precio, stock);
                            break;
                        case "accesorio":
                            nuevo = new Accesorio(nombre, precio, stock);
                            break;
                        default:
                            System.out.println("Tipo no válido.");
                            continue;
                    }

                    productoService.agregarProducto(nuevo);
                    System.out.println("Producto agregado.");
                    break;

                case "4":
                    System.out.print("Nombre del producto a eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    try {
                        productoService.eliminarProducto(nombreEliminar);
                        System.out.println("Producto eliminado.");
                    } catch (ProductoNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "5":
                    System.out.print("Ingrese el nombre del producto a editar: ");
                    String nombreEditar = scanner.nextLine();

                    try {
                        Producto productoExistente = productoService.buscarProductoPorNombre(nombreEditar);

                        System.out.println("Producto encontrado:");
                        productoExistente.mostrarInformacion();

                        System.out.println("¿Qué desea editar?");
                        System.out.println("1. Nombre");
                        System.out.println("2. Precio base");
                        System.out.println("3. Stock");
                        System.out.println("4. Volver");
                        int opcionEditar = Integer.parseInt(scanner.nextLine());

                        switch (opcionEditar) {
                            case 1:
                                System.out.print("Nuevo nombre: ");
                                String nuevoNombre = scanner.nextLine();
                                productoExistente.setNombre(nuevoNombre);
                                System.out.println("Producto actualizado.");
                                break;
                            case 2:
                                System.out.print("Nuevo precio base: ");
                                double nuevoPrecio = Double.parseDouble(scanner.nextLine());
                                productoExistente.setPrecio(nuevoPrecio); // A revisar abajo
                                System.out.println("Producto actualizado.");
                                break;
                            case 3:
                                System.out.print("Nuevo stock: ");
                                int nuevoStock = Integer.parseInt(scanner.nextLine());
                                productoExistente.setCantidadEnStock(nuevoStock);
                                System.out.println("Producto actualizado.");
                                break;
                            case 4:
                                System.out.println("Volviendo...");
                                break;
                            default:
                                System.out.println("Opción inválida.");
                        }


                    } catch (ProductoNoEncontradoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "0":
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}