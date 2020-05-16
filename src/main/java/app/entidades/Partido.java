package app.entidades;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Partido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    private String referencia; // Para ubicar el partido

    private LocalDate fecha; // Fecha del partido
    private LocalTime hora; // Hora de inicio

    @Size(max = 100)
    private String estadio; // Nombre de estadio

    @Size(max = 20)
    private int jornada; // sera el numero de la jornada en donde se encuentra el partido


    // Relacion N:1 desde Equipo
    @ManyToOne(targetEntity = Equipo.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_local") // Agrega el campo id_local a esta tabla
    private Equipo local;

    // Relacion N:1 desde Equipo
    @ManyToOne(targetEntity = Equipo.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_visitante") // Agrega el campo id_visitante a esta tabla
    private Equipo visitante;

    // Relacion N:1 desde Calendario
    @ManyToOne(targetEntity = Calendario.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_calendario") // Agrega el campo id_calendario a esta tabla
    private Calendario calendario;


    // Relacion de 1:1 hacia Marcador ==>> id de partido en la tabla Marcador
    @OneToOne(mappedBy = "partido", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Marcador.class)
    private Marcador marcador;


    // Constructores
    public Partido() {
    }

    public Partido(Equipo local, Equipo visitante, Marcador marcador) {
        this.local = local;
        this.visitante = visitante;
        this.marcador = marcador;
    }

    public Partido(@Size(max = 100) String referencia, LocalDate fecha, LocalTime hora, @Size(max = 100) String estadio, Calendario calendario) {
        this.referencia = referencia;
        this.fecha = fecha;
        this.hora = hora;
        this.estadio = estadio;
        this.calendario = calendario;
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

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    public Equipo getLocal() {
        return local;
    }

    public void setLocal(Equipo local) {
        this.local = local;
    }

    public Equipo getVisitante() {
        return visitante;
    }

    public void setVisitante(Equipo visitante) {
        this.visitante = visitante;
    }

    public Marcador getMarcador() {
        return marcador;
    }

    public void setMarcador(Marcador marcador) {
        this.marcador = marcador;
    }
}
