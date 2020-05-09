package app.repositoryCRUD;


import app.entidades.Jornada;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JornadaRepository  extends CrudRepository<Jornada, Long> {
}
