package app.entidades;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Persona {

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

    @Size(max = 50)
    private String ocupacion; // Indicara si la clase persona es un jugador o coordinador

    // Campos para Jugador

    @Size(max = 100)
    private String posicion; // Lugar que ocupa en el campo el jugador

    private int dorsal; // Numero de camiseta del jugador

    // Campo para Coordinador
    @Size(max = 100)
    private String cargo;

    // Relacion N:1 hacia Equipo
    @ManyToOne(targetEntity = Equipo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona") // campo o columna a crear en la tabla
    private Equipo equipo; // Nombre de equipo, sera el id de identificacion

    // Contructores
    public Persona() {
    }

    // Constructor de Jugador
    public Persona(String nombre, String apellidos, String dni, Character genero, LocalDate fNac, String email, String telefono, String ocupacion, String posicion, int dorsal, Equipo equipo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.genero = genero;
        this.fNac = fNac;
        this.email = email;
        this.telefono = telefono;
        this.ocupacion = ocupacion;
        this.posicion = posicion;
        this.dorsal = dorsal;
        this.equipo = equipo;
    }

    // Constructor de Coordinador
    public Persona(String nombre, String apellidos, String dni, Character genero, LocalDate fNac, String email, String telefono, String ocupacion, String cargo, Equipo equipo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.genero = genero;
        this.fNac = fNac;
        this.email = email;
        this.telefono = telefono;
        this.ocupacion = ocupacion;
        this.cargo = cargo;
        this.equipo = equipo;
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

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
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

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

}
