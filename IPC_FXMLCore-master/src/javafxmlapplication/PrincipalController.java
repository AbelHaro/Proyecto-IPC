/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class PrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("IniciarSesionNeutro.fxml"));
            Parent root;
            root = miCargador.load();
            JavaFXMLApplication.setRoot(root);
        } catch (IOException ex) {
            System.out.println("Escena no Encontrada");
        }
    }

    @FXML
    private void registrarse(ActionEvent event) throws IOException {
        
        JavaFXMLApplication.setRoot("RegistrarseV2");
        /*
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("RegistrarseV2.fxml"));
            Parent root;
            root = miCargador.load();
            JavaFXMLApplication.setRoot(root);
        } catch (IOException ex) {
            System.out.println("Escena no Encontrada");
        }
*/
    }

    @FXML
    private void verPistasDisponibles(ActionEvent event) throws IOException {
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("VerPistasDisponibles.fxml"));
            Parent root;
            root = miCargador.load();
            JavaFXMLApplication.setRoot(root);
        } catch (IOException ex) {
            System.out.println("Escena no Encontrada");
        }
    }
    
}
