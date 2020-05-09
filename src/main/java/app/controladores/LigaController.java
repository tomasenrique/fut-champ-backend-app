package app.controladores;


import app.entidades.Liga;
import app.repositoryCRUD.LigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/futchamp/liga")
public class LigaController {


    @Autowired
    private LigaRepository ligaRepository;


    @PostMapping("/agregar")
    public ResponseEntity<Liga> agregarLiga(@RequestBody Liga liga) {
        Liga addLiga = ligaRepository.save(liga);
        return ResponseEntity.status(HttpStatus.CREATED).body(addLiga);
    }

    // =================================================================================================================

    // Muestra todas las ligas disponibles
    @GetMapping("/mostrar")
    public Iterable<Liga> mostrarLigas() {
        try {
            return ligaRepository.findAll(); // Devuelve la lista de usuarios
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
        }
    }

    // Muestra una liga buscada por su nombre
    @GetMapping("/mostrar/nombre/{nombreLiga}")
    public Liga mostrarLiga(@PathVariable String nombreLiga) {
        Liga buscandoLiga = ligaRepository.findLigaByNombre(nombreLiga);
        if (buscandoLiga != null) {
            return buscandoLiga;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la liga buscada.");
        }
    }

    // =================================================================================================================

    @PutMapping("/actualizar")
    public Liga actualizarLiga(@RequestBody Liga liga) {
        Optional<Liga> ligaBuscada = ligaRepository.findById(liga.getId());
        if (ligaBuscada.isPresent()) {
            Liga ligaActualizar = ligaBuscada.get();
            ligaActualizar.setNombre(liga.getNombre()); // Actualiza el nombre
            ligaRepository.save(ligaActualizar);
            return ligaActualizar;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la liga a actualizar.");
        }
    }

    @DeleteMapping("/eliminar/{idLiga}")
    public ResponseEntity<?> eliminarLiga(@PathVariable Long idLiga) {
        Optional<Liga> buscarLigaEliminar = ligaRepository.findById(idLiga);

        if (buscarLigaEliminar.isPresent()) {
            Liga eliminarLiga = buscarLigaEliminar.get();
            ligaRepository.deleteById(eliminarLiga.getId());
            return ResponseEntity.status(HttpStatus.OK).body(eliminarLiga);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la liga a eliminar");
        }
    }

}
