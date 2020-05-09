package app.repositoryCRUD;

import app.entidades.Liga;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigaRepository extends CrudRepository<Liga, Long> {
}
