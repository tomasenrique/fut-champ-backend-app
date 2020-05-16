package app.controladores;


import app.entidades.League;
import app.repositoryCRUD.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/futchamp/league")
public class LigaController {


    @Autowired
    private LeagueRepository leagueRepository;


    @PostMapping("/agregar")
    public ResponseEntity<League> agregarLiga(@RequestBody League league) {
        League addLeague = leagueRepository.save(league);
        return ResponseEntity.status(HttpStatus.CREATED).body(addLeague);
    }

    // =================================================================================================================

    // Muestra todas las ligas disponibles
    @GetMapping("/mostrar")
    public Iterable<League> mostrarLigas() {
        try {
            return leagueRepository.findAll(); // Devuelve la lista de usuarios
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
        }
    }

    // Muestra una liga buscada por su nombre
    @GetMapping("/mostrar/nombre/{nombreLiga}")
    public League mostrarLiga(@PathVariable String nombreLiga) {
        League buscandoLeague = leagueRepository.findLigaByName(nombreLiga);
        if (buscandoLeague != null) {
            return buscandoLeague;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la liga buscada.");
        }
    }

    // =================================================================================================================
//
//    @PutMapping("/actualizar")
//    public Liga actualizarLiga(@RequestBody Liga liga) {
//        Optional<Liga> ligaBuscada = ligaRepository.findById(liga.getId());
//        if (ligaBuscada.isPresent()) {
//            Liga ligaActualizar = ligaBuscada.get();
//            ligaActualizar.setNombre(liga.getNombre()); // Actualiza el nombre
//            ligaRepository.save(ligaActualizar);
//            return ligaActualizar;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la liga a actualizar.");
//        }
//    }
//
//    // Verificar este metodo, elimina toda la red - bloquado para no borrar
//    @DeleteMapping("/eliminar/{idLiga}")
//    public ResponseEntity<?> eliminarLiga(@PathVariable Long idLiga) {
//        Optional<Liga> buscarLigaEliminar = ligaRepository.findById(idLiga);
//
//        if (buscarLigaEliminar.isPresent()) {
//            Liga eliminarLiga = buscarLigaEliminar.get();
//            ligaRepository.deleteById(eliminarLiga.getId());
//            return ResponseEntity.status(HttpStatus.OK).body(eliminarLiga);
//        }else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la liga a eliminar");
//        }
//    }

}
