package com.cursojava.curso.repositories;

import com.cursojava.curso.entities.WebPage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SearchRepositoyImp implements SearchRepository{

    @PersistenceContext //Nos va a cargar el entityManager  junto a la conexion a la Base de Datos, mediante eso puedo hacer la consulta
    EntityManager entityManager; //EntityManager maneja la interacción de base de datos y los metadatos para correlaciones relacionales de objetos.

    @Override
    public WebPage getByUrl(String url) { //Este metodo me retornarpa TRUE si el link existe en la BASE DE DATOS
        //EL METODO ME DEVUELVE UN WebPage, apartir de la url que busque en la BASE DE DATOS
        String query = "FROM WebPage WHERE url = :url";
        List<WebPage> list = entityManager.createQuery(query)
                .setParameter("url",url)
                .getResultList(); //Me obtiene el resultado de la "query"
        return list.size() == 0 ? null : list.get(0);
    }



    @Override //Esta funcion será la que recorrera los links de la BASE DE DATOS
    public List<WebPage> getLinksToIndex() {
        String query = "FROM WebPage WHERE title is null AND description is null"; //Aca me va a traer solamente los links
        //La sentencia anterior no es SQL, es el sql de Hibernate
        return entityManager.createQuery(query)
                .setMaxResults(100) //>>>> ACA LIMITO LA CANTIDAD DE PAGINAS A INDEXAR EN LA NOCHE
                .getResultList();
    }



    @Transactional //Cuando se realizen las consultas en SQL, indica que haga una transaccion de SQL unicamente en el metodo (si fueran varios llamados a la BD lo envuelve en una sola consulta)
    @Override
    public List<WebPage> search(String textSearch) {
        String query = "FROM WebPage WHERE description like :textSearch"; //WebPage hace referencia a la clase.
        //La sentencia anterior no es SQL, es el sql de Hibernate
        return entityManager.createQuery(query)
                .setParameter("textSearch", "%"+ textSearch +"%" ) //Este setParameter me permite crear parametros independientes del tipo.
                .getResultList();
    }

    @Transactional //Defino que se realiza una transación para la base de datos
    @Override
    public void save(WebPage webPage) {
        entityManager.merge(webPage);
    }

    @Override
    public boolean exist(String url) {
        return getByUrl(url) != null;
    }
}

/* Hibernate convertirá los datos entre los tipos utilizados por Java y los definidos por SQL, luego generará
   sentencias SQL y liberará al desarrollador del manejo manual de los datos, manteniendo la portabilidad entre
   todos los motores de bases de datos acelerando el tiempo de ejecución.
   VIENE SIENDO ESE PUENTE INTERMEDIO ENTRE LA BD Y EL PROYECTO
 */
