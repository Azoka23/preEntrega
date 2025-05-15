
package com.techlab.clientes;

import java.util.List;
import com.techlab.excepciones.ClienteNoEncontradoException;

public class ClienteService {

    private List<Cliente> clientes;

    public ClienteService(List<Cliente> clientes) {
        this.clientes = clientes;
    }


    public Cliente buscarClientePorDni(String dni) throws ClienteNoEncontradoException {
        for (Cliente c : clientes) {
            if (c.getDni().equals(dni)) {
                return c;
            }
        }
        throw new ClienteNoEncontradoException("Cliente con DNI " + dni + " no encontrado.");
    }


    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }


    public void actualizarCliente(Cliente cliente) throws ClienteNoEncontradoException {
        Cliente existingCliente = buscarClientePorDni(cliente.getDni());
        existingCliente.setNombre(cliente.getNombre());
        existingCliente.setEmail(cliente.getEmail());
        // Agregar más atributos si es necesario
    }


    public void listarClientes() {
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }
}
