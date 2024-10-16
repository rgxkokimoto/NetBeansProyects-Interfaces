/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package mi1aplicacionfx.control;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Usuario
 */

public class FXMLDocumentController implements Initializable {
    
    //  existe una opcion para generar los objetos y metodos de accion autamaticamente que existan si los creaste en la interfaz, de la vista que quiereas 
    // coge la vista selecionada > click derecho > Make Controler
    
    @FXML
    private Button btnBoton1;
    @FXML
    private Button btnBoton2;
    @FXML
    private Label lblMensaje;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        
    }    

    @FXML
    private void handelButtonOneAction(ActionEvent event) {
        System.out.println("Boton 1 pulsado");
        lblMensaje.setText("Boton 1 pulsado");
    }

    @FXML
    private void handelButtontwoAction(ActionEvent event) {
        System.out.println("Boton 2 pulsado");
        lblMensaje.setText("Boton 2 pulsado");
    }

    @FXML
    private void handelButtonMouseEnteredOneAction(MouseEvent event) {
        System.out.println("Has tocado el boton 1");
        lblMensaje.setText("Has tocado el boton 1");
    }
    
}
