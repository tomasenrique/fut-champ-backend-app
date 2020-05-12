package app.repositoryCRUD;

import app.entidades.Acceso;
import app.entidades.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccesoRepository extends CrudRepository<Acceso, Long> {

    Acceso findAccesoByPersona(Persona coordinador);  // Encuantra la clave de acceso de una coordinador

}
