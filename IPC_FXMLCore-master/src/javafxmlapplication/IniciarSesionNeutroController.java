/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class IniciarSesionNeutroController implements Initializable {

    @FXML
    private Button bAcceder;
    @FXML
    private Button bRegistrarse;
    @FXML
    private Button bVerDisponibilidad;
    @FXML
    private Label errorAcceder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handlebAccederOnAction(ActionEvent event) {
    }

    @FXML
    private void handlebRegistrarseOnAction(ActionEvent event) {
    }

    @FXML
    private void handlebVerDisponibilidadOnAction(ActionEvent event) {
    }
    
}
