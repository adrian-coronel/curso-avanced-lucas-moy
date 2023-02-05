package com.cursojava.curso.services;

import com.cursojava.curso.entities.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.StringHelper.isBlank;


// ¿CUANDO SE DEBE INDEXAR UNA PAGINA WEB? --> Puede ser en las noches o tod0 el tiempo como las arañas(spiders) de google


@Service //Indica que esta clase es un servicio
public class SpiderService {

    @Autowired
    private SearchService  searchService;


    public void indexWebPages(){ //ESTE METODO INDEXA MUCHAS PAGINAS WEBS ATRAVEZ DEL METODO "indexWebPage" de abajo
        //LA FUNCION DESCARGA LA PAGINA WEB>>>
        List<WebPage> linksToIndex =  searchService.getLinksToIndex();
        linksToIndex.stream().parallel().forEach(webPage -> {
            try {
                indexWebPage(webPage);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        });

    }

    //La idea de esta funcion es que procese tod0 el contenido de una pagina web para poder saber de que trata y porder guardarla en la base de datos
    private void indexWebPage(WebPage webPage) throws Exception {
        String url = webPage.getUrl();
        String content = getWebContent(url); //El metodo getWebContent me devuelve el "contenido" del la pagina del "link" (es el metodo de abajo)
        if (isBlank(content)) return;

        //<<<< LA PROCESA
        indexAndSaveWebPage(webPage, content);
        saveLinks(getDomain(url), content);
    }

    private String getDomain(String url) {
        String[] aux = url.split("/");
        return aux[0] + "//" + aux[2]; //Aqui me retorna el "dominio"
        // aux[0]-> https:
        // aux[1] -> ...NADA
        // aux[2] -> www.bbc.com
    }

    private void saveLinks(String domain, String content) {//Solamente guardará los links y termina el proceso
        List<String> links = getLinks(domain, content); //Aqui me dará todos los links mediante la funcion que cree abajo
        links.stream().filter(link -> !searchService.exist(link)) //Si el valor no existe en la BASE DE DATOS, que continuen
                      .map(link -> new WebPage(link))  //Recorro los links, uso un "map" para convertir cada link en un objet
                      .forEach(webPage -> searchService.save(webPage)); /*Luego de que ya estén convertidos, lo recorremos con un forEach
                                                                     para que cada uno se guarde con la funcion "save()"*/
    }

    private void goToNextWebPage(String content){ //La idea de este metodo es que siga indexando la proxima pagina

    }





    private void indexAndSaveWebPage(WebPage webPage, String content) {
        String title = getTitle(content); //Aqui llamo a la funcion de aca abajo para que me estraiga el titulo de la pagina(url)
        String description = getDescription(content);

        //<<<<< LA GUARDA EN LA BASE DE DATO
        webPage.setTitle(title);
        webPage.setDescription(description);
        searchService.save(webPage);  //Aqui le cargo los datos para que sean guardados
    }

    public List<String> getLinks(String domain, String content){
        List<String> links = new ArrayList<>();
        String[] splitHref = content.split("href=\""); //Aqui  digo que me va a cortar el href para que se me obtenga el link >>>> href=" ...link...

        List<String> listHref = Arrays.asList(splitHref); //Convierto el "splitHref" a una Lista
        //listHref.remove(0); //Se elimina lo que estaba antes del "href=\""
        //Esta linea de aca arriba lanza un error, por ello la comento, pero de todas maneras hay filtros en "cleanLinks" pasa por filtros
        listHref.forEach(strHref -> {
            String[] aux = strHref.split("\""); //Aqui indico que me haga un corte en la comilla al final del link, ASI DE ESTA FORMA QUEDANDOME EL LINK
            links.add(aux[0]);  //Me va a agregar al Array cada link que recorra
        });
        return cleanLinks(domain, links);  //Me devuelve los links limpiados(que no se colen links de img, js, inconos... etc)
    }

    private List<String> cleanLinks(String domain, List<String> links){
        String[] exclutedExtensions = new String[]{"css","js","json","jpg","png","woff2"};

         // ANYMATCH --> devuelve si algún elemento de esta secuencia coincide con el predicado proporcionado
         // NONEMATCH --> devuelve si ningún elemento de este flujo coincide con el predicado proporcionado
        List<String> resultLinks = links.stream().filter(link -> Arrays.stream(exclutedExtensions)
                .noneMatch(link::endsWith)) // "endsWith"  determina si una cadena de texto termina con los caracteres de una cadena indicada
                .map(link -> link.startsWith("/") ? domain + link : link) //Si el link comienza con "/" que me la concatene con el domain(dominio) y sino solo me entregue el "link"
                .filter(link -> link.startsWith("http"))
                .collect(Collectors.toList()); //Me lo convierte en Array (de los elementos filtrados)

        List<String> uniqueLinks = new ArrayList<>();
        uniqueLinks.addAll(new HashSet<>(resultLinks)); //Al convertirlo en un "SET" me garantiza que los valores no se repitiran

        return uniqueLinks;
    }


    /*<meta name="description" content="Breaking news, sport, TV, radio and a whole lot more.
        The BBC informs, educates and entertains - wherever you are, whatever your age.">
                <meta name="keywords" content="BBC, bbc.co.uk, bbc.com, Search, British Broadcasting Corporation, BBC iPlayer, BBCi">
        <title>BBC - Homepage</title>*/


    //Este metodo me devuelve el titulo de la pagina que se este cargando
    public String getTitle(String content){
        //el .split() me devuelve un Array de Strings cortandome el texto en 2 partes, es decir el primer elemento seria de <meta name="description" content="Breaki.... y el segundo >>>>  BBC - Homepage</title>
        String[] aux = content.split("<title>"); //Aqui corta el <title>, osea ya no estará.
        String[] aux2 = aux[1].split("</title>"); //Aqui corto o elimino </title> del segundo elemento, quedando como el primer valor >>>>> BBC - Homepage y el segundo lo que resta del HTML
        return aux2[0];
    }


    public String getDescription(String content){
        //el .split() me devuelve un Array de Strings cortandome el texto en 2 partes, es decir el primer elemento seria de <meta name="description" content="Breaki.... y el segundo >>>>  BBC - Homepage</title>
        String[] aux = content.split("<meta name=\"description\" content=\"");  //Esto -> \" me permite poner comillas dobles en en el texto
        String[] aux2 = aux[1].split("\">");
        return aux2[0];
    }


    private String getWebContent(String link){
        try{
            URL url = new URL(link); //Creo un objeto

            // (Aqui lo parseo xq no devuelve el mismo tipo)
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();// Me conecto a la URL
            String encoding = conn.getContentEncoding(); //Obtiene valores que especifican las codificaciones de contenido que se van a usar(Saber en que formato esta encodeado)

            /*---- Descarga del contenido ----*/
            InputStream input = conn.getInputStream();

            String resultado = new BufferedReader(new InputStreamReader(input)) //new InputStreamReader() es un lector de InputStream
                    .lines() // leera cada liena que se reciba, el joining() junta tod0 lo que haya en un string
                    .collect(Collectors.joining());//Se descarga en la variable String "resultado"
            /* *
             String resultado = new BufferedReader(new InputStreamReader(input)) //Estas clases nos sirven para leer archivos, procesar flujos de información que se van descargando
                    .lines()
                    .collect(Collectors.joining());
            * */

            return resultado;
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return ""; //Si hay error devuelve esto
    }

}
