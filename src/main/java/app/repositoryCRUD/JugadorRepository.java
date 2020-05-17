package app.repositoryCRUD;

import app.entidades.Equipo;
import app.entidades.Jugador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Long> {

    Jugador findJugadorByDni(String dni); // Devuelve un jugador buscado por su dni

    Jugador findJugadorByEmail(String email);

    List<Jugador> findJugadorByEquipo(Equipo equipo); // devuelve una lista de juagadores de un mismo equipo

}
