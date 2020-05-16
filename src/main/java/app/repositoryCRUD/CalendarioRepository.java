package app.repositoryCRUD;

import app.entidades.Calendario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarioRepository extends CrudRepository<Calendario, Long> {
}
