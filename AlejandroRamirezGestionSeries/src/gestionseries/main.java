package gestionseries;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Alejandro
 */
public class main extends Application {
    
    @Override
    public void start(Stage stage)throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("GestionSeries.fxml"));
        
         
         stage.setTitle("Gestion Series");
         
        Scene scene = new Scene(root);  
        
        stage.setScene(scene); 
        stage.show();
        stage.setResizable(false); // evitar que la ventana sea redimensionable
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
