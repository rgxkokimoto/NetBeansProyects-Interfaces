/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package a6_practicaexamenuf1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Alejandro
 */
public class CONTROL_VPrincipal implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private AnchorPane mainContentArea;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void handleButtonGoProfile(ActionEvent event) {
    }

    @FXML
    private void handleButtonGoFiles(ActionEvent event) {
    }

    @FXML
    private void handleButtonGoTask(ActionEvent event) {
    }

    @FXML
    private void handleButtonExit(MouseEvent event) {
    }
    
}
