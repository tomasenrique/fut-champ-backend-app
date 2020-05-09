package app.controladores;

import app.entidades.Equipo;
import app.entidades.Persona;
import app.repositoryCRUD.EquipoRepository;
import app.repositoryCRUD.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/futchamp/jugador")
public class JugadorController {

    @Autowired
    private PersonaRepository personaRepositoryJugador;

    @Autowired
    private EquipoRepository obtenerDatosEquipoRepository;

    @PostMapping("/agregar")
    public ResponseEntity<Persona> agregarJugador(@RequestBody Persona jugador) {
        // Busca el equipo por su nombren
        Equipo equipoBuscado =  obtenerDatosEquipoRepository.findEquipoByNombre(jugador.getEquipo().getNombre());
        jugador.setEquipo(equipoBuscado); // asigna el id de equipoBuscado
        Persona addJugador = personaRepositoryJugador.save(jugador);
        return ResponseEntity.status(HttpStatus.CREATED).body(addJugador);
    }

    @GetMapping("/mostrar")
    public Iterable<Persona> mostrarJugadores() {
        final String OCUPACION = "jugador";
        try {
            return personaRepositoryJugador.findPersonaByOcupacion(OCUPACION); // devuelve la lista de usuarios
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
        }
    }



//    @PutMapping("/actualizar")
//    public Persona actualizarJugador(@RequestBody Persona jugador) {
//
//        Persona personaBuscar = personaRepositoryJugador.findPersonaByDni(jugador.getDni());
//
//
//
//        Optional<Usuario> buscarUsuarioBuscado = usuarioRepository.findById(usuario.getId());
//        // Se verifica que el objeto exista y se asigna los nuevo valores
//        if (buscarUsuarioBuscado.isPresent()){
//            Usuario usuarioActualizar =  buscarUsuarioBuscado.get();
//            usuarioActualizar.setNombre(usuario.getNombre());
//            usuarioActualizar.setApellido(usuario.getApellido());
//            usuarioActualizar.setDni(usuario.getDni());
//            usuarioActualizar.setEmail(usuario.getEmail());
//            usuarioActualizar.setTelefono(usuario.getTelefono());
//            usuarioRepository.save(usuarioActualizar);
//            return usuarioActualizar;
//        }else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario a actualizar.");
//        }
//    }








}
