/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package practica6.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author ignac
 */
public class FilesController implements Initializable {

    @FXML
    private ListView<String> listaArchivos;
    
    private ObservableList<String> fileList;
    private final String DIR_CARPETA = "src/practica6/ficheros/archivos_subidos/";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileList = FXCollections.observableArrayList();
        listaArchivos.setItems(fileList);
        cargarArchivos();
    }    

    @FXML
    private void handleActionBtnSubirArchivo(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File archivo = fc.showOpenDialog(null);
        
        if(archivo != null){
            File destino = new File(DIR_CARPETA, archivo.getName());
            if(!destino.exists()){
                try {
                    Files.copy(archivo.toPath(), destino.toPath());
                    fileList.add(archivo.getName());
                } catch (IOException ex) {
                    mostrarAlerta("Error", "No se pudo subir el archivo", Alert.AlertType.ERROR);
                    Logger.getLogger(FilesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    @FXML
    private void handleActionBtnDescargar(ActionEvent event) {
        String archivo = listaArchivos.getSelectionModel().getSelectedItem();
        
        if(archivo != null){
            FileChooser fc = new FileChooser();
            fc.setInitialFileName(archivo);
            File destino = fc.showSaveDialog(null);
            
            if(destino != null){
                File origen = new File(DIR_CARPETA, archivo);
                
                try {
                    Files.copy(origen.toPath(), destino.toPath());
                } catch (IOException ex) {
                    mostrarAlerta("Error", "No se pudo descargar el archivo", Alert.AlertType.ERROR);
                    Logger.getLogger(FilesController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
        }
        
    }

    @FXML
    private void handleActionBtnEliminar(ActionEvent event) {
        String archivo = listaArchivos.getSelectionModel().getSelectedItem();
        
        if(archivo != null){
            File archivoEliminado = new File(DIR_CARPETA, archivo);
            
            if(archivoEliminado.delete()){
                fileList.remove(archivo);
            } else {
                 mostrarAlerta("Error", "No se pudo eliminar el archivo", Alert.AlertType.ERROR);
            }
            
        }
        
    }

    private void cargarArchivos() {
        File file = new File(DIR_CARPETA);
        File [] files = file.listFiles();
        if(files != null){
            for(File archivo : files){
                fileList.add(archivo.getName());
            }
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo); 
        alert.setHeaderText(null); 
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
}
