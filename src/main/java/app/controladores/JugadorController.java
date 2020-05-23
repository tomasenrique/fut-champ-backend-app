package app.controladores;

import app.entidades.Equipo;
import app.entidades.Jugador;
import app.repositoryCRUD.EquipoRepository;
import app.repositoryCRUD.JugadorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/futchamp/jugador")
public class JugadorController {

    private static final Logger log = LoggerFactory.getLogger(JugadorController.class);


    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private EquipoRepository obtenerDatosEquipoRepository;


    @PostMapping("/agregar")
    public ResponseEntity<Jugador> agregarJugador(@RequestBody Jugador jugador) {
        Equipo equipoBuscado = obtenerDatosEquipoRepository.findEquipoByName(jugador.getEquipo().getName()); // Busca el equipo por su nombre

        if (equipoBuscado != null) {
            jugador.setEquipo(equipoBuscado); // asigna el id de equipoBuscado
            Jugador addJugador = jugadorRepository.save(jugador);
            return ResponseEntity.status(HttpStatus.CREATED).body(addJugador);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puedo agregar el jugador.");
        }
    }

    // =================================================================================================================

    // Muestra una lista completa de todos los jugadores de todos los equipos de togas las ligas
    @GetMapping("/mostrar")
    public Iterable<Jugador> mostrarJugadores() {
        try {
            return jugadorRepository.findAll(); // devuelve la lista de usuarios
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay jugadores registrados");
        }
    }

    // Muestra una lista de jugadores de un mismo equipo
    @GetMapping("/mostrar/equipo/{nombreEquipo}")
    public Iterable<Jugador> mostrarJugadoresEquipo(@PathVariable String nombreEquipo) {
        Equipo buscandoEquipo = obtenerDatosEquipoRepository.findEquipoByName(nombreEquipo);

        try {
            return jugadorRepository.findJugadorByEquipo(buscandoEquipo);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay jugadores registrados.");
        }
    }

    // Muestra un jugador buscado por su dni
    @GetMapping("/mostrar/dni/{dniJugador}")
    public Jugador mostrarJugadorDni(@PathVariable String dniJugador) {
        Jugador buscandoJugador = jugadorRepository.findJugadorByDni(dniJugador); // Busca el jugador por su dni

        if (buscandoJugador != null) {
            return buscandoJugador;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el jugador buscado.");
        }
    }

    // Muestra un jugador buscado por su email
    @GetMapping("/mostrar/email/{emailJugador}")
    public Jugador mostrarJugadorEmail(@PathVariable String emailJugador) {
        Jugador buscandoJugador = jugadorRepository.findJugadorByEmail(emailJugador); // Busca el jugador por su email

        if (buscandoJugador != null) {
            return buscandoJugador;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el jugador buscado.");
        }
    }

    // Muestra un jugador buscado por su id de identificacion en la tabla
    @GetMapping("/mostrar/id/{idJugador}")
    public Jugador mostrarJugadorId(@PathVariable Long idJugador) {
        Optional<Jugador> buscandoJugador = jugadorRepository.findById(idJugador);

        if (buscandoJugador.isPresent()) {
            return buscandoJugador.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el jugador buscado.");
        }
    }

    // Muestra un jugador buscado por su nombre y apellido
    // http://localhost:8080/api/futchamp/jugador/mostrar/nombreApellidos?nombre=James&apellidos=Rodríguez
    @GetMapping("/mostrar/nombreApellidos")
    public Jugador mostrarJugadorNombreApellido(@RequestParam String nombre, @RequestParam String apellidos) {
        Jugador buscandoJugador = jugadorRepository.findJugadorByNombreAndApellidos(nombre, apellidos);

        if (buscandoJugador != null) {
            return buscandoJugador;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el jugador buscado.");
        }
    }

    // =================================================================================================================

    @PutMapping("/actualizar")
    public Jugador actualizarJugador(@RequestBody Jugador jugador) {
        Optional<Jugador> buscandoJugadorActualizar = jugadorRepository.findById(jugador.getId());

        if (buscandoJugadorActualizar.isPresent()) {
            Jugador actualizarJugador = buscandoJugadorActualizar.get();
            actualizarJugador.setNombre(jugador.getNombre());
            actualizarJugador.setApellidos(jugador.getApellidos());
            actualizarJugador.setDni(jugador.getDni());
            actualizarJugador.setEmail(jugador.getEmail());
            actualizarJugador.setImagen(jugador.getImagen()); // actualiza url de imagen
            actualizarJugador.setPosicion(jugador.getPosicion());
            actualizarJugador.setDorsal(jugador.getDorsal());
            jugadorRepository.save(actualizarJugador); // Actualiza datos
            return actualizarJugador;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario a actualizar.");
        }
    }

    // =================================================================================================================

    // Elimina toda la lista de jugadores de todos los equipos
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarJugadores() {
        try {
            jugadorRepository.deleteAll();
            throw new ResponseStatusException(HttpStatus.OK, "Lista de jugadores eliminada.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay jugadores que eliminar.");
        }
    }

    // Elimina un jugador buscado por su id de identificacion en la tabla
    @DeleteMapping("/eliminar/id/{idjugador}")
    public ResponseEntity<?> eliminarJugadorId(@PathVariable Long idjugador) {
        Optional<Jugador> buscarJugadorEliminar = jugadorRepository.findById(idjugador);
        if (buscarJugadorEliminar.isPresent()) {
            Jugador eliminarJugador = buscarJugadorEliminar.get();
            jugadorRepository.deleteById(eliminarJugador.getId());
            return ResponseEntity.status(HttpStatus.OK).body(eliminarJugador);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el jugador a eliminar");
        }
    }


    // Eliminar los jugadores de un mismo equipo
    @DeleteMapping("/eliminar/equipo/{nombreEquipo}")
    public ResponseEntity<?> eliminarJugadoresEquipo(@PathVariable String nombreEquipo) {
        Equipo buscandoEquipoEliminar = obtenerDatosEquipoRepository.findEquipoByName(nombreEquipo); // Busca el equipo

        try {
            // Obtiene todos loa jugadores de un mismo equipo
            List<Jugador> listaJugadoresEliminar = jugadorRepository.findJugadorByEquipo(buscandoEquipoEliminar);
            jugadorRepository.deleteAll(listaJugadoresEliminar); // Eliminar los jugadores
            throw new ResponseStatusException(HttpStatus.OK, "Lista de jugadores de un equipo eliminada.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay jugadores registrados.");
        }
    }


}
