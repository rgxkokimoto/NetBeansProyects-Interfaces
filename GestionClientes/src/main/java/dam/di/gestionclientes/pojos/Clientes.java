/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam.di.gestionclientes.pojos;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author ignac
 */
//Atributos
public class Clientes {
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String ciudad;
    private String provincia;
    private Date fechaAlta;

    //Constructor
    public Clientes(String nombre, String apellido, String email, String direccion, String ciudad, String provincia, Date fechaAlta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.fechaAlta = fechaAlta;
    }

    //Getters y setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    // Array para la tabla
    public String[] arrayStrings() {
        String[] s = new String[7];
        s[0] = nombre;
        s[1] = apellido;
        s[2] = email;
        s[3] = direccion;
        s[4] = ciudad;
        s[5] = provincia;
        s[6] = fechaAlta.toString();
        return s;
    }
    
    
}
