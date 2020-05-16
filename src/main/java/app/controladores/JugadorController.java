package app.controladores;

//
//@RestController
//@RequestMapping(path = "/api/futchamp/jugador")
public class JugadorController {

//    private final String OCUPACION = Abreviaturas.JUG.getAbreviaturas();
//
//    @Autowired
//    private PersonaRepository personaRepositoryJugador;
//
//    @Autowired
//    private EquipoRepository obtenerDatosEquipoRepository;
//
//    @PostMapping("/agregar")
//    public ResponseEntity<Jugador> agregarJugador(@RequestBody Jugador jugador) {
//        Equipo equipoBuscado = obtenerDatosEquipoRepository.findEquipoByNombre(jugador.getEquipo().getNombre()); // Busca el equipo por su nombre
//
//        if (equipoBuscado != null) {
//            jugador.setEquipo(equipoBuscado); // asigna el id de equipoBuscado
//            Jugador addJugador = personaRepositoryJugador.save(jugador);
//            return ResponseEntity.status(HttpStatus.CREATED).body(addJugador);
//        }else {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puedo agregar el jugador.");
//        }
//    }
//
//    // =================================================================================================================
//
//    // Muestra una lista completa de todos los jugadores de todos los equipos
//    @GetMapping("/mostrar")
//    public Iterable<Jugador> mostrarJugadores() {
//        try {
//            return personaRepositoryJugador.findByOcupacion(OCUPACION); // devuelve la lista de usuarios
//        } catch (DataIntegrityViolationException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
//        }
//    }
//
//    // Muestra los jugadores de un equipo por medio del id de este.
//    @GetMapping("/mostrar/idEquipo/{idEquipo}")
//    public Iterable<Jugador> mostrarJugadoresEquipo(@PathVariable Long idEquipo) {
//        Optional<Equipo> buscandoEquipo = obtenerDatosEquipoRepository.findById(idEquipo); // busca equipo para obtener su id
//        try {
//            return personaRepositoryJugador.findByOcupacionAndEquipo(OCUPACION, buscandoEquipo.get());
//        } catch (DataIntegrityViolationException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
//        }
//    }
//
//    // Muestra un jugador por medio de su dni
//    @GetMapping("/mostrar/dni/{dni}")
//    public Jugador mostrarJugadorDni(@PathVariable String dni) {
//        Jugador buscandoJugador = personaRepositoryJugador.findByOcupacionAndDni(OCUPACION, dni);
//        if (buscandoJugador != null) {
//            return buscandoJugador;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
//        }
//    }
//
//    // =================================================================================================================
//
//    @PutMapping("/actualizar")
//    public Jugador actualizarJugador(@RequestBody Jugador jugador) {
//        Optional<Jugador> personaBuscar = personaRepositoryJugador.findById(jugador.getId());
//        if (personaBuscar.isPresent()) {
//            Jugador actualizarJugador = personaBuscar.get();
//            actualizarJugador.setNombre(jugador.getNombre());
//            actualizarJugador.setApellidos(jugador.getApellidos());
//            actualizarJugador.setDni(jugador.getDni());
//            actualizarJugador.setfNac(jugador.getfNac());
//            actualizarJugador.setEmail(jugador.getEmail());
//            actualizarJugador.setTelefono(jugador.getTelefono());
//            actualizarJugador.setImagen(jugador.getImagen()); // actualiza url de imagen
//            actualizarJugador.setPosicion(jugador.getPosicion());
//            actualizarJugador.setDorsal(jugador.getDorsal());
//            personaRepositoryJugador.save(actualizarJugador); // Actualiza datos
//            return actualizarJugador;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario a actualizar.");
//        }
//    }
//
//    @DeleteMapping("/eliminar/{idjugador}")
//    public ResponseEntity<?> eliminarJugador(@PathVariable Long idjugador) {
//        Optional<Jugador> buscarJugadorEliminar = personaRepositoryJugador.findById(idjugador);
//        if (buscarJugadorEliminar.isPresent()) {
//            Jugador eliminarJugador = buscarJugadorEliminar.get();
//            personaRepositoryJugador.deleteById(eliminarJugador.getId());
//            return ResponseEntity.status(HttpStatus.OK).body(eliminarJugador);
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el jugador a eliminar");
//        }
//    }


}
