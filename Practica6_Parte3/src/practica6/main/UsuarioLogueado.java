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
    // esto es muy importante es la forma de crear subtipos de una misma clasa una instancia en este 
    // contexto es un nuevo usario con su propia información. 
    public static UsuarioLogueado getInstance(){
        if(instance == null){
            instance = new UsuarioLogueado();
        }
        return instance;
    }
    
     public String getNombre(){
        return nombre;
    }
     
     // aqui si que es util jajajaj
     public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
}
