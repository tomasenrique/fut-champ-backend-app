package app.entidades;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Partido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String referencia; // Para ubicar el partido

    @Size(max = 100)
    private String equipoA; // Nombre del equipo

    @Size(max = 100)
    private String equipoB;

    private LocalDate fecha; // Fecha del partido
    private LocalTime inicio; // Hora de inicio
    private LocalTime fin; // Hora final

    @Size(max = 100)
    private String estadio; // Nombre de estadio

    // Relacion N:1 hacia Jornada
    @ManyToOne(targetEntity = Jornada.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_jornada") // campo o columna a crear en la tabla
    private Jornada jornada;


    // Relacion 1:N hacia Equipo
    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Equipo.class)
    private List<Equipo> equipos = new ArrayList<>();

    // Constructores
    public Partido() {
    }

    public Partido(String referencia, @Size(max = 100) String equipoA, @Size(max = 100) String equipoB, LocalDate fecha, LocalTime inicio, LocalTime fin, @Size(max = 100) String estadio, Jornada jornada) {
        this.referencia = referencia;
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.fecha = fecha;
        this.inicio = inicio;
        this.fin = fin;
        this.estadio = estadio;
        this.jornada = jornada;
    }

    // Setter y Getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipoA() {
        return equipoA;
    }

    public void setEquipoA(String equipoA) {
        this.equipoA = equipoA;
    }

    public String getEquipoB() {
        return equipoB;
    }

    public void setEquipoB(String equipoB) {
        this.equipoB = equipoB;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public LocalTime getFin() {
        return fin;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
