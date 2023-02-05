package com.cursojava.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

//@Configuration //La anotación @Configuration indica que la clase sobre la que se encuentra aplicada debe ser usada como parte de la configuración de Spring
//@EnableScheduling //INDICA QUE ADMITE TAREAS PROGRAMADAS
public class ScheduleService {

    @Autowired
    private SpiderService spiderService;
/*
    //AQUI INDICO QUE SE EJECUTE EN EL MOMENTO ESPECIFICADO
    @Scheduled(cron = "0 0 0 * * ?") //--> 0:HORA  0:MINUTO  0:SEGUNDO  *  * : TODOS LOS DIAS
    public void scheduleIndexWebPages(){
        spiderService.indexWebPages(); //que se indexcen las paginas web a esa hora
    }
*/
}
