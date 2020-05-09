package app.entidades;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@DiscriminatorColumn(name = "ocupacion") // Indica un campo en la tabla que identifica el tipo registro hecho
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Indica que las clases que heredan estaran en usa sola tabla
abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    private String nombre;

    @Size(max = 100)
    private String apellidos;

    @Size(max = 20)
    @Column(unique = true)
    private String dni;

    private Character genero; // Sexo del jugador 'M' o 'H'
    private LocalDate fNac;  // Fecha de nacimiento

    @Size(max = 40)
    @Column(unique = true)
    private String email;

    @Size(max = 20)
    private String telefono;

    // Campos para Jugador
    @Size(max = 100)
    private String posicion; // Lugar que ocupa en el campo el jugador

    private int dorsal; // Numero de camiseta del jugador

    // Campo para Coordinador
    @Size(max = 100)
    private String cargo;


    // Contructores
    public Persona() {
    }

    // Constructor de Jugador
    public Persona(@Size(max = 100) String nombre, @Size(max = 100) String apellidos, @Size(max = 20) String dni, Character genero, LocalDate fNac, @Size(max = 40) String email, @Size(max = 20) String telefono, @Size(max = 100) String posicion, int dorsal) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.genero = genero;
        this.fNac = fNac;
        this.email = email;
        this.telefono = telefono;
        this.posicion = posicion;
        this.dorsal = dorsal;
    }

    // Constructor de Coordinador
    public Persona(@Size(max = 100) String nombre, @Size(max = 100) String apellidos, @Size(max = 20) String dni, Character genero, LocalDate fNac, @Size(max = 40) String email, @Size(max = 20) String telefono, @Size(max = 100) String cargo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.genero = genero;
        this.fNac = fNac;
        this.email = email;
        this.telefono = telefono;
        this.cargo = cargo;
    }


    // Setter y Getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
