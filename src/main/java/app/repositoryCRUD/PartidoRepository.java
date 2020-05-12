package app.repositoryCRUD;

import app.entidades.Partido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends CrudRepository<Partido, Long> {

    Partido findPartidoByReferencia(String referencia); // encuentra un partido por su referencia

}
