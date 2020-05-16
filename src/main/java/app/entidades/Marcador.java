package app.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Marcador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int Glocal; //
    private int Gvisitante; //

    // Relacion 1:1 desde Partido
    @OneToOne(targetEntity = Partido.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_partido") // Agrega el campo id_partido  a esta tabla
    private Partido partido;

    // Constructores
    public Marcador() {
    }

    public Marcador(int glocal, int gvisitante, Partido partido) {
        Glocal = glocal;
        Gvisitante = gvisitante;
        this.partido = partido;
    }

    // Setter y Getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGlocal() {
        return Glocal;
    }

    public void setGlocal(int glocal) {
        Glocal = glocal;
    }

    public int getGvisitante() {
        return Gvisitante;
    }

    public void setGvisitante(int gvisitante) {
        Gvisitante = gvisitante;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

}
