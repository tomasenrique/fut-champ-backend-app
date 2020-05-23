package app.controladores;

import app.entidades.Marcador;
import app.repositoryCRUD.MarcadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping(path = "/api/futchamp/marcador")
public class MarcadorController {

//    @Autowired
//    private MarcadorRepository marcadorRepository;
//
//
//
//
//
//    @GetMapping("/mostrar")
//    public Iterable<Marcador> mostrarMarcadores(){
//
//        try {
//            marcadorRepository.findAll();
//        }catch (DataIntegrityViolationException e){
//            return throw
//
//        }
//
//
//
//
//    }
}
