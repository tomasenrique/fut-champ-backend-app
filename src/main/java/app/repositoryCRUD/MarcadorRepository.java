package app.repositoryCRUD;

import app.entidades.Marcador;
import app.entidades.Partido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcadorRepository extends CrudRepository<Marcador, Long> {

    Marcador findMarcadorByPartido(Partido idPartido); // Devuelve un marcador de un partido.



}
