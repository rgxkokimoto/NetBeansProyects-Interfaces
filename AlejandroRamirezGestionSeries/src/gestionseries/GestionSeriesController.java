/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gestionseries;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 *
 * @author Alejandro
 */
public class GestionSeriesController implements Initializable {
    
    @FXML
    private Button btnAniadirSerie;
    @FXML
    private Button btnModificarSerie;
    @FXML
    private Button btnModificarSerie1;
    
    @FXML
    private ListView<String> seriesListView;
    private ObservableList<String> observableTaskList;
    private final String RUTA_SERIES = "src\\gestionseries\\files\\series.txt";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // al iniciar la aplicacion ya deben verse la lista 
       observableTaskList = FXCollections.observableArrayList(); // carga de las series en el archivo de texto
       cargarSeries();
       
       seriesListView.setItems(observableTaskList); // enlaze de el ListView con el ObservableList
    }    

    @FXML
    private void handleButtonAddSerie(ActionEvent event) { // te lanza a añadir serie en blanco
        
        // Restablecer id en la singleton a nula por si se 
        SerieSelecionada.getIntance().setIdSerie(null);
        
        // Cargar Detalle serie.fxml        
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("DetalleSerie.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Añadir serie");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionSeriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleActionMod(ActionEvent event) {
        
        String selctSerie = seriesListView.getSelectionModel().getSelectedItem();
        
        // Extraer el ide de la serie selecionada 
        if (selctSerie != null) {
            try {
                // moverse con ides cargados
                String line[] = selctSerie.split("-");
                String id = line[0];
                
                // AÑADIMOS EL ID A LA CLASE SINGLETON
                SerieSelecionada.getIntance().setIdSerie(line[0]);
                
                // cargar segunda vista
                Parent root = FXMLLoader.load(getClass().getResource("DetalleSerie.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(GestionSeriesController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }

    @FXML
    private void handleActionExit(ActionEvent event) {
        System.exit(0);
    }

    private void cargarSeries() {
        try(BufferedReader br = new BufferedReader(new FileReader(RUTA_SERIES))){
            
            String line; 
            String[] serieCut;
            while ((line = br.readLine()) != null) {
                serieCut = line.split(",");
                    observableTaskList.add(serieCut[0].trim() + "-" + serieCut[1].trim() + "-" + serieCut[2].trim());
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionSeriesController.class.getName()).log(Level.SEVERE, null, ex);
            mostrarAlert("TITULO", "No se pudo cargar el archivo", Alert.AlertType.ERROR);
            System.out.println("No se pudo encontrar el archivo o ruta: " + RUTA_SERIES);
    
        } catch (IOException ex) {
            Logger.getLogger(GestionSeriesController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Hubo un problema al abrir el archivo de: " + RUTA_SERIES );
        }
    }

    private void mostrarAlert(String titulo, String mensaje, Alert.AlertType alertType) {
        //TODO tienes un obs para verlo
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
    }
    
}
