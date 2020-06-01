package app.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Tabla implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int pj; // Partidos jugados
    private int pg; // Partidos ganados
    private int pe; // Partidos empatados
    private int pp; // Partidos perdidos
    private int pts; // Puntos

    // Relacion 1:1 desde Equipo
    @OneToOne
    @JoinColumn(name = "id_equipo", updatable = false, nullable = false) // Agrega un campo id_equipo a esta tabla
    private Equipo equipo;

    // Constructores
    public Tabla() {
    }

    public Tabla(int pj, int pg, int pe, int pp, int pts, Equipo equipo) {
        this.pj = pj;
        this.pg = pg;
        this.pe = pe;
        this.pp = pp;
        this.pts = pts;
        this.equipo = equipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPj() {
        return pj;
    }

    public void setPj(int pj) {
        this.pj = pj;
    }

    public int getPg() {
        return pg;
    }

    public void setPg(int pg) {
        this.pg = pg;
    }

    public int getPe() {
        return pe;
    }

    public void setPe(int pe) {
        this.pe = pe;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

}
