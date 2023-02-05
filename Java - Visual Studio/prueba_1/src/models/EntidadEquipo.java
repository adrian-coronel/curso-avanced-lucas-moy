package models;

public class EntidadEquipo {

    private String id;
    private String idEquipo;
    private String nombre;
    private String apellido;
    private String pais_natal;

    public EntidadEquipo(String id, String idEquipo, String nombre, String apellido, String pais_natal) {
        this.id = id;
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pais_natal = pais_natal;
    }



    public EntidadEquipo(){}

    

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getIdEquipo() {
        return idEquipo;
    }
    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getPais_natal() {
        return pais_natal;
    }
    public void setPais_natal(String pais_natal) {
        this.pais_natal = pais_natal;
    }
    
}