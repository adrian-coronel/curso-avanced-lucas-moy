/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication7;

/**
 *
 * @author Rocky
 */
public class Empleado {

    private String idEmpleado;
    private int idArea;
    private String nombres;
    private String apellidos;
    private String dni;
    private String direccion;
    private String telefono;
    private String cargo;
    private double salario;
    private String fechaIngreso;
    private double comision;

    public Empleado(){}

    public Empleado(int idArea, String nombres, String apellidos, String dni, String direccion, String telefono, String cargo, double salario, String fechaIngreso, double comision) {
        this.idArea = idArea;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.comision = comision;
    }

    

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", idArea=" + idArea + ", nombres=" + nombres + ", apellidos=" + apellidos + ", dni=" + dni + ", direccion=" + direccion + ", telefono=" + telefono + ", cargo=" + cargo + ", salario=" + salario + ", fechaIngreso=" + fechaIngreso + ", comision=" + comision + '}';
    }
    
    
}
