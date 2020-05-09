package app.entidades;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(value="coordinador") // Dato identificador que ira en el campo 'ocupacion' el la tabla
public class Coordinador extends Persona {

    // Relacion N:1 hacia Equipo
    @OneToOne(targetEntity = Equipo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_equipo") // campo o columna a crear en la tabla
    private Equipo equipo; // Nombre de equipo, sera el id de identificacion

    // Constructores
    public Coordinador() {
    }

    public Coordinador(@Size(max = 100) String nombre, @Size(max = 100) String apellidos, @Size(max = 20) String dni, Character genero, LocalDate fNac, @Size(max = 40) String email, @Size(max = 20) String telefono, @Size(max = 100) String cargo, Equipo equipo) {
        super(nombre, apellidos, dni, genero, fNac, email, telefono, cargo);
        this.equipo = equipo;
    }

    // Setter y Getter
    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

}
