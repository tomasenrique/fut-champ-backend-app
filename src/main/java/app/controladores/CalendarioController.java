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

import java.util.ArrayList;
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

//    @Autowired
//    private MarcadorRepository obtenerDatosMarcadorRepository;

//    @Autowired
//    private PartidoRepository obtenerDatosPartidoRepository;

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


    // =================================================================================================================


    //delete - ==>> NO USAR AUN QUE ESTA EN PRUEBA
    @DeleteMapping("/eliminar")
    public void eliminarCalendario() {
        calendarioRepository.deleteAll();
    }


}
