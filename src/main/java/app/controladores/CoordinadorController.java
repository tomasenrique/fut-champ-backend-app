package app.controladores;

import app.entidades.Acceso;
import app.entidades.Equipo;
import app.entidades.Persona;
import app.enumeradores.Abreviaturas;
import app.repositoryCRUD.AccesoRepository;
import app.repositoryCRUD.EquipoRepository;
import app.repositoryCRUD.PersonaRepository;
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

    private final String OCUPACION = Abreviaturas.COO.getAbreviaturas();

    @Autowired
    private PersonaRepository personaRepositoryCoordinador;

    @Autowired
    private AccesoRepository accesoRepository;

    @Autowired
    private EquipoRepository obtenerDatosEquipoRepository;


    // Agrega un coordinador y crea una clave automatica por defecto para luego actualizarla
    @PostMapping("/agregar")
    public ResponseEntity<Persona> agregarCoordinadorClave(@RequestBody Persona coordinador) {
        Equipo equipoBuscado = obtenerDatosEquipoRepository.findEquipoByNombre(coordinador.getEquipo().getNombre()); // Busca el equipo por su nombre

        if (equipoBuscado != null) {
            coordinador.setEquipo(equipoBuscado); // asigna el id de equipoBuscado
            Persona addCoordinador = personaRepositoryCoordinador.save(coordinador); // Guarda el coordinador
            Acceso claveCompuesta = new Acceso(coordinador.getDni() + coordinador.getfNac(), coordinador); // genera clave automatica
            accesoRepository.save(claveCompuesta); // Guarda la clave generada del coordinador.
            return ResponseEntity.status(HttpStatus.CREATED).body(addCoordinador); //Devuelve el objeto creado menos la clave
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puedo agregar el coordinador.");
        }
    }

    // =================================================================================================================

    // Muestra una lista completa de todos los coordinadores de todos los equipos
    @GetMapping("/mostrar")
    public Iterable<Persona> mostrarCoordinadores() {
        try {
            return personaRepositoryCoordinador.findByOcupacion(OCUPACION); // devuelve la lista de usuarios
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
        }
    }

    // Muestra los coordinadores de un equipo por medio del id de este.
    @GetMapping("/mostrar/idEquipo/{idEquipo}")
    public Iterable<Persona> mostrarCoordinadoresEquipo(@PathVariable Long idEquipo) {
        Optional<Equipo> buscandoEquipo = obtenerDatosEquipoRepository.findById(idEquipo); // busca equipo para obtener su id

        if (buscandoEquipo.isPresent()) {
            return personaRepositoryCoordinador.findByOcupacionAndEquipo(OCUPACION, buscandoEquipo.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
        }
    }

    // Muestra un coordinador por medio de su dni
    @GetMapping("/mostrar/dni/{dni}")
    public Persona mostrarCoordinadorDni(@PathVariable String dni) {
        Persona buscandoJugador = personaRepositoryCoordinador.findByOcupacionAndDni(OCUPACION, dni);
        if (buscandoJugador != null) {
            return buscandoJugador;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
        }
    }


    /**
     * CREAR AQUI UN METODO PARA VERIFICAR ACCESO DE UN COORDINADOR POR MEDIO DE SU DNI Y SU CLAVE DE ACCESO QUE DEVUELVA
     * COMO RESPUESTA TRUE O FALSE PARA ACCEDER A LA APLICACION MOVIL
     */

    // =================================================================================================================

    // Actualiza los datos de un coordinador
    @PutMapping("/actualizar")
    public Persona actualizarCoordinador(@RequestBody Persona coordinador) {
        Optional<Persona> personaCoordinadorBuscar = personaRepositoryCoordinador.findById(coordinador.getId());
        if (personaCoordinadorBuscar.isPresent()) {
            Persona actualizarCoordinador = personaCoordinadorBuscar.get();
            actualizarCoordinador.setNombre(coordinador.getNombre());
            actualizarCoordinador.setApellidos(coordinador.getApellidos());
            actualizarCoordinador.setDni(coordinador.getDni());
            actualizarCoordinador.setGenero(coordinador.getGenero());
            actualizarCoordinador.setfNac(coordinador.getfNac());
            actualizarCoordinador.setEmail(coordinador.getEmail());
            actualizarCoordinador.setTelefono(coordinador.getTelefono());
            actualizarCoordinador.setImagen(coordinador.getImagen()); // actualiza url de imagen
            actualizarCoordinador.setOcupacion(coordinador.getOcupacion());
            actualizarCoordinador.setCargo(coordinador.getCargo());
            personaRepositoryCoordinador.save(actualizarCoordinador); // Actualiza datos
            return actualizarCoordinador;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el coordinador a actualizar.");
        }
    }

    // Actualizar la clave de acceso de un coordinador por medio de su id de registro y agregando la nueva clave
    // Enlace de acceso ==>> http://localhost:8080/api/futchamp/coordinador/actualizar/clave?idCoordinador=15&nuevaClave=t5e7e7t6-1980-06
    @PutMapping("/actualizar/clave")
    public ResponseEntity<?> actualizarCoordinadorClave(@RequestParam Long idCoordinador, @RequestParam String nuevaClave) {
        Optional<Persona> buscandoCoordinador = personaRepositoryCoordinador.findById(idCoordinador); // Obtiene el coordinador

        if (buscandoCoordinador.isPresent()) { // verifica que no sea nulo el coordinador
            Acceso actualizarAccesoCoordinador = accesoRepository.findAccesoByPersona(buscandoCoordinador.get()); // Obtiene la clave a actualizar
            if (actualizarAccesoCoordinador != null) { // verifica que no sea nula la clave
                actualizarAccesoCoordinador.setClave(nuevaClave); // Actualiza la clave del coordinador
                accesoRepository.save(actualizarAccesoCoordinador); // Guarda la nueva clave del coordinador
                return ResponseEntity.status(HttpStatus.OK).body(actualizarAccesoCoordinador); // ==>> VERIFICAR COMO CODIFICAR ESTE DATO ?
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar la clave.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar la clave del coordinador.");
        }
    }

    // =================================================================================================================

    // Elimina un coordinador y su clave de acceso
    @DeleteMapping("/eliminar/{idCoordinador}")
    public ResponseEntity<?> eliminarJugador(@PathVariable Long idCoordinador) {
        Optional<Persona> buscarCoordinadorEliminar = personaRepositoryCoordinador.findById(idCoordinador);

        if (buscarCoordinadorEliminar.isPresent()) {
            Persona coordinadorEliminar = buscarCoordinadorEliminar.get();
            personaRepositoryCoordinador.deleteById(coordinadorEliminar.getId());
            return ResponseEntity.status(HttpStatus.OK).body(coordinadorEliminar);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el coordinador a eliminar");
        }
    }

}
