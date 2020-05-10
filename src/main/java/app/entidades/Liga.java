package app.entidades;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Liga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 150)
    private String nombre;

    private String imagen; // Imagen url de la liga.

    // Relacion 1:N hacia Equipo
    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Equipo.class)
    private List<Equipo> equipos = new ArrayList<>();

    // Relacion 1:N hacia Jornada
    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Jornada.class)
    private List<Jornada> jornadas = new ArrayList<>(); // Para la relacion con la clase Jornada

    // Constructores
    public Liga() {
    }

    public Liga(@Size(max = 150) String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
