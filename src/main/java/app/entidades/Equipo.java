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
    @Column(unique = true)
    private String name;  // Nombre de equipo, dato de tipo unico

    private String logo; // url de la imagen de logo del equipo

    // Relacion N:1 desde Liga
    @ManyToOne(targetEntity = League.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_league") // Agrega el campo id_league a esta tabla
    private League league;

    // Relacion 1:N hacia Persona Jugador
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Jugador.class)
    private List<Jugador> jugadores = new ArrayList<>();

    // relacion 1:N hacia Partido local
    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Partido.class)
    private List<Partido> partidosLocal = new ArrayList<>();

    // relacion 1:N hacia Partido visitante
    @OneToMany(mappedBy = "visitante", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Partido.class)
    private List<Partido> partidosVisitante = new ArrayList<>();

    // Relacion 1:1 hacia Tabla ==>> id de equipo en la clase tabla
    @OneToOne(mappedBy = "equipo", cascade = CascadeType.ALL)
    private Tabla tabla; // Sera para la tabla de puntuacion de los equipos

    // Contructores
    public Equipo() {
    }

    public Equipo(@Size(max = 100) String name, String logo, League league) {
        this.name = name;
        this.logo = logo;
        this.league = league;
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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

}
