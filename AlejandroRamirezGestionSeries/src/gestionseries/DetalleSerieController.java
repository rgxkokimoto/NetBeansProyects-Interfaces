/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gestionseries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */
public class DetalleSerieController implements Initializable {

    @FXML
    private TextField jTfTitulo;
    @FXML
    private TextField jTfPlataforma;
    @FXML
    private TextField jTfDirector;
    @FXML
    private TextField jTfDuracion;
    @FXML
    private TextField jTfSinopsis;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnGuardar1;

    private final String RUTA_SERIES = "src\\gestionseries\\files\\series.txt";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            cargarDataMod();
     
    }
    
    // MODIFICAR Y GUARDAR OBS miralofuncionaba funcionaba para add y modificar 
    @FXML
    private void handleButtonGuardar(ActionEvent event) {
        String id;
        id = SerieSelecionada.getIntance().getIdSerie().toString();
        
        String titulo = jTfTitulo.getText().trim();
        String plataforma = jTfPlataforma.getText().trim();
        String director = jTfDirector.getText().trim();
        String duracion = jTfDuracion.getText().trim();
        String sinopsis = jTfSinopsis.getText().trim();
        
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_SERIES))) {
                 
                String line;
                String [] lineCut;
                
                while((line = br.readLine()) != null) {
                    lineCut = line.split(",");
                    if(lineCut[0].trim().equals(id)) {
                        save(serieModd);
                    }
                    
                }
            
               // 
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetalleSerieController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetalleSerieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleButtonVolver(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GestionSeries.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DetalleSerieController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    public void mod(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_SERIES))) {

            String line;
            String lineCut[];

            while ((line = br.readLine()) != null) {
                lineCut = line.split(",");
                if (lineCut[0].trim().equals(id)) {
                    jTfTitulo.setText(lineCut[1]);
                    jTfPlataforma.setText(lineCut[2]);
                    jTfDirector.setText(lineCut[3]);
                    jTfDuracion.setText(lineCut[4]);
                    jTfSinopsis.setText(lineCut[5]);
                    break; // bueno luego cambiaremos esto
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(DetalleSerieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void cargarDataMod() {
        // Recuperar la instancia
        String id = SerieSelecionada.getIntance().getIdSerie();
        mod(id);

    }

    private void save(String serieModd) {
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_SERIES,true))) {
            bw.write(serieModd);
        } catch (IOException ex) {
            Logger.getLogger(DetalleSerieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}
