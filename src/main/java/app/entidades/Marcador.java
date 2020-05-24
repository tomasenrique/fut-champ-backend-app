package app.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Marcador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int gLocal; //
    private int gVisitante; //

    // Relacion 1:1 desde Partido
    @OneToOne(targetEntity = Partido.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_partido") // Agrega el campo id_partido  a esta tabla
    private Partido partido;

    // Constructores
    public Marcador() {
    }

    public Marcador(int gLocal, int gVisitante, Partido partido) {
        this.gLocal = gLocal;
        this.gVisitante = gVisitante;
        this.partido = partido;
    }

    // Setter y Getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getgLocal() {
        return gLocal;
    }

    public void setgLocal(int gLocal) {
        this.gLocal = gLocal;
    }

    public int getgVisitante() {
        return gVisitante;
    }

    public void setgVisitante(int gVisitante) {
        this.gVisitante = gVisitante;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

}
