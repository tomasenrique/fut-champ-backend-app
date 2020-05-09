package app.repositoryCRUD;

import app.entidades.Equipo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends CrudRepository<Equipo, Long> {

    List<Equipo> findEquipoByCategoria(String categoria);  // Obtiene una lista de equipos por categoria

    Equipo findEquipoByNombre(String nombre); // Obtiene un equipo por su nombre


}
