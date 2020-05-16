package app.entidades;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Jugador implements Serializable {

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

    private LocalDate fNac;  // Fecha de nacimiento

    @Size(max = 40)
    @Column(unique = true)
    private String email;

    @Size(max = 20)
    private String telefono;

    private String imagen; // url de ubicacion de la imagen

    @Size(max = 100)
    private String posicion; // Lugar que ocupa en el campo el jugador

    @Size(max = 10)
    private String dorsal; // Numero de camiseta del jugador

    // Relacion N:1 desde Equipo
    // Al poner CascadeType.DETACH indicamos que solo se borre la persona y no asociado a ella.
    @ManyToOne(targetEntity = Equipo.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_equipo") // campo o columna a crear en la tabla
    private Equipo equipo; // Nombre de equipo, sera el id de identificacion


    // Contructores
    public Jugador() {
    }

    // Constructor resumido
    public Jugador(@Size(max = 100) String nombre, @Size(max = 100) String apellidos, @Size(max = 20) String dni, @Size(max = 40) String email, @Size(max = 10) String dorsal, Equipo equipo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.dorsal = dorsal;
        this.equipo = equipo;
    }

    // Constructor completo
    public Jugador(@Size(max = 100) String nombre, @Size(max = 100) String apellidos, @Size(max = 20) String dni, LocalDate fNac, @Size(max = 40) String email, @Size(max = 20) String telefono, String imagen, @Size(max = 100) String posicion, @Size(max = 10) String dorsal, Equipo equipo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fNac = fNac;
        this.email = email;
        this.telefono = telefono;
        this.imagen = imagen;
        this.posicion = posicion;
        this.dorsal = dorsal;
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

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


}
