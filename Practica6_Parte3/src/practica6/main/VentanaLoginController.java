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
 */
public class VentanaLoginController implements Initializable {

    @FXML
    private ImageView jImgLogin;
    @FXML
    private TextField jTxtUsuario;
    @FXML
    private PasswordField jPsswdCont;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegistro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { }

    @FXML
    private void handleLoginAction(ActionEvent event) {
        File archivo = new File("src/practica6/Ficheros/usuarios.txt");
        try {
            if (archivo.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(archivo));
                String linea = "";
                String[] datos;
                boolean encontrado = false;
                try {
                    while (!encontrado) {
                        linea = reader.readLine();
                        datos = linea.split(",");
                        if (datos[0].equals(jTxtUsuario.getText())) {
                            if (datos[1].equals(jPsswdCont.getText())) {
                                UsuarioLogueado.getInstance().setNombre(jTxtUsuario.getText());
                                encontrado = true;
                                Parent root = FXMLLoader.load(getClass().getResource("VentanaPrincipal.fxml"));
                                Scene scene = new Scene(root);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            }
                        }
                    }
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR INICIO SESION");
                    alert.setHeaderText("Usuario/Contraseña incorrectas");
                    alert.showAndWait();
                }
            } else {
                archivo.mkdir();
                archivo.createNewFile();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("El archuvo usuario.txt no ha sido encontrado es necesario para guardar los usuarios");
        } catch (IOException ex) {
            Logger.getLogger(VentanaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ocurrio un problema con el usuario ");
        }
    }

    @FXML
    private void handleRegisterAction(ActionEvent event) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("src/practica6/Ficheros/usuarios.txt", true));
            if (!jTxtUsuario.getText().isEmpty() && !jPsswdCont.getText().isEmpty()) {
                String datos = jTxtUsuario.getText() + "," + jPsswdCont.getText();
                System.out.println(datos);
                writer.write(datos);
                writer.newLine();
                System.out.println("Usuario registrado");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR REGISTRO");
                alert.setHeaderText("Datos incompletos");
                alert.setContentText("El usuario y la contraseña no pueden estar vacios");
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
