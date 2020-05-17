package app.controladores;

import app.entidades.Equipo;
import app.entidades.League;
import app.repositoryCRUD.EquipoRepository;
import app.repositoryCRUD.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/futchamp/equipo")
public class EquipoController {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private LeagueRepository obtenerDatosLeagueRepository;

    @PostMapping("/agregar")
    public ResponseEntity<Equipo> agregarEquipo(@RequestBody Equipo equipo) {
        // Obtiene la liga por medio de su nombre para luego obtener su id
        League buscandoLeague = obtenerDatosLeagueRepository.findLigaByName(equipo.getLeague().getName());

        if (buscandoLeague != null) {
            equipo.setLeague(buscandoLeague); // Asigna el id de league
            Equipo addEquipo = equipoRepository.save(equipo); // guarda el equipo
            return ResponseEntity.status(HttpStatus.CREATED).body(addEquipo);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puedo agregar el equipo.");
        }
    }


    // =================================================================================================================

    // Muestra una lista de todos los equipos que hay en la liga
    @GetMapping("/mostrar")
    public Iterable<Equipo> mostrarEquipos() {
        try {
            return equipoRepository.findAll();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay equipos registrados.");
        }
    }

}
