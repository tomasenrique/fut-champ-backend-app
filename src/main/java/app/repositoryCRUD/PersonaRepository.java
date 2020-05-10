package app.repositoryCRUD;

import app.entidades.Equipo;
import app.entidades.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {

    List<Persona> findByOcupacion(String ocupacion); // Obtiene la lista de jugadores o coordinadores

    List<Persona> findByOcupacionAndEquipo(String ocupacion, Equipo equipo); // Obtiene una lista de jugadores o coordinadores del mismo equipo

    Persona findByOcupacionAndDni(String ocupacion, String dni); // Obtiene un jugador o coordinador por su dni

}
