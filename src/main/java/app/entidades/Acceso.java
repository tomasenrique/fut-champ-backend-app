package app.entidades;

import javax.persistence.*;

@Entity
public class Acceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clave;

    // Relacion 1:1 hacia Persona - Coordinador
    @OneToOne
    @JoinColumn(name = "id_coordinador", updatable = true, nullable = false)
    private Persona persona;

    // Constructores
    public Acceso() {
    }

    public Acceso(String clave, Persona persona) {
        this.clave = clave;
        this.persona = persona;
    }

    // Setter y Getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
