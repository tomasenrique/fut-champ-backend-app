package app.repositoryCRUD;

import app.entidades.Partido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PartidoRepository extends CrudRepository<Partido, Long> {

    Partido findPartidoByReferencia(String referencia); // encuentra un partido por su referencia => VER PORQUE A LO MEJOR DEVUELVE 2 DATOS

    List<Partido> findPartidoByReferencia(LocalDate fecha); // Devuelve una lista de los partidos que se hacen en la fecha

    List<Partido> findPartidoByEstadio(String estadio);




}
