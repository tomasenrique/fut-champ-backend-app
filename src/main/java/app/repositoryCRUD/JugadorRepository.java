package app.repositoryCRUD;

import app.entidades.Equipo;
import app.entidades.Jugador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Long> {

    Jugador findJugadorByNombreAndApellidos(String nombres, String apellidos); // Devuleve un jugador buscado por su nombre y apellido

    Jugador findJugadorByDni(String dni); // Devuelve un jugador buscado por su dni

    Jugador findJugadorByEmail(String email); // Devuelve un jugador buscado por su email

    List<Jugador> findJugadorByEquipo(Equipo equipo); // Devuelve una lista de jugadores de un mismo equipo

}
