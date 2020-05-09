package app.repositoryCRUD;

import app.entidades.Liga;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigaRepository extends CrudRepository<Liga, Long> {

    Liga findLigaByNombre(String nombre);  // Busca una liga por su nombre
}
