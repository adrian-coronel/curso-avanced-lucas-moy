package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Jugador;

public class jugadorDao {

  private static Connection connMySql = conexion.conectarMySql();


    public static List<Jugador> listarJugadores() {
        List<Jugador> listado = new ArrayList<>(); //Creo mi ArrayList de jugador
        try {
            String sql = "SELECT * FROM `JUGADORES`";
            Statement statement = connMySql.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Jugador jugador = new Jugador();
                jugador.setId(rs.getString(1));
                jugador.setIdEntrenador(rs.getString(2));
                jugador.setIdEquipo(rs.getString(3));
                jugador.setNombre(rs.getString(4));
                jugador.setApellido(rs.getString(5));
                jugador.setNumCamiseta(rs.getInt(6));
                jugador.setNumGoles(rs.getInt(7));
                jugador.setPais_natal(rs.getString(8));
                jugador.setPosicion(rs.getString(9));
                listado.add(jugador);
            }
            statement.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return listado;
    }

}
