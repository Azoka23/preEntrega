package com.techlab.clientes;


public class Cliente {
    private String dni;
    private String nombre;
    private String email;


    public Cliente(String dni, String nombre, String email) {
        this.dni = dni;
        this.nombre = nombre;
        setEmail(email);
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Asigna el email del cliente si contiene el carácter "@".
     * En caso contrario, muestra un mensaje de error por consola.
     * @param email Email a validar y asignar.
     */
    public void setEmail(String email) {
        if (email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Email inválido.");
        }
    }


    public void mostrarInformacion() {
        System.out.println("Cliente: " + nombre + ", Email: " + email);
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "DNI='" + dni + '\'' +
                ", Nombre='" + nombre + '\'' +
                ", Email='" + email + '\'' +
                '}';
    }

}
