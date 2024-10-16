/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mi1aplicacionfx.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Usuario
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
        // TODO
        try {
            AnchorPane perfilView = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            mainContentArea.getChildren().setAll(perfilView);
        } catch (IOException e) {
            System.out.println("no funciona la carga automatica de la ventana perfil");
        }
    }

    @FXML
    private void handlePerfilAction(ActionEvent event)
            throws IOException {
        AnchorPane perfilView = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        mainContentArea.getChildren().setAll(perfilView);
    }

    @FXML
    private void handleTareaAction(ActionEvent event) throws IOException {
        AnchorPane taskView = FXMLLoader.load(getClass().getResource("Tasks.fxml"));
        mainContentArea.getChildren().setAll(taskView);

    }

    @FXML
    private void handleArchivosAction(ActionEvent event) throws IOException {
        AnchorPane profileView = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        mainContentArea.getChildren().setAll(profileView);
    }

    @FXML
    private void handleSalirAction(ActionEvent event) {
        System.exit(0);
    }
}


