package app.repositoryCRUD;

import app.entidades.Marcador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcadorRepository extends CrudRepository<Marcador, Long> {
}
