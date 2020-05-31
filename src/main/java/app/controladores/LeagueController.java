package app.controladores;

import app.entidades.League;
import app.repositoryCRUD.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/futchamp/league")
public class LeagueController {

    @Autowired
    private LeagueRepository leagueRepository;


    @PostMapping("/agregar")
    public ResponseEntity<League> agregarLeague(@RequestBody League league) {
        if (league != null && !leagueRepository.existsLeagueByName(league.getName())) {
            leagueRepository.save(league);
            return ResponseEntity.status(HttpStatus.CREATED).body(league);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puedo agregar la league..");
        }
    }

    // =================================================================================================================

    // Muestra todas las ligas disponibles
    @GetMapping("/mostrar")
    public Iterable<League> mostrarLeagues() {
        try {
            return leagueRepository.findAll(); // Devuelve la lista de usuarios
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay leagues registradas");
        }
    }

    // Muestra una liga buscada por su nombre
    @GetMapping("/mostrar/nombre/{nombreLeague}")
    public League mostrarLeagueNombre(@PathVariable String nombreLeague) {
        League buscandoLeague = leagueRepository.findLigaByName(nombreLeague);
        if (buscandoLeague != null) {
            return buscandoLeague;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la league buscada.");
        }
    }

    // Muestra una liga buscada por su id
    @GetMapping("/mostrar/id/{idLeague}")
    public League mostrarLeagueId(@PathVariable Long idLeague) {

        Optional<League> buscandoLeague = leagueRepository.findById(idLeague);
        if (buscandoLeague.isPresent()) {
            return buscandoLeague.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la league buscada.");
        }
    }

    // =================================================================================================================

    @PutMapping("/actualizar")
    public League actualizarLiga(@RequestBody League league) {
        Optional<League> leagueBuscada = leagueRepository.findById(league.getId());

        if (leagueBuscada.isPresent()) {
            League leagueActualizar = leagueBuscada.get();
            leagueActualizar.setName(league.getName()); // Actualiza el nombre
            leagueActualizar.setLogo(league.getLogo()); // Actualiza el logo si es necesario.
            leagueRepository.save(leagueActualizar);
            return leagueActualizar;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la league a actualizar.");
        }
    }

    // Verificar este metodo, elimina toda la red, NO USAR, AUN EN PRUEBAS
    @DeleteMapping("/eliminar/{idleague}")
    public ResponseEntity<?> eliminarLiga(@PathVariable Long idleague) {
        Optional<League> buscarLeagueEliminar = leagueRepository.findById(idleague);

        if (buscarLeagueEliminar.isPresent()) {
            League eliminarleague = buscarLeagueEliminar.get();
            leagueRepository.deleteById(eliminarleague.getId());
            return ResponseEntity.status(HttpStatus.OK).body(eliminarleague);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la league a eliminar");
        }
    }

}
