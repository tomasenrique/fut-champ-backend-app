package app.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Partido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha; // Fecha del partido
    private LocalTime hora; // Hora de inicio

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


    // Relacion de 1:1 hacia Marcador ==>> id de partido en la tabla Marcador, inicializa la tabla a cero
    @OneToOne(mappedBy = "partido", cascade = CascadeType.DETACH, fetch = FetchType.LAZY, targetEntity = Marcador.class)
    private Marcador marcador;


    // Constructores
    public Partido() {
    }

    public Partido(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
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

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

}
