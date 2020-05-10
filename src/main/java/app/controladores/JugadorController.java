package app.controladores;

import app.entidades.Equipo;
import app.entidades.Liga;
import app.entidades.Persona;
import app.enumeradores.Abreviaturas;
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

    final String OCUPACION = Abreviaturas.JUG.getAbreviaturas();

    @Autowired
    private PersonaRepository personaRepositoryJugador;

    @Autowired
    private EquipoRepository obtenerDatosEquipoRepository;

    @PostMapping("/agregar")
    public ResponseEntity<Persona> agregarJugador(@RequestBody Persona jugador) {
        Equipo equipoBuscado = obtenerDatosEquipoRepository.findEquipoByNombre(jugador.getEquipo().getNombre()); // Busca el equipo por su nombre
        jugador.setEquipo(equipoBuscado); // asigna el id de equipoBuscado
        Persona addJugador = personaRepositoryJugador.save(jugador);
        return ResponseEntity.status(HttpStatus.CREATED).body(addJugador);
    }

    // =================================================================================================================

    // Muestra una lista completa de todos los jugadores de todos lo equipos
    @GetMapping("/mostrar")
    public Iterable<Persona> mostrarJugadores() {
        try {
            return personaRepositoryJugador.findByOcupacion(OCUPACION); // devuelve la lista de usuarios
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
        }
    }

    // Muestra los jugadores de un equipo por medio del id de este.
    @GetMapping("/mostrar/idEquipo/{idEquipo}")
    public Iterable<Persona> mostrarJugadoresEquipo(@PathVariable Long idEquipo) {
        Optional<Equipo> buscandoEquipo = obtenerDatosEquipoRepository.findById(idEquipo); // busca equipo para obtener su id
        try {
            return personaRepositoryJugador.findByOcupacionAndEquipo(OCUPACION, buscandoEquipo.get());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
        }
    }

    // Muestra un jugador por medio de su dni
    @GetMapping("/mostrar/dni/{dni}")
    public Persona mostrarJugadorDni(@PathVariable String dni) {
        Persona buscandoJugador = personaRepositoryJugador.findByOcupacionAndDni(OCUPACION, dni);
        if (buscandoJugador != null) {
            return buscandoJugador;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
        }
    }

    // =================================================================================================================

    @PutMapping("/actualizar")
    public Persona actualizarJugador(@RequestBody Persona jugador) {
        Optional<Persona> personaBuscar = personaRepositoryJugador.findById(jugador.getId());
        if (personaBuscar.isPresent()) {
            Persona actualizarPersona = personaBuscar.get();
            actualizarPersona.setNombre(jugador.getNombre());
            actualizarPersona.setApellidos(jugador.getApellidos());
            actualizarPersona.setDni(jugador.getDni());
            actualizarPersona.setGenero(jugador.getGenero());
            actualizarPersona.setfNac(jugador.getfNac());
            actualizarPersona.setEmail(jugador.getEmail());
            actualizarPersona.setTelefono(jugador.getTelefono());
            actualizarPersona.setOcupacion(jugador.getOcupacion());
            actualizarPersona.setPosicion(jugador.getPosicion());
            actualizarPersona.setDorsal(jugador.getDorsal());
            personaRepositoryJugador.save(actualizarPersona); // Actualiza datos
            return actualizarPersona;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario a actualizar.");
        }
    }

//    @DeleteMapping("/eliminar/{idjugador}")
////    public ResponseEntity<?> eliminarJugador(@PathVariable Long idjugador) {
////        Optional<Persona> buscarJugadorEliminar = personaRepositoryJugador.findById(idjugador);
////        if (buscarJugadorEliminar.isPresent()) {
////            Persona eliminarJugador = buscarJugadorEliminar.get();
////            personaRepositoryJugador.deleteById(eliminarJugador.getId());
////            return ResponseEntity.status(HttpStatus.OK).body(eliminarJugador);
////        } else {
////            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el jugador a eliminar");
////        }
////    }

    @DeleteMapping("/eliminar/{idjugador}")
    public ResponseEntity<?> eliminarJugador(@PathVariable Long idjugador) {
        Optional<Persona> buscarJugadorEliminar = personaRepositoryJugador.findById(idjugador);
        if (buscarJugadorEliminar.isPresent()) {
            Persona eliminarJugador = buscarJugadorEliminar.get();
            personaRepositoryJugador.deleteById(eliminarJugador.getId());
            return ResponseEntity.status(HttpStatus.OK).body(eliminarJugador);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el jugador a eliminar");
        }
    }


}
