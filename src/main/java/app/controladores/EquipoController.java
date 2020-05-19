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

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/futchamp/equipo")
public class EquipoController {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private LeagueRepository obtenerDatosLeagueRepository;

    @PostMapping("/agregar")
    public ResponseEntity<Equipo> agregarEquipo(@RequestBody Equipo equipo) {
        // Obtiene la league por medio de su nombre para luego obtener su id
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

    // Muestra una lista de todos los equipos que hay en todas las leagues
    @GetMapping("/mostrar")
    public Iterable<Equipo> mostrarEquipos() {
        try {
            return equipoRepository.findAll();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay equipos registrados.");
        }
    }

    // Muestra una lista de equipos de una misma league buscado por el nombre de esta
    @GetMapping("/mostrar/leagues/{nombreLeague}")
    public Iterable<Equipo> mostrarEquiposLeague(@PathVariable String nombreLeague) {
        League buscandoLeague = obtenerDatosLeagueRepository.findLigaByName(nombreLeague); // busca la league

        try {
            return equipoRepository.findEquipoByLeague(buscandoLeague); // DEvuelve la lista de equipos de una misma league
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay equipos registrados.");
        }
    }

    // Muestra un equipo buscado por su nombre
    @GetMapping("/mostrar/nombre/{nombreEquipo}")
    public Equipo mostrarEquipoNombre(@PathVariable String nombreEquipo) {
        Equipo buscandoEquipos = equipoRepository.findEquipoByName(nombreEquipo);

        if (buscandoEquipos != null) {
            return buscandoEquipos;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no existe el equipo buscado.");
        }
    }

    // Muestra un equipo buscado por su id de identificacion en la tabla
    @GetMapping("/mostrar/id/{idEquipo}")
    public Equipo mostrarEquipoId(@PathVariable Long idEquipo) {
        Optional<Equipo> buscandoEquipos = equipoRepository.findById(idEquipo);

        if (buscandoEquipos.isPresent()) {
            return buscandoEquipos.get(); // Devuelve el equipo buscado.
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no existe el equipo buscado.");
        }
    }

    // =================================================================================================================

    // Actualiza los datos de un equipo
    @PutMapping("actualizar")
    public Equipo actualizarEquipo(@RequestBody Equipo equipo) {
        Optional<Equipo> buscandoEquipoActualizar = equipoRepository.findById(equipo.getId());

        if (buscandoEquipoActualizar.isPresent()) {
            Equipo equipoActualizar = buscandoEquipoActualizar.get();
            equipoActualizar.setName(equipo.getName());
            equipoActualizar.setLogo(equipo.getLogo());
            // equipoActualizar.setLeague(equipo.getLeague()); // POR VERIFICAR
            equipoRepository.save(equipoActualizar);
            return equipoActualizar;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el equipo a actualizar.");
        }
    }


    // Eliminar un equipo por su id, Verificar este metodo, elimina toda la red - NO USAR, AUN SE ESTA HACIENDO PRUEBAS.
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
