/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package practica6.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Alejandro Ramírez Gómez 
 */
public class main extends Application {
    
    
    /*
        Stage es el contenedor superior para todas las escenas de la interfaz
        gráfica de usuario (GUI). A través de él, puedes definir el tamaño de la ventana, 
        su título, y agregar elementos visuales.
    */
    
    /*
        Posible solucion al problema del empaquetado
        netBeans tiene una extructura mas sensible a la hora de poner las rutas si quieres 
        referneciar a otras clases tienes que empezar desde el src "/" una ruta relativa basicamente 
        la extructura de tu proyecto es /practica6/main/VentanaLogin.fxml
    
        1º voy a tratar de crear paqutes teniendo esto en cuenta esto 
    */
    //
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("VentanaLogin.fxml"));
        //asignar a root la CLASE y el recurso de la vista que quieres que se cargue 
        
        Scene scene = new Scene(root); // root se asignara a una instancia de la clase sence 
        
        stage.setScene(scene); // y stage usa un metodo para incluir para poder utilizarla; un lio de 3 pares 
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args); 
    }
    
}
