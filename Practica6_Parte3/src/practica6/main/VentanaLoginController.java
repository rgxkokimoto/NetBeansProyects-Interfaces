/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package practica6.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AAA
 * 
 * aun no se como funciona el TODO pero hasta entoces puedo usar 
 * CONTROL + F para buscar coincidencias 
 * 
 * he creado un mini sistema de tags para rever cosas 
 * TODO : para cosas que te dejaste sin hacer 
 * REV : cosas que quieres revisar porque no entiendes oh quieres probar algo nuevo 
 * 
 * 
 */
public class VentanaLoginController implements Initializable {

    @FXML
    private ImageView jImgLogin;  // TODO usa control + f para buscar coincidencias 
    @FXML
    private TextField jTxtUsuario;
    @FXML
    private PasswordField jPsswdCont;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegistro;
    
    static final String R_FILE_USERS = "src/practica6/Ficheros/usuarios.txt";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { }

    @FXML
    private void handleLoginAction(ActionEvent event) {
        File archivo = new File(R_FILE_USERS);
        try {
            if (archivo.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(archivo));
                String linea = "";
                String[] datosA;
                boolean encontrado = false;
                try {
                    // REV  si el while no encuentra nada va a tira la excepcion pero como funciona esto?
                    while (!encontrado) {
                        linea = reader.readLine();  // van a ser los datos del archivo que va leyendo
                        datosA = linea.split(","); // asi podremos usar los datos que nos dan
                        if (datosA[0].equals(jTxtUsuario.getText()) && datosA[1].equals(jPsswdCont.getText())) {
                             // si se encuentran la contraseña y el usario se permite entrar
                                UsuarioLogueado.getInstance().setNombre(jTxtUsuario.getText()); // crea una nueva instancia de usario
                                Parent root = FXMLLoader.load(getClass().getResource("VentanaPrincipal.fxml"));
                                Scene scene = new Scene(root);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                                encontrado = true;
                        }
                        
                        // REV : oye no seria mejor solo on un if  este es el antiguo
                       /* if (datosA[0].equals(jTxtUsuario.getText())) { // el texto del usario es igual al dato del archivo de texto?
                            if (datosA[1].equals(jPsswdCont.getText())) { // el texto de la contraseña es igual al del archivo de texto?
                                // si se encuentran la contraseña y el usario se permite entrar
                                UsuarioLogueado.getInstance().setNombre(jTxtUsuario.getText()); // la intancia de usario es el usario individual su cuenta
                                Parent root = FXMLLoader.load(getClass().getResource("VentanaPrincipal.fxml"));
                                Scene scene = new Scene(root);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                                encontrado = true;
                            }
                        }*/
                    }
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR INICIO SESION");
                    alert.setHeaderText("Usuario/Contraseña incorrectas");
                    alert.showAndWait(); 
                    // TODO : quiero enontrar un metodo para controlar el color 
                    jTxtUsuario.setText("Mal");
                }
            } else {
                archivo.mkdir();
                archivo.createNewFile();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("El archivo usuario.txt no ha sido encontrado es necesario para guardar los usuarios");
        } catch (IOException ex) {
            Logger.getLogger(VentanaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ocurrio un problema con el usuario ");
        }
    }

    @FXML
    private void handleRegisterAction(ActionEvent event) {
            BufferedWriter writer = null;
            try {  
                    writer = new BufferedWriter(new FileWriter("src/practica6/Ficheros/usuarios.txt", true)); // Recuerda true añadir false: sobrescribir 
                    if (!jTxtUsuario.getText().isEmpty() && !jPsswdCont.getText().isEmpty()) { // MIentras no este vacio 
                            String datos = jTxtUsuario.getText() + "," + jPsswdCont.getText();
                            System.out.println(datos);
                            writer.write(datos);
                            writer.newLine();
                            System.out.println("Usuario registrado");
                    } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR REGISTRO");
                            alert.setHeaderText("Datos incompletos");
                            alert.setContentText("El usuario y la contraseña no pueden estar vacios oh puede que el usario ya exista");
                            alert.showAndWait();
                    }
            } catch (IOException ex) {
                    Logger.getLogger(VentanaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
    }

}
