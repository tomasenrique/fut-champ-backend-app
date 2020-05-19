package app.controladores;

import app.entidades.Equipo;
import app.entidades.Jugador;
import app.repositoryCRUD.EquipoRepository;
import app.repositoryCRUD.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/futchamp/jugador")
public class JugadorController {


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

    // Muestra una lista completa de todos los jugadores de todos los equipos
    @GetMapping("/mostrar")
    public Iterable<Jugador> mostrarJugadores() {
        try {
            return jugadorRepository.findAll(); // devuelve la lista de usuarios
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay jugadores registrados");
        }
    }


    //
    @GetMapping("/mostrar/")
    public void mostrarJugadoresEquipo() {




//        try {
//            return jugadorRepository.findAll(); // devuelve la lista de usuarios
//        } catch (DataIntegrityViolationException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay jugadores registrados");
//        }
    }


}
