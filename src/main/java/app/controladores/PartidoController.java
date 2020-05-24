package app.controladores;

import app.entidades.Calendario;
import app.entidades.Equipo;
import app.entidades.Partido;
import app.repositoryCRUD.CalendarioRepository;
import app.repositoryCRUD.EquipoRepository;
import app.repositoryCRUD.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/futchamp/partido")
public class PartidoController {

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private EquipoRepository obtenerDatosEquipoRepository;

    @Autowired
    private CalendarioRepository obtenerDatosCalendarioRepository;


    // Agregar un nuevo partido usando los nombres de los equipos y del calendarios para obtener su id de cada uno
    @PostMapping("/agregar")
    public ResponseEntity<Partido> agregarPartido(@RequestBody Partido partido) {
        // Obtiene los datos del equipo local
        Equipo buscandoEquipoLocal = obtenerDatosEquipoRepository.findEquipoByName(partido.getLocal().getName());
        // Obtiene los datos del equipo visitante
        Equipo buscandoEquipoVisitante = obtenerDatosEquipoRepository.findEquipoByName(partido.getVisitante().getName());
        // Obtiene los datos de un calendario por medio de su nombre y fecha inicial
        Calendario buscandoCalendario = obtenerDatosCalendarioRepository.findCalendarioByLeagueAndFecha(partido.getCalendario().getLeague(), partido.getCalendario().getFecha());

        if (buscandoEquipoLocal != null) {
            if (buscandoEquipoVisitante != null) {
                if (buscandoCalendario != null) { // OJO CON LA FECHA QUE PARECE QUE HAY PROBLEMA ???
                    partido.setLocal(buscandoEquipoLocal);
                    partido.setVisitante(buscandoEquipoVisitante);
                    partido.setCalendario(buscandoCalendario);
                    partidoRepository.save(partido);
                    return ResponseEntity.status(HttpStatus.CREATED).body(partido);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo agregar el partido, no hay calendario");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se agrego el partido, no hay equipo visitante");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se agrego el partido, no hay equipo local");
        }
    }

    // =================================================================================================================

    // Muestra todos partidos que hay en la BBDD
    @GetMapping("/mostrar")
    public Iterable<Partido> mostrarPartidos() {
        try {
            return partidoRepository.findAll();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay partidos registrados.");
        }
    }

    // Muestra una lista de partidos por jornada
    @GetMapping("/mostrar/jornada/{jornada}")
    public Iterable<Partido> mostrarPartidosJornada(@PathVariable int jornada) {
        try {
            return partidoRepository.findPartidoByJornada(jornada);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay partidos registrados en la jornada.");
        }
    }

    // Muestra una lista de partidos que fueron creados por un calendario(league) que son buscados por el nombre de esta y su fecha de inicio
    // http://localhost:8080/api/futchamp/partido/mostrar/calendario?nombreLeagueCalendario=Primera Catalana&fechaInicio=2020-09-01
    @GetMapping("/mostrar/calendario")
    public Iterable<Partido> mostrarPartidosCalendario(@RequestParam String nombreLeagueCalendario, @RequestParam String fechaInicio) {
        // Busca el calendario para despues obtener su id
        Calendario buscandoCalendarioLeague =  obtenerDatosCalendarioRepository.findCalendarioByLeagueAndFecha(nombreLeagueCalendario, LocalDate.parse(fechaInicio));

        try {
            return partidoRepository.findPartidoByCalendario(buscandoCalendarioLeague);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay partidos registrados en este calendario.");
        }
    }

    // Muestra una lista de partidos locales de un equipo
    @GetMapping("/mostrar/locales/{nombreEquipoLocal}")
    public Iterable<Partido> mostrarPartidosLocales(@PathVariable String nombreEquipoLocal) {
        Equipo buscandoEquipoLocal = obtenerDatosEquipoRepository.findEquipoByName(nombreEquipoLocal);

        try {
            return partidoRepository.findPartidoByLocal(buscandoEquipoLocal);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay partidos locales de este equipo.");
        }
    }

    // Muestra una lista de partidos visitantes de un equipo
    @GetMapping("/mostrar/visitantes/{nombreEquipoVisitante}")
    public Iterable<Partido> mostrarPartidosVisitantes(@PathVariable String nombreEquipoVisitante) {
        Equipo buscandoEquipoVisitante = obtenerDatosEquipoRepository.findEquipoByName(nombreEquipoVisitante);

        try {
            return partidoRepository.findPartidoByVisitante(buscandoEquipoVisitante);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay partidos visitantes de este equipo.");
        }
    }

    // Muestra un partido buscado por su id
    @GetMapping("/mostrar/id/{idPartido}")
    public Partido mostrarPartidoId(@PathVariable Long idPartido) {
        Optional<Partido> buscandoPartidoId = partidoRepository.findById(idPartido);

        if (buscandoPartidoId.isPresent()) {
            return buscandoPartidoId.get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no existe el partido buscado.");
        }
    }

    // =================================================================================================================

    // Actualiza la fecha, hora y jornada de un partido de una league
    @PutMapping("/actualizar")
    public Partido actualizarPartido(@RequestBody Partido partido){
        Optional<Partido> buscandoPartidoActualizar = partidoRepository.findById(partido.getId());

        if (buscandoPartidoActualizar.isPresent()) {
            Partido actualizandoPartido = buscandoPartidoActualizar.get();
            actualizandoPartido.setFecha(partido.getFecha());
            actualizandoPartido.setHora(partido.getHora());
            actualizandoPartido.setJornada(partido.getJornada());
            return partidoRepository.save(actualizandoPartido);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar el partido.");
        }
    }

    // =================================================================================================================

    // Elimina un partido buscado por su id
    @DeleteMapping("/eliminar/id/{idPartido}")
    public ResponseEntity<?> eliminarPartido(@PathVariable Long idPartido){
        Optional<Partido> buscandoPartidoEliminar = partidoRepository.findById(idPartido);

        if (buscandoPartidoEliminar.isPresent()) {
            Partido eliminandoPartido = buscandoPartidoEliminar.get();
            partidoRepository.deleteById(eliminandoPartido.getId());
            return ResponseEntity.status(HttpStatus.OK).body(eliminandoPartido);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puedo eliminar el partido.");
        }
    }

    // Elimina todo los partidos que hay en la BBDD
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarPartidos(){
        try {
            partidoRepository.deleteAll();
            throw new ResponseStatusException(HttpStatus.OK, "Lista de partidos eliminada.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay partidos que eliminar.");
        }
    }

}
