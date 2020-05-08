package app.entidades;

import java.io.Serializable;
import java.time.LocalDate;

public class Jugador extends Persona implements Serializable {

    private Long id;
    private int altura; // en centimetros
    private int peso; // en kilos
    private String posicion; // lugar que ocupa en el campo
    private int dorsal; // numero de camiseta
    private Equipo equipo; // Nombre de equipo

    // Contructores
    public Jugador() {
    }

    public Jugador(String nombre, String apellido, String dni, Character genero, LocalDate fNac, String email, String telefono, String nacionalidad, Long id, int altura, int peso, String posicion, int dorsal, Equipo equipo) {
        super(nombre, apellido, dni, genero, fNac, email, telefono, nacionalidad);
        this.id = id;
        this.altura = altura;
        this.peso = peso;
        this.posicion = posicion;
        this.dorsal = dorsal;
        this.equipo = equipo;
    }

    // Setter y Getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
