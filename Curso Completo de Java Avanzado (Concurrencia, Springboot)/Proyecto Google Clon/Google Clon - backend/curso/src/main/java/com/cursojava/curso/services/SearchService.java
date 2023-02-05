package com.cursojava.curso.services;

import com.cursojava.curso.entities.WebPage;
import com.cursojava.curso.repositories.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service //Esta anotación indica que es un servicio
// Hace que se cree un objeto cuando se levante el servicio automaticamente, sin necesidad de instancarla --> SearchServices service;
public class SearchService {

    @Autowired //Esta anotación carga automaticamente el objeto dentro de service
    private SearchRepository repository;

    public List<WebPage> search(String textSeach){
         return repository.search(textSeach); //Aqui llamo al metodo "search" que se encuentra en la interface "SearchRepository"
    }

    public void save(WebPage webPage){ //Aca me va a guardar los valores del SpiderSERVICE
        repository.save(webPage); //Aqui guarda el objeto
    }

    public boolean exist(String link){
        return repository.exist(link);
    }

    public List<WebPage> getLinksToIndex(){
        return repository.getLinksToIndex();
    }

}
