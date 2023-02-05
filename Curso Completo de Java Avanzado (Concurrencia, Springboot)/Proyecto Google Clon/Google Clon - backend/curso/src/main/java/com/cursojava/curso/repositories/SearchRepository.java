package com.cursojava.curso.repositories;

import com.cursojava.curso.entities.WebPage;

import java.util.List;

//Aqui definimos la estructura que tendrá nuestro repositorio
public interface SearchRepository {

    WebPage getByUrl(String url);

    List<WebPage> getLinksToIndex();

    List<WebPage> search(String textSearch);

    void save(WebPage webPage);
    boolean exist(String link);
}
