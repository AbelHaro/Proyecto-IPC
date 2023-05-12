/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
iimport javafx.event.ActionEvent;
import javafx.fxml.FXML;
mport javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 * FXML Controller class
 *
 * @author Nabil
 */
public class RegistrarseController implements Initializable {


    @FXML
    private Label errorTelNoValido;
    @FXML
    private Label errorNickNoValido;
    @FXML
    private Label errorPasswordNoValido;
    @FXML
    private Label errorNumdeTarjetaNoValido;
    @FXML
    private Label errorSVCNoValido;
    @FXML
    private Button bCancelarRegistro;
    @FXML
    private Button bRegistrarse;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handlebCancelarRegistroOnAction(ActionEvent event) {
    }

    @FXML
    private void handlebRegistrarseOnAction(ActionEvent event) {
    }

}
