/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication7;

import javaapplication7.EmpladoDao;
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for(Empleado element : EmpladoDao.Listar()){
            System.out.println(element.toString());
        }
    }
    
}
