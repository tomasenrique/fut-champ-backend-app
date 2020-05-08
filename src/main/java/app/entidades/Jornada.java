package app.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Jornada implements Serializable {

    private Long id;
    private LocalDate fecha;

    private ArrayList<Partido> partidos; // Para la relacion con la clase Partido


    // Constructores
    public Jornada() {
    }



    // Setter y Getter


}
