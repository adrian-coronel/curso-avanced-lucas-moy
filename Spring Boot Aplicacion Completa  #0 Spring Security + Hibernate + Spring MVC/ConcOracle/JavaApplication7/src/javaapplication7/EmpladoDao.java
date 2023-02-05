/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication7;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javaapplication7.Empleado;
public class EmpladoDao {
    
    
    private static Connection conn = null;
    private static String user = "C##CURSO";
    private static String password = "miryam2003";
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String driver = "oracle.jdbc.driver.OracleDriver";

    public static Connection getConnection(){
        try{
            /*Lo primero que se debe realizar para poder conectarse a una base de datos es cargar el
            driver encargado de ésta función. Para ello es utilizada la llamada Class. forName.*/
            Class.forName(driver);

            //DriverManager gestiona el conjunto de controladores Java Database Connectivity (JDBC)
            conn = DriverManager.getConnection(url,user,password);
            if (conn != null) {
                System.out.println("Conexion Exitosa");
            }else {
                System.out.println("Conexion erronea");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
           
        }
        return conn;
    }


    public static void agregar(Empleado empleado){
            Statement pst = null;
            //Representación de una sentencia SQL
            String sql = "insert into empleados(id_area,nombres, apellidos, dni,direccion,telefono,cargo,salario,fecha_ingreso,comision)"
                        +"values ("+empleado.getIdArea()+",'"+empleado.getNombres()+"','"+empleado.getApellidos()+"','"+empleado.getDni()+"','"+empleado.getDireccion()+"','"+empleado.getTelefono()+"'," +
                        "'"+empleado.getCargo()+"',"+empleado.getSalario()+",null,"+empleado.getComision()+");";
            try
            {
                pst = getConnection().createStatement();//pst recibe la conexion, lo que ejecutara la sentencia con los parametro insertados adelante
                System.out.println(sql);
                pst.execute(sql); //Para que se ejecute;
                getConnection().commit();
                pst.close();
        }catch (SQLException e){ e.getMessage();}
   }

    public static List<Empleado> Listar (){
        List<Empleado> lista = new ArrayList<>();
        String sql = "select * from empleados";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Empleado emp = new Empleado();
                emp.setIdEmpleado(resultSet.getString(1));
                emp.setIdArea(resultSet.getInt(2));
                emp.setNombres(resultSet.getString(3));
                emp.setApellidos(resultSet.getString(4));
                emp.setDni(resultSet.getString(5));
                emp.setDireccion(resultSet.getString(6));
                emp.setTelefono(resultSet.getString(7));
                emp.setCargo(resultSet.getString(8));
                emp.setSalario(resultSet.getDouble(9));
                emp.setFechaIngreso(resultSet.getString(10));
                emp.setComision(resultSet.getDouble(11));   
                lista.add(emp);
            }
        }catch (Exception e){
            e.getMessage();
        }
        return lista;
    }

    
}
