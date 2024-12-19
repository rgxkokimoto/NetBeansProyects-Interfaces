/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package a6_practicaexamenuf1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import practica6.main.UsuarioLogueado;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */
public class CONTROL_Login implements Initializable {

    @FXML
    private TextField jTfUsuario;
    @FXML
    private PasswordField jPf;
    private static final String R_USUARIOS_FILE = "src\\a6_practicaexamenuf1\\Ficheros\\usuarios.txt";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleActionEntrar(ActionEvent event) {
   
        boolean find = findUser();
        
        if (!find) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error de inicio de sesión");
             alert.setHeaderText("Credenciales incorrectas");
             alert.setContentText("Por favor, verifica tu usuario y contraseña.");
             alert.showAndWait();
             //jTfUsuario.setStyle("-fx-text-fill: red;");
             //jPf.setStyle("-fx-text-fill: red;");
        } else {
            // Entrar a la aplicación y crear nueva instancia si no existe y le asina nombre al usario
            UsuarioLogueado.getInstance().setNombre(jTfUsuario.getText()); // crea una nueva instancia de usario
            try {
                cambiarVista(event,"VISTA_Principal.fxml"); // cambia al ventana params $1: propio evento , $2: nombre del fxml
            } catch (IOException ex) {
                System.out.println("a6_practicaexamenuf1.CONTROL_Login.handleActionEntrar()");
                Logger.getLogger(CONTROL_Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }

    public void cambiarVista(ActionEvent event, String nameFrame) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nameFrame));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleActionRegistro(ActionEvent event) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(R_USUARIOS_FILE, true))) {
             // TODO el problema de la duplicidad se arreglaria con una tupla de 2 booleans retornada uno para el nombre y otro para el array
            if (jTfUsuario.getText().isEmpty() && jPf.getText().isEmpty()) {
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ERROR DE REGISTRO");
                    alert.setHeaderText("Campos vacios");
                    alert.setContentText("Los campos nombre de usario y contraseña no pueden estar vacios");
                    alert.showAndWait();
            } else {
                boolean find = findUser();
                if (find) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ERROR DE REGISTRO");
                    alert.setHeaderText("Ya exite esta instancia");
                    alert.setContentText("El usuario ya existe pruebe otro nombre y contraseña");
                    alert.showAndWait();
                } else {
                    // registramos al usario en el txt
                    String datos = jTfUsuario.getText() + "," + jPf.getText();
                    System.out.println(datos);
                    bw.newLine();
                    bw.write(datos);
                    System.out.println("Usuario registrado");
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(CONTROL_Login.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private boolean findUser() {

        try (BufferedReader br = new BufferedReader(new FileReader(R_USUARIOS_FILE))) { // ahora lo entiendo este archivo guarda instancias de la clase usario
            String user = jTfUsuario.getText().trim();
            String pass = jPf.getText().trim();
            String line;
            String[] arrLineCut;
            boolean find = false;

            while ((line = br.readLine()) != null && !find) {
                arrLineCut = line.split(",");
                if (arrLineCut[0].trim().equals(user) && arrLineCut[1].trim().equals(pass)) {
                    find = true;
                }
            }

            return find;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CONTROL_Login.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Archivo no encontrado");
        } catch (IOException ex) {
            Logger.getLogger(CONTROL_Login.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hay un  problema al leer el archivo");
        }

        return false;
    }
    
    
    
}
