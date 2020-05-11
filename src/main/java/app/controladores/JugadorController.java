package app.controladores;

import app.entidades.Equipo;
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

    private final String OCUPACION = Abreviaturas.JUG.getAbreviaturas();

    @Autowired
    private PersonaRepository personaRepositoryJugador;

    @Autowired
    private EquipoRepository obtenerDatosEquipoRepository;

    @PostMapping("/agregar")
    public ResponseEntity<Persona> agregarJugador(@RequestBody Persona jugador) {
        Equipo equipoBuscado = obtenerDatosEquipoRepository.findEquipoByNombre(jugador.getEquipo().getNombre()); // Busca el equipo por su nombre

        if (equipoBuscado != null) {
            jugador.setEquipo(equipoBuscado); // asigna el id de equipoBuscado
            Persona addJugador = personaRepositoryJugador.save(jugador);
            return ResponseEntity.status(HttpStatus.CREATED).body(addJugador);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puedo agregar el jugador.");
        }
    }

    // =================================================================================================================

    // Muestra una lista completa de todos los jugadores de todos los equipos
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
            Persona actualizarJugador = personaBuscar.get();
            actualizarJugador.setNombre(jugador.getNombre());
            actualizarJugador.setApellidos(jugador.getApellidos());
            actualizarJugador.setDni(jugador.getDni());
            actualizarJugador.setGenero(jugador.getGenero());
            actualizarJugador.setfNac(jugador.getfNac());
            actualizarJugador.setEmail(jugador.getEmail());
            actualizarJugador.setTelefono(jugador.getTelefono());
            actualizarJugador.setImagen(jugador.getImagen()); // actualiza url de imagen
            actualizarJugador.setOcupacion(jugador.getOcupacion());
            actualizarJugador.setPosicion(jugador.getPosicion());
            actualizarJugador.setDorsal(jugador.getDorsal());
            personaRepositoryJugador.save(actualizarJugador); // Actualiza datos
            return actualizarJugador;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario a actualizar.");
        }
    }

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
