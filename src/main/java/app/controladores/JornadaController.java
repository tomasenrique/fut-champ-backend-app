package app.controladores;

//@RestController
//@RequestMapping(path = "/api/futchamp/jornada")
public class JornadaController {

//
//    @Autowired
//    private JornadaRepository jornadaRepository;
//
//    @Autowired
//    private LigaRepository obtenerDatosLigaRepository;
//
//
//    @PostMapping("/agregar")
//    public ResponseEntity<Jornada> agregarJornada(@RequestBody Jornada jornada) {
//        Liga buscandoLiga = obtenerDatosLigaRepository.findLigaByNombre(jornada.getLiga().getNombre());
//
//        if (buscandoLiga != null) {
//            jornada.setLiga(buscandoLiga);
//            Jornada addJornada = jornadaRepository.save(jornada);
//            return ResponseEntity.status(HttpStatus.CREATED).body(addJornada);
//        } else {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo agregar la jornada.");
//        }
//    }
//
//    // =================================================================================================================
//
//    // Muestra una lista completa de las jornadas
//    @GetMapping("/mostrar")
//    public Iterable<Jornada> mostrarJornadas() {
//        try {
//            return jornadaRepository.findAll();
//        } catch (DataIntegrityViolationException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay jornadas registradas.");
//        }
//    }
//
//
//    // Muestra una jornadas por su referencia
//    @GetMapping("/mostrar/referencia/{referencia}")
//    public Jornada mostrarJornadaReferencia(@PathVariable String referencia) {
//        Jornada buscandoJornada = jornadaRepository.findJornadaByReferencia(referencia);
//
//        if (buscandoJornada != null) {
//            return buscandoJornada;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la jornada.");
//        }
//    }
//
//    // =================================================================================================================
//
//    @PutMapping("/actualizar")
//    public Jornada actualizarJornada(@RequestBody Jornada jornada) {
//        Optional<Jornada> buscandoJornadaActualizar = jornadaRepository.findById(jornada.getId());
//
//        if (buscandoJornadaActualizar.isPresent()) {
//            Jornada actualizarJornada = buscandoJornadaActualizar.get();
//            actualizarJornada.setReferencia(jornada.getReferencia());
//            actualizarJornada.setFecha(jornada.getFecha());
//            jornadaRepository.save(actualizarJornada);
//            return actualizarJornada;
//
//        } else {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar la jornada.");
//        }
//    }
//
//
//    // Verificar este metodo, elimina toda la red - bloquado para no borrar
//    @DeleteMapping("/eliminar/{idJornada}")
//    public ResponseEntity<?> eliminarJornada(@PathVariable Long idJornada){
//        Optional<Jornada> buscandoJornadaEliminar = jornadaRepository.findById(idJornada);
//
//        if (buscandoJornadaEliminar.isPresent()) {
//            Jornada jornadaEliminar = buscandoJornadaEliminar.get();
//            jornadaRepository.deleteById(jornadaEliminar.getId());
//            return ResponseEntity.status(HttpStatus.OK).body(jornadaEliminar);
//        }else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar la jornada.");
//        }
//    }


}
