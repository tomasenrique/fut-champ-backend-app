package app.entidades;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Jornada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    private String referencia; // Nombre o referencia para ubicar a los equipos que hay en una jornada

    private LocalDate fecha;

    // Relacion N:1 hacia Liga
    @ManyToOne(targetEntity = Liga.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_liga") // campo o columna a crear en la tabla
    private Liga liga;

    @OneToMany(mappedBy = "jornada", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Partido.class)
    private List<Partido> partidos = new ArrayList<>();

    // Constructores
    public Jornada() {
    }

    public Jornada(String referencia, LocalDate fecha, Liga liga) {
        this.referencia = referencia;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
