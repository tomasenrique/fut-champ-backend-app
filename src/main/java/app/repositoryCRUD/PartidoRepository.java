package app.repositoryCRUD;

import app.entidades.Partido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PartidoRepository extends CrudRepository<Partido, Long> {







}
