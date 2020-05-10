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

    private String logo; // url de la imagen de logo del equipo

    // Relacion 1:N hacia Persona Jugador o Coordinador
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Persona.class)
    private List<Persona> integrantes_equipo = new ArrayList<>();


    // Relacion N:1 hacia Liga
    // Al poner CascadeType.DETACH indicamos que solo se borre el equipo y no asociado a el.
    @ManyToOne(targetEntity = Liga.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_liga") // campo o columna a crear en la tabla
    private Liga liga;

    // Contructores
    public Equipo() {
    }

    public Equipo(@Size(max = 100) String nombre, @Size(max = 100) String categoria, String logo, Liga liga) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.logo = logo;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
