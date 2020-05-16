package app.controladores;

//@RestController
//@RequestMapping(path = "/api/futchamp/partido")
public class PartidoController {

//    @Autowired
//    private PartidoRepository partidoRepository;
//
//    @Autowired
//    private JornadaRepository obtenerDatosJornadaRepository;
//
//
//    @PostMapping("/agregar")
//    public ResponseEntity<Partido> agregarPartido(@RequestBody Partido partido) {
//        Jornada buscandoJornada = obtenerDatosJornadaRepository.findJornadaByReferencia(partido.getReferencia());
//        // Aqui se crea la referencia del partido
//        String refEquipo1 = partido.getEquipoA().substring(0, 4);
//        String refEquipo2 = partido.getEquipoA().substring(0, 4);
//        String referenciaPartido= refEquipo1 + refEquipo2 + partido.getFecha() + partido.getInicio();
//
//        if (buscandoJornada != null) {
//            partido.setReferencia(referenciaPartido.trim());
//            partido.setJornada(buscandoJornada); // asigna el id de la jornada
//            Partido addPartido = partidoRepository.save(partido);
//            return ResponseEntity.status(HttpStatus.CREATED).body(addPartido);
//        } else {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puedo agregar el partido.");
//        }
//    }
//
//
//    // =================================================================================================================
//
//    // Muestra una lista de todos los equipos que hay en la liga
//    @GetMapping("/mostrar")
//    public Iterable<Partido> mostrarPartidos() {
//        try {
//            return partidoRepository.findAll();
//        } catch (DataIntegrityViolationException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay partidos registrados.");
//        }
//    }

    // =================================================================================================================

    // Verificar este metodo, elimina toda la red - bloquado para no borrar
    // PARA EL METODO DE ELIMINAR
}
