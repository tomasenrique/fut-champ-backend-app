package app.entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class Equipo implements Serializable {

    private Long id;
    private String nombre;  // nombre de equipo
    private String ciudad;
    private String pais;
    private String categoria; // Sera si es A o B, primera o segunda division

    private ArrayList<Jugador> jugadores; //  Para la relacion entre clase Jugador

    // Contructores
    public Equipo() {
    }

    public Equipo(String nombre, String ciudad, String pais, String categoria) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.categoria = categoria;
    }

    // Setter y Getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
