package app.repositoryCRUD;

import app.entidades.Jugador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Long> {



}
