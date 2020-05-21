package app.repositoryCRUD;

import app.entidades.Calendario;
import app.entidades.League;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CalendarioRepository extends CrudRepository<Calendario, Long> {

    // Muestra una lista de calendarios con los nombres de las leagues que generaron los partidos y su fecha y hora inical
    List<Calendario> findCalendarioByLeague(String nombreLeague);

    // Muestra solo un calendario ubicado por su nombre de liga y la fecha inicial.
    Calendario findCalendarioByLeagueAndFecha(String nombreLeague, LocalDate fechaInicio);


}
