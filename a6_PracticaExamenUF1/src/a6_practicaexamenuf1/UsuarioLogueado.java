/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica6.main;

/**
 * @author AAA
 */
public class UsuarioLogueado {
    
    private static UsuarioLogueado instance;
    private String nombre;
    private UsuarioLogueado(){}
    
    public static UsuarioLogueado getInstance(){
        if(instance == null){
            instance = new UsuarioLogueado();
        }
        return instance;
    }
    
     public String getNombre(){
        return nombre;
    }
     
     public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
}
