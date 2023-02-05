package com.cursojava.curso.controllers;

import com.cursojava.curso.entities.WebPage;
import com.cursojava.curso.services.SearchService;
import com.cursojava.curso.services.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/* RestController recibe peticiones con un formato de específico que cumple con formatos de
   solicitud RESTful habitualmente y mayormente en JSON*/
@RestController
public class SearchController {

    @Autowired //Esta anotación carga automaticamente el objeto dentro de service
    private SearchService service;

    //Aca me lanso un error de CROSS -> Se da cuando hacemos un llamado a un servidor y este se encuentra en un puerto diferente(se da de los 2 lados)
    //@CrossOrigin("*") //Estoy diciendo que acepte un llamado de cualquier origen(Resulvo el problema de CROSS)(CUALQUIER PERSONA PODRA UTILIZAR ESTA REQUEST)
    //>>>>>>> POR EL MOMENTO NO USARE EL >>@CrossOrigin("*"), ya que pase los archivos a la carpeta "static"

    @Autowired
    private SpiderService spiderService;


    //Cuando se llame a la url "api/search" mediante el metodo "RequestMethod.GET" va a devolver algo
    @RequestMapping(value = "api/search", method = RequestMethod.GET)
    public List<WebPage> search(@RequestParam Map<String , String> params){ //La idea es recibir varios parametros
                            //@RequestParam -> Indica que vamos a pedir parametros
        String query = params.get("query");
        return service.search(query);
    }


    @RequestMapping(value = "api/test", method = RequestMethod.GET)
    public void search(){
        spiderService.indexWebPages();
    }


}
