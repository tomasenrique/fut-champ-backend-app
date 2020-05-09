package app.repositoryCRUD;

import app.entidades.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {

    List<Persona> findPersonaByOcupacion(String ocupacion); // Obtiene la lista de jugadores o coordinadores


    Persona findPersonaByDni(String dni);

    Persona findPersonaByEmail(String email);

    List<Persona> findPersonaByGenero(Character genero);



//    Persona findPersonaByPosicion(String posicion);

//    Persona findPersonaByDorsal(int dorsal);


}
