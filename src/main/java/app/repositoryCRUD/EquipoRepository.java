package app.repositoryCRUD;

import app.entidades.Equipo;
import app.entidades.League;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EquipoRepository extends CrudRepository<Equipo, Long> {

    Equipo findEquipoByName(String nombre); // Obtiene un equipo por su nombre

    List<Equipo> findEquipoByLeague(League league); // Obtendra todos los equipos de una league

}
