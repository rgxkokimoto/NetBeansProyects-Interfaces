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
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 * 
 * @author AAA
 */
public class ProfileController implements Initializable {
    
    @FXML
    private ImageView imgPerfil;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNombre;
    @FXML
    private Button btnGuardar;
    @FXML
    private PasswordField psswdCambiarCont;
    @FXML
    private Button btnCambiarImg;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosUser();
    }
    
    private void cargarDatosUser() {
        BufferedReader reader = null;
        try {
            String usuario = UsuarioLogueado.getInstance().getNombre();
            reader = new BufferedReader(new FileReader("src/practica6/Ficheros/datos_usuario.txt"));
            String linea;
            String[] datos;
            File carpetaDestino = new File("src/practica6/img/profilepics/");
            while ((linea = reader.readLine()) != null) {
                datos = linea.split(",");
                if (datos[0].equals(usuario)) {
                    txtNombre.setText(datos[1]);
                    txtApellidos.setText(datos[2]);
                    txtDireccion.setText(datos[3]);
                    txtEmail.setText(datos[4]);
                    File archivo = new File(carpetaDestino, datos[5]);
                    imgPerfil.setImage(new Image(archivo.toURI().toString()));
                }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    @FXML
    private void handleGuardarCambiosAction(ActionEvent event) {
        String usuario = UsuarioLogueado.getInstance().getNombre();
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String direccion = txtDireccion.getText();
        String email = txtEmail.getText();
        String img = usuario + ".png";
        
        StringBuilder cambios = new StringBuilder();
        boolean encontrado = false;
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/practica6/Ficheros/datos_usuario.txt"));
            String linea;
            String[] datos;
            while ((linea = reader.readLine()) != null) {
                datos = linea.split(",");
                if (datos[0].equals(usuario)) {
                    datos[1] = nombre;
                    datos[2] = apellidos;
                    datos[3] = direccion;
                    datos[4] = email;
                    datos[5] = img;
                    encontrado = true;
                }
                
                linea = String.join(",", datos);
                
                cambios.append(linea).append("\n");
            }
            
            if (!encontrado) {
                String usuarioNuevo = usuario + "," + nombre + "," + apellidos + "," + direccion + "," + email + "," + img;
                cambios.append(usuarioNuevo).append(System.lineSeparator());
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/practica6/Ficheros/datos_usuario.txt"));
            writer.write(cambios.toString());
            writer.flush();
            if (!psswdCambiarCont.getText().isEmpty()) {
                cambiarContrasenia(usuario);
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleCambiarImagenAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Selecciona una imagen de perfil");
        
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("imágenes","*.png"));
        
        File img = fc.showOpenDialog(null);
        
        if (img != null) {
            try {
                String usuario = UsuarioLogueado.getInstance().getNombre();
                String nuevoArchivo = usuario + ".png";
                File carpetaDestino = new File("src/practica6/img/profilepics/");
                
                File archivo = new File(carpetaDestino, nuevoArchivo);
                Files.copy(img.toPath(), archivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
                
                imgPerfil.setImage(new Image(archivo.toURI().toString()));
                
            } catch (IOException ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al cargar una imagen");
                alert.setHeaderText(null);
                alert.setContentText("No se ha podido cargar la imagen seleccionada");
                alert.showAndWait();
            }
        }
        
    }
    
    private void cambiarContrasenia(String usuario) {
        String contNueva = psswdCambiarCont.getText();
        
        StringBuilder cambios = new StringBuilder();
        String linea;
        String[] datos;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/practica6/Ficheros/usuarios.txt"));
            
            while ((linea = reader.readLine()) != null) {
                datos = linea.split(",");
                
                if (datos[0].equals(usuario)) {
                    datos[1] = contNueva;
                }
                
                linea = String.join(",", datos);
                cambios.append(linea).append("\n");
                
            }
            
            psswdCambiarCont.setText("");
            
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/practica6/Ficheros/usuarios.txt"));
            writer.write(cambios.toString());
            writer.flush(); // flush asegura que se genere el cambio 
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
