package app.controladores;


import app.entidades.Calendario;
import app.entidades.Equipo;
import app.entidades.League;
import app.repositoryCRUD.CalendarioRepository;
import app.repositoryCRUD.EquipoRepository;
import app.repositoryCRUD.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(path = "/calendario")
public class CalendarioController {
    @Autowired
    private CalendarioRepository calendarioRepository;
    @Autowired
    private LeagueRepository ligaRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    //add
    @PostMapping("/add/{nLiga}")
    public void addCalendario(@PathVariable("nLiga") String nLiga){
        League liga= ligaRepository.findLigaByName(nLiga);
        Calendario calendario= new Calendario();


        List<Equipo> listaEquipos = (List<Equipo>) equipoRepository.findAll();


        calendario.generaCalendario(listaEquipos, LocalDate.of(2020,9,01), LocalTime.of(10,30));
        calendarioRepository.save(calendario);
    }
    //get
    @GetMapping("/mostrar")
    public Iterable<League> mostrarLigas() {
        try {
            return ligaRepository.findAll();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay ligas registrados");
        }
    }

    //delete
    @DeleteMapping("/deleteAll")
    public void delCalendario(){
        calendarioRepository.deleteAll();
    }

}
