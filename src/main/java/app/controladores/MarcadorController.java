package app.controladores;

import app.entidades.Marcador;
import app.entidades.Partido;
import app.repositoryCRUD.MarcadorRepository;
import app.repositoryCRUD.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/futchamp/marcador")
public class MarcadorController {

    @Autowired
    private MarcadorRepository marcadorRepository;

    @Autowired
    private PartidoRepository obtenerDatosPartidoRepository;


    // Agrega un marcador a un partido usando su id para ubicarlo.
    @PostMapping("/agregar")
    public ResponseEntity<Marcador> agregarMarcador(@RequestBody Marcador marcador) {
        Optional<Partido> buscandoPartido = obtenerDatosPartidoRepository.findById(marcador.getPartido().getId());

        if (buscandoPartido.isPresent()) {
            marcador.setPartido(buscandoPartido.get()); // Se asigna id de partido
            marcadorRepository.save(marcador); // Se agrega marcador
            return ResponseEntity.status(HttpStatus.CREATED).body(marcador);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo agregar marcador al partido.");
        }
    }

    // =================================================================================================================

    // Muestra toda la lista de marcadores en donde se muesta la puntuacion de cada partido.
    @GetMapping("/mostrar")
    public Iterable<Marcador> mostrarMarcadores() {
        try {
            return marcadorRepository.findAll();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay marcadores en la lista.");
        }
    }

    // Busca el marcador de un partido por el id de este
    @GetMapping("/mostrar/marcador/{idPartido}")
    public Marcador mostrarMarcadorPartido(@PathVariable Long idPartido) {
        Optional<Partido> buscandoPartido = obtenerDatosPartidoRepository.findById(idPartido);

        if (buscandoPartido.isPresent()) {
           Partido partidoEncontrado = buscandoPartido.get();

            return marcadorRepository.findMarcadorByPartido(partidoEncontrado); // Devuelve el marcador del partido.
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay marcador para este partido.");
        }
    }

    // =================================================================================================================

    @PutMapping("/actualizar")
    public Marcador actualizarMarcador(@RequestBody Marcador marcador){
        Optional<Marcador> buscandoMarcadorActualizar = marcadorRepository.findById(marcador.getId());

        if (buscandoMarcadorActualizar.isPresent()) {
            Marcador actualizandoMarcador = buscandoMarcadorActualizar.get();
            actualizandoMarcador.setgLocal(marcador.getgLocal());
            actualizandoMarcador.setgVisitante(marcador.getgVisitante());
            marcadorRepository.save(actualizandoMarcador);
            return actualizandoMarcador;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el marcador a actualizar.");
        }
    }

    // Elimina un marcador por su id
    @DeleteMapping("/eliminar/id/{idMarcador}")
    public ResponseEntity<?> eliminarMarcadorId(@PathVariable Long idMarcador){
        Optional<Marcador> buscandoMarcadorEliminar = marcadorRepository.findById(idMarcador);

        if (buscandoMarcadorEliminar.isPresent()) {
            Marcador eliminandoMarcador =  buscandoMarcadorEliminar.get();
            marcadorRepository.deleteById(eliminandoMarcador.getId());
            return ResponseEntity.status(HttpStatus.OK).body(eliminandoMarcador);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el marcador a eliminar.");
        }
    }

    // Elimina todos los marcadores de la BBDD
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarMarcadores(){
        try {
            marcadorRepository.deleteAll();
            throw new ResponseStatusException(HttpStatus.OK, "Marcadores eliminados.");
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay marcadores para eliminar.");
        }
    }

}
