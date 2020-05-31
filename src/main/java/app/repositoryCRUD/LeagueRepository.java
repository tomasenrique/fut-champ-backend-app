package app.repositoryCRUD;

import app.entidades.League;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends CrudRepository<League, Long> {

    League findLigaByName(String nombre);  // Busca una liga por su nombre
    Boolean existsLeagueByName(String nombre);
}
