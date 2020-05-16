package app.entidades;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class League implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 150)
    private String name; // nombre de la liga

    private String logo; // Imagen url de la liga.

    // Relacion 1:1 desde Calendario
    @OneToOne // Agrega el campo id_calendario a esta tabla
    @JoinColumn(name = "id_calendario", updatable = false, nullable = true)
    private Calendario calendario;

    // Relacion 1:N hacia Equipo
    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Equipo.class)
    private List<Equipo> Equipos;

    // Constructores
    public League() {
    }

    public League(@Size(max = 150) String name, String logo) {
        this.name = name;
        this.logo = logo;
    }

    public League(@Size(max = 150) String name, String logo, Calendario calendario) {
        this.name = name;
        this.logo = logo;
        this.calendario = calendario;
    }

    // Setter y Getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    // PARA VER SI DA ERROR
//    public List<Equipo> getEquipos() { // para obtener la lista de equipoa para generar el calendario de partidos
//        return Equipos;
//    }


}
