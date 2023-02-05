package com.cursojava.curso.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity //Con "@Entity" indicamos que esta clase es una entidad, lo que se usará para la conexion con la BD
@Table(name = "webpage") // "@Table" indica a que tabla va a hacer referencia en la Base de Datos

@Getter @Setter
@ToString @EqualsAndHashCode


public class WebPage {

    @Id //Indico que esta variable será el "ID"(columna principal)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indico que el ID será AUTOINCREMENT(se va aumentando 1 valor por registro) --> notación solo para el Id
    @Column(name = "id")
    private Long id;

    @Column(name = "url")//Indico el nombre de columna a cada variable
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    public WebPage(){}

    public WebPage(String url){
        this.url = url;
    }

}
