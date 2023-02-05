package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Cuando puse esto me importo la libreria
public class UsuarioController {

    @RequestMapping(value = "prueba")
    public List<String> prueba(){
        return List.of("Manzana","Banana","Kiwi");
    }
}
