/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam.mycompany.pojos;

/**
 *
 * @author Usuario
 */
public class Cliente {
    private String nombre;
    private String apellido;
    private String direccion;
    private String ciudad;
    private String provincia;
    private String fechaAlta;
    
    // Constructors

    public Cliente(String nombre, String apellido, String direccion, String ciudad, String provincia, String fechaAlta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.fechaAlta = fechaAlta;
    }
    
    // geters an seters 

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
    
    // To string?
    
    

            
            
}
