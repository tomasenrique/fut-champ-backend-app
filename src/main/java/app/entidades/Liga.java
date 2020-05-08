package app.entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class Liga implements Serializable {

    private Long id;
    private String nombre;

    private ArrayList<Jornada> jornadas; // Para la relacion con la clase Jornada
    private ArrayList<Equipo> equipos; // Para la relacion con la clase Equipo


    // Constructores
    public Liga() {
    }


    // Setter y Getter
}
