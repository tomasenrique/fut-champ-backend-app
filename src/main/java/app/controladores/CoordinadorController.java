package app.controladores;

import app.entidades.Coordinador;
import app.repositoryCRUD.CoordinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/futchamp/coordinador")
public class CoordinadorController {

    @Autowired
    private CoordinadorRepository coordinadorRepository;


    @PostMapping("/agregar")
    public ResponseEntity<Coordinador> agregarCoordinador(@RequestBody Coordinador coordinador) {

        if (coordinador != null) {
            coordinadorRepository.save(coordinador);
            return ResponseEntity.status(HttpStatus.CREATED).body(coordinador);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se puedo agregar el coordinador.");
        }
    }

    // =================================================================================================================

    // Muestra una lista de todos los usuarios con autorizacion.
    @GetMapping("/mostrar")
    public Iterable<Coordinador> mostrarCoordinadores() {
        try {
            return coordinadorRepository.findAll();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay coordinadores agregados.");
        }
    }


    // Este metodo se encarga de verificar si existe un usuario y si este tiene autorizacion devolviendo un Boleano
    // http://localhost:8080/api/futchamp/coordinador/verificarAutorizacion?usuario=tomas&clave=x9893913a
    @GetMapping("/verificarAutorizacion")
    public ResponseEntity<Boolean> verificarAutorizacion(@RequestParam String usuario, @RequestParam String clave) {
        Coordinador buscandoCoordinador = coordinadorRepository.findByUsuarioAndClave(usuario, clave);

        if (buscandoCoordinador != null) { // verifica que el objeto no sea nulo
            // Verificando que los datos son identicos
            if (usuario.equals(buscandoCoordinador.getUsuario()) && clave.equals(buscandoCoordinador.getClave())) {
                return ResponseEntity.status(HttpStatus.OK).body(Boolean.TRUE); // Si es asi, tiene acceso
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Boolean.FALSE);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Coordinador no existe o no tiene autorizacion.");
        }
    }

    // Este metodo obtiene el id del coordinador para poder usar el metodo PUT para actualizar la clave
    @GetMapping("/obtenerIdentificador")
    public ResponseEntity<Long> obtenerIdCoordinador(@RequestParam String usuario, @RequestParam String clave) {
        Coordinador buscandoCoordinadorId = coordinadorRepository.findByUsuarioAndClave(usuario, clave);

        if (buscandoCoordinadorId != null) {
            Long identificador = buscandoCoordinadorId.getId();
            return ResponseEntity.status(HttpStatus.OK).body(identificador); // Devuelve el id del coordinador.
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Coordinador no existe.");
        }
    }

    // =================================================================================================================

    // Para actualizar las contraseñas de un coordinador
    @PutMapping("/actualizar/clave")
    public ResponseEntity<?> actualizarCoordinadorClave(@RequestBody Coordinador coordinador){
        Optional<Coordinador> buscandoCoordinadorActualizar = coordinadorRepository.findById(coordinador.getId());

        if (buscandoCoordinadorActualizar.isPresent()) {
            Coordinador actualizarCoordinador = buscandoCoordinadorActualizar.get();
            actualizarCoordinador.setClave(coordinador.getClave());
            coordinadorRepository.save(actualizarCoordinador);
            throw new ResponseStatusException(HttpStatus.OK, "Contraseña Actualizada.");
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Coordinador no existe.");
        }
    }

    // =================================================================================================================
    @DeleteMapping("eliminar")
    public ResponseEntity<?> eliminarCoordinadores(){
        try {
            coordinadorRepository.deleteAll();
            throw new ResponseStatusException(HttpStatus.OK, "Coordinadores eliminados.");
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay coordinadores para eliminar.");
        }
    }


    // Elimina un coordinador por medio de su id
    @DeleteMapping("eliminar/id/{idCoordinador}")
    public ResponseEntity<?> eliminarCoordinador(@PathVariable Long idCoordinador){
        Optional<Coordinador> buscandoCoordinadorEliminar = coordinadorRepository.findById(idCoordinador);

        if (buscandoCoordinadorEliminar.isPresent()) {
            coordinadorRepository.deleteById(idCoordinador);
            throw new ResponseStatusException(HttpStatus.OK, "Coordinador eliminado.");
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el coordinador.");
        }
    }


}
