package app.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Jornada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;

    // Relacion N:1 hacia Liga
    @ManyToOne(targetEntity = Liga.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_liga") // campo o columna a crear en la tabla
    private Liga liga;

    // Relacion 1:N hacia Partido
    @OneToMany(mappedBy = "jornada", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Partido.class)
    private List<Partido> partidos = new ArrayList<>();

    // Constructores
    public Jornada() {
    }

    public Jornada(LocalDate fecha, Liga liga) {
        this.fecha = fecha;
        this.liga = liga;
    }

    // Setter y Getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

}
