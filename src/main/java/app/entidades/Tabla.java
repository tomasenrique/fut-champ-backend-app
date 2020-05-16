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
    private int gf; // Goles a favor
    private int ge; //
    private int pts; // Puntos

    // Relacion 1:1 desde Equipo
    @OneToOne
    @JoinColumn(name = "id_equipo", updatable = false, nullable = false) // Agrega un campo id_equipo a esta tabla
    private Equipo equipo;

    // Constructores
    public Tabla() {
    }

    public Tabla(int pj, int pg, int pe, int pp, int gf, int ge, int pts, Equipo equipo) {
        this.pj = pj;
        this.pg = pg;
        this.pe = pe;
        this.pp = pp;
        this.gf = gf;
        this.ge = ge;
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

    public int getGf() {
        return gf;
    }

    public void setGf(int gf) {
        this.gf = gf;
    }

    public int getGe() {
        return ge;
    }

    public void setGe(int ge) {
        this.ge = ge;
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
