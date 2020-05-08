package app.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Partido implements Serializable {

    private Long id;
    private String equipoA; // Nombre del equipo
    private String equipoB;
    private LocalDate fecha; // Fecha del partido
    private LocalTime inicio; // Hora de inicio
    private LocalTime fin; // Hora final
    private String estadio; // Nombre de estadio
    private String ciudad;
    private String pais;


    // Constructores
    public Partido() {
    }


    // Setter y Getter





}
