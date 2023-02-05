package models;
public class Jugador extends EntidadEquipo{
  
  private String idEntrenador;
  private int numCamiseta;
  private int numGoles;
  private String posicion;
  
  public Jugador(String id, String idEquipo, String nombre, String apellido, String pais_natal, String idEntrenador,
  int numCamiseta, int numGoles, String posicion) {
    super(id, idEquipo, nombre, apellido, pais_natal);
    this.idEntrenador = idEntrenador;
    this.numCamiseta = numCamiseta;
    this.numGoles = numGoles;
    this.posicion = posicion;
  }
  
  public String toString() {
  
    return "[id="+getId()+", idEquipo="+getIdEntrenador()+", nombre="+getNombre()+", apellido="+ getApellido()+", paisNatal="+getPais_natal()+", numCamiseta="+getNumCamiseta()+", numeroGoles="+getNumGoles()+", posicion="+getPosicion()+"]";
  }

  public Jugador(){
    super();
  }
  
  public String getIdEntrenador() {
    return idEntrenador;
  }

  public void setIdEntrenador(String idEntrenador) {
    this.idEntrenador = idEntrenador;
  }

  public int getNumCamiseta() {
    return numCamiseta;
  }

  public void setNumCamiseta(int numCamiseta) {
    this.numCamiseta = numCamiseta;
  }

  public int getNumGoles() {
    return numGoles;
  }

  public void setNumGoles(int numGoles) {
    this.numGoles = numGoles;
  }

  public String getPosicion() {
    return posicion;
  }

  public void setPosicion(String posicion) {
    this.posicion = posicion;
  }
 

}
