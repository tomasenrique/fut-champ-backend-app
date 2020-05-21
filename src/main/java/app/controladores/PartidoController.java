package app.controladores;

import app.entidades.Calendario;
import app.entidades.Equipo;
import app.entidades.League;
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

@RestController
@RequestMapping(path = "/api/futchamp/partido")
public class PartidoController {

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private EquipoRepository obtenerDatosEquipoRepository;

    @Autowired
    private CalendarioRepository obtenerDatosCalendarioRepository;


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




    // =================================================================================================================


}
