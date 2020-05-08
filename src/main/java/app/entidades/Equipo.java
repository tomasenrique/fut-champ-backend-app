package app.entidades;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Equipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    private String nombre;  // nombre de equipo

    @Size(max = 100)
    private String categoria; // Sera si es A o B, primera o segunda division

    // Relacion 1:N hacia Persona - Jugador
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Persona.class)
    private List<Persona> jugadores = new ArrayList<>();

    // Relacion N:1 hacia Liga
    @ManyToOne(targetEntity = Liga.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_liga") // campo o columna a crear en la tabla
    private Liga liga;

    // Contructores
    public Equipo() {
    }

    public Equipo(String nombre, String categoria, Liga liga) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.liga = liga;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }


}
