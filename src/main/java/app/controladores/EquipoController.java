package app.controladores;

import app.entidades.Equipo;
import app.entidades.Liga;
import app.repositoryCRUD.EquipoRepository;
import app.repositoryCRUD.LigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/futchamp/equipo")
public class EquipoController {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private LigaRepository obtenerDatosLigaRepository;


    @PostMapping("/agregar")
    public ResponseEntity<Equipo> agregarEquipo(@RequestBody Equipo equipo) {
        Liga obtenerLiga = obtenerDatosLigaRepository.findLigaByNombre(equipo.getLiga().getNombre());

        if (obtenerLiga != null) {
            equipo.setLiga(obtenerLiga); // Asigna el id de liga
            Equipo addEquipo = equipoRepository.save(equipo);
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

    // Muestra una lista de los equipos por categoria
    @GetMapping("/mostrar/categoria/{categoria}")
    public Iterable<Equipo> mostrarEquiposCategoria(@PathVariable String categoria) {
        try {
            return equipoRepository.findEquipoByCategoria(categoria);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay equipos registrados.");
        }
    }

    // Muestra un equipo por su nombre
    @GetMapping("/mostrar/nombre/{nombre}")
    public Equipo mostrarEquiposNombre(@PathVariable String nombre) {
        Equipo buscandoEquipo = equipoRepository.findEquipoByNombre(nombre);

        if (buscandoEquipo != null) {
            return buscandoEquipo;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el equipo buscado.");
        }
    }

    // =================================================================================================================

    @PutMapping("actualizar")
    public Equipo actualizarEquipo(@RequestBody Equipo equipo) {
        Optional<Equipo> buscandoEquipoActualizar = equipoRepository.findById(equipo.getId());

        if (buscandoEquipoActualizar.isPresent()) {
            Equipo equipoActualizar = buscandoEquipoActualizar.get();
            equipoActualizar.setNombre(equipo.getNombre());
            equipoActualizar.setCategoria(equipo.getCategoria());
            equipoActualizar.setLogo(equipo.getLogo()); // Actualiza el logo el equipo
            equipoRepository.save(equipoActualizar);
            return equipoActualizar;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el equipo a actualizar.");
        }
    }


    @DeleteMapping("/eliminar/{idEquipo}")
    public ResponseEntity<?> eliminarLiga(@PathVariable Long idEquipo) {
        Optional<Equipo> buscarEquipoEliminar = equipoRepository.findById(idEquipo);

        if (buscarEquipoEliminar.isPresent()) {
            Equipo eliminarEquipo = buscarEquipoEliminar.get();
            equipoRepository.deleteById(eliminarEquipo.getId());
            return ResponseEntity.status(HttpStatus.OK).body(eliminarEquipo);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el equipo a eliminar");
        }
    }

}
