package app.entidades;

import java.time.LocalDate;

abstract class Persona {
    private String nombre;
    private String apellido;
    private String dni;
    private Character genero; // Sexo del jugador 'M' o 'H'
    private LocalDate fNac;  // Fecha de nacimiento
    private String email;
    private String telefono;
    private String nacionalidad;

    // Contructores
    public Persona() {
    }

    public Persona(String nombre, String apellido, String dni, Character genero, LocalDate fNac, String email, String telefono, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.genero = genero;
        this.fNac = fNac;
        this.email = email;
        this.telefono = telefono;
        this.nacionalidad = nacionalidad;
    }

    // Setter y Getter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public LocalDate getfNac() {
        return fNac;
    }

    public void setfNac(LocalDate fNac) {
        this.fNac = fNac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
