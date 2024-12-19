/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionseries;

/**
 * @author Alejandro
 */
public class SerieSelecionada {
    
    private static SerieSelecionada instance;
    private String serieId;
    
    // Constructor privado porque?
    private  SerieSelecionada() {
    }
    
    public static SerieSelecionada getIntance(){
        if(instance == null){
            instance = new SerieSelecionada();
        }
        return instance;
    }
    
    public void  setIdSerie(String serieId){
        this.serieId = serieId;
    }
    
    public String getIdSerie(){
        return serieId;
    }
    
}
