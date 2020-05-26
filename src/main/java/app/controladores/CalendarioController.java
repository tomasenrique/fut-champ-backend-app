package app.controladores;

import app.entidades.Calendario;
import app.entidades.Equipo;
import app.entidades.League;
import app.entidades.Partido;
import app.repositoryCRUD.*;
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
@RequestMapping(path = "/api/futchamp/calendario")
public class CalendarioController {

    private static final Logger log = LoggerFactory.getLogger(CalendarioController.class);

    @Autowired
    private CalendarioRepository calendarioRepository;

    @Autowired
    private LeagueRepository obtenerDatosLeagueRepository;

    @Autowired
    private EquipoRepository obtenerDatosEquipoRepository;

    // Agrega un calendario y a la vez genera los partidos de una league
    @PostMapping("/agregar")
    public ResponseEntity<Calendario> agregarCalendario(@RequestBody Calendario calendario) {
        // Para obtenner el id de la league por medio del nombre de esta.
        League league = obtenerDatosLeagueRepository.findLigaByName(calendario.getLeague());
        // Obtiene una lista de equipos de una league
        List<Equipo> listaEquipos = obtenerDatosEquipoRepository.findEquipoByLeague(league);

        if (league != null) { // verifica que exista la liga
            if (listaEquipos != null) { // verifica que halla una lista de equipos

                calendario.generaCalendario(listaEquipos, calendario); // se la pasa la lista de equipos y el calendario para asignar id de este
                calendarioRepository.save(calendario);
                return ResponseEntity.status(HttpStatus.CREATED).body(calendario);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puedo agregar el calendario, no exixten equipos.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puedo agregar el calendario, no exixte la league.");
        }
    }

    // =================================================================================================================

    // Muestra todos los calendarios que generaron partidos
    @GetMapping("/mostrar")
    public Iterable<Calendario> mostrarCalendarios() {
        try {
            return calendarioRepository.findAll();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay Calendarios.");
        }
    }

    // Muestra un calendario buscado por su nombre y fecha de inicio
    // http://localhost:8080/api/futchamp/partido/mostrar/calendario?nombreLeagueCalendario=Primera Catalana&fechaInicio=2020-09-01
    @GetMapping("/mostrar/nombreFecha")
    public Calendario mostrarCalendario(@RequestParam String nombreLeagueCalendario, @RequestParam String fechaInicio) {
        // Busca el calendario para despues obtener su id
        Calendario buscandoCalendarioLeague = calendarioRepository.findCalendarioByLeagueAndFecha(nombreLeagueCalendario, LocalDate.parse(fechaInicio));

        if (buscandoCalendarioLeague != null) {
            return buscandoCalendarioLeague;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existe el calendario buscado.");
        }
    }


    // Muestra una lista de una league con sus fechas de inicio
    // http://localhost:8080/api/futchamp/calendario/mostrar/nombre?nombreLeagueCalendario=Primera Catalana
    @GetMapping("/mostrar/nombre")
    public Iterable<Calendario> mostrarCalendariosLeague(@RequestParam String nombreLeagueCalendario) {
        try {
            return calendarioRepository.findCalendarioByLeague(nombreLeagueCalendario);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existe el calendarios con este nombre.");
        }
    }

    // =================================================================================================================

    // Actualizar los datos de un calenndario
    @PutMapping("/actualizar")
    private ResponseEntity actualizarCalendario(@RequestBody Calendario calendario) {
        Optional<Calendario> buscandocalendarioActualizar = calendarioRepository.findById(calendario.getId());

        if (buscandocalendarioActualizar.isPresent()) {
            Calendario actualizandoCalendario = buscandocalendarioActualizar.get();
            actualizandoCalendario.setLeague(calendario.getLeague());
            actualizandoCalendario.setFecha(calendario.getFecha());
            actualizandoCalendario.setHora(calendario.getHora());
            calendarioRepository.save(actualizandoCalendario);
            return ResponseEntity.status(HttpStatus.OK).body(actualizandoCalendario);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puedo actualizar el calendario.");
        }
    }

    // Elimina calendario y los partidos de este buscado por su nombre y su fecha de inicio
    @DeleteMapping("eliminar/nombreFecha")
    private ResponseEntity<?> EliminarCalendarioNombreFecha(@RequestParam String nombreLeagueCalendario, @RequestParam String fechaInicio) {
        Calendario buscandoCalendarioEliminar = calendarioRepository.findCalendarioByLeagueAndFecha(nombreLeagueCalendario, LocalDate.parse(fechaInicio));

        if (buscandoCalendarioEliminar != null) {
            calendarioRepository.deleteById(buscandoCalendarioEliminar.getId());
            return ResponseEntity.status(HttpStatus.OK).body(buscandoCalendarioEliminar);
        }else{
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el calendario a borrar.");
        }
    }

    // Eliminar un calendario y los partidos de este
    @DeleteMapping("/eliminar")
    public void eliminarCalendarios() {
        calendarioRepository.deleteAll();
    }

}
