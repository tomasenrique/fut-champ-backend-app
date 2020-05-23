package app.repositoryCRUD;

import app.entidades.Calendario;
import app.entidades.Equipo;
import app.entidades.Partido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends CrudRepository<Partido, Long> {

    List<Partido> findPartidoByJornada(int Jornada); // Muestra una lista de partidos por jornada

    List<Partido> findPartidoByCalendario(Calendario calendario); // Muestra una lista de partidos creados por un calendario(league)

    List<Partido> findPartidoByLocal(Equipo equipoLocal); // Muestra una lista de partidos locales

    List<Partido> findPartidoByVisitante(Equipo equipoVisitante); // Muestra una lista de partidos visitantes

}
