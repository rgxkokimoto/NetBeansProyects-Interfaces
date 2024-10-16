/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package practica6.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ignac
 */
public class VentanaPrincipalController implements Initializable {

    @FXML
    private Button btnPerfil;
    @FXML
    private Button btnTastks;
    @FXML
    private Button btnArch;
    @FXML
    private Button btnSalir;
    @FXML
    private AnchorPane mainContentArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AnchorPane perfilView = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            mainContentArea.getChildren().setAll(perfilView);
        } catch (IOException ex) {
            System.out.println("No funciona la carga automatica de la ventana Profile");
            Logger.getLogger(VentanaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleTareaAction(ActionEvent event) {
        try {
            AnchorPane tasksView = FXMLLoader.load(getClass().getResource("Tasks.fxml"));
            mainContentArea.getChildren().setAll(tasksView);
            Stage stage = (Stage) mainContentArea.getScene().getWindow();
            stage.getIcons().clear();
            Image icon = new Image(getClass().getResourceAsStream("../img/icnTask.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Ventana Tasks");
        } catch (IOException e) {
            System.out.println("No funciona la carga automatica de la ventana Tasks");
        }
    }

    @FXML
    private void handleArchivosAction(ActionEvent event) {
        try {
            AnchorPane filesView = FXMLLoader.load(getClass().getResource("Files.fxml"));
            mainContentArea.getChildren().setAll(filesView);
            Stage stage = (Stage) mainContentArea.getScene().getWindow();
            stage.getIcons().clear();
            Image icon = new Image(getClass().getResourceAsStream("../img/icnCarp.jpeg"));
            stage.getIcons().add(icon);
            stage.setTitle("Ventana Files");
        } catch (IOException e) {
            System.out.println("No funciona la carga automatica de la ventana Files");
        }
    }

    @FXML
    private void handleSalirAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handlePerfilAction(ActionEvent event) {
        try {
            AnchorPane perfilView = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            mainContentArea.getChildren().setAll(perfilView);
            Stage stage = (Stage) mainContentArea.getScene().getWindow();
            stage.getIcons().clear();
            Image icon = new Image(getClass().getResourceAsStream("../img/iconoPer.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Ventana Profile");
        } catch (IOException e) {
            System.out.println("No funciona la carga automatica de la ventana Profile");
        }
    }

}
