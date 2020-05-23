package app.repositoryCRUD;

import app.entidades.Coordinador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinadorRepository extends CrudRepository<Coordinador, Long> {

    // Devuelve un coordinador para verificar acceso, actualizar clave o eliminar autorizacion de acceso
    Coordinador findByUsuarioAndClave(String usuario, String clave);

}
