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
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleBooleanProperty;
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
    private BooleanProperty validTelefono;
    @FXML
    private TextField TTelefono;
    @FXML
    private TextField TNickname;
    @FXML
    private PasswordField TPassword;
    @FXML
    private TextField TNumTrajetaCredito;
    @FXML
    private TextField TSVC;
    /**
     * Initializes the controller class.
     */
    int lengthTTelefono = TTelefono.getText().length();
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    validTelefono = new SimpleBooleanProperty();
    validTelefono.setValue(Boolean.FALSE); 
    //Check values when user leaves edits
    TTelefono.focusedProperty().addListener((observable, oldValue, newValue)->{
    if(!newValue){ //focus lost.
        checkEditTelefono();
    }
        });
            }    
    private void checkEditTelefono(){
    if(lengthTTelefono>10 || lengthTTelefono<10){errorTelNoValido.setVisible(true);} 
    }



    
    @FXML
    private void handlebCancelarRegistroOnAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("Principal.fxml"));
            Parent root;
            root = miCargador.load();
            JavaFXMLApplication.setRoot(root);
        } catch (IOException ex) {
            System.out.println("Escena no Encontrada");
        }
    }

    @FXML
    private void handlebRegistrarseOnAction(ActionEvent event) {
    }

}

    

    