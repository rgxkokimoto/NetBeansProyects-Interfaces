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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ignac
 */
public class TasksController implements Initializable {

    @FXML
    private TextField txtTarea;
    @FXML
    private Button btnAniadir;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCompletar;
    @FXML
    private ListView<String> listTareas; // Componente para añadir elementos a la lista 

    private ObservableList<String> observableTaskList; // Esta clase notifica automaticamente en una lista y se acutualiza dinamicamente 
    private String usuarioLogueado;
    private final String DIRECTORIO_TAREAS = "src/practica6/Ficheros/Tareas/";
    private String archivoTareas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioLogueado = UsuarioLogueado.getInstance().getNombre(); // La instancia es el usario que esta logueado en tu sesión
        archivoTareas = DIRECTORIO_TAREAS + "tareas_" + usuarioLogueado + ".txt";
        observableTaskList = FXCollections.observableArrayList();

        cargarTareas();

        listTareas.setItems(observableTaskList); // rellena las tareas que han sido cargadas anteriormente en el obsevable TaskList
    }

    @FXML
    private void handleAniadirTareaAction(ActionEvent event) {

        String nuevaTarea = txtTarea.getText();

        if (!nuevaTarea.isEmpty()) {  // si el usario le diese a añadir crearia una tarea vacia oh daria un error 
            observableTaskList.add(nuevaTarea);
            guardarTareas(); // Tambien hay que guardar la tarea en su archivo
            txtTarea.setText("");// Limpiar 
        }

    }

    @FXML
    private void handleEliminarTareaAction(ActionEvent event) {

        String selectedTask = listTareas.getSelectionModel().getSelectedItem();

        if (selectedTask != null) {
            observableTaskList.remove(selectedTask);
            guardarTareas();
        }
    }

    @FXML
    private void handleCompletarTareaAction(ActionEvent event) {
        String selectedTask = listTareas.getSelectionModel().getSelectedItem();
        if (selectedTask != null && !selectedTask.contains("(Completada)")) {
            int index = observableTaskList.indexOf(selectedTask);
            selectedTask += " (Completada) ";
            observableTaskList.set( index, selectedTask); // cambia el valor en la lista 
            guardarTareas(); // actualizado guarda el archivo.
        }
    }

    private void cargarTareas() {

        try {

            File archivoUsario = new File(archivoTareas);

            if (archivoUsario.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(archivoTareas));

                String line;
                String[] tareas;
                while ((line = reader.readLine()) != null) {
                    tareas = line.split(",");
                    observableTaskList.add(tareas[1]);
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TasksController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TasksController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void guardarTareas() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTareas));
            for (String tarea : observableTaskList) {
                writer.write(usuarioLogueado + "," + tarea);
                writer.newLine(); // Salto de línea
            }
            writer.close(); // esto hace un flush general Buen Habito cerrar el Buffer 
        } catch (IOException ex) {
            Logger.getLogger(TasksController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
