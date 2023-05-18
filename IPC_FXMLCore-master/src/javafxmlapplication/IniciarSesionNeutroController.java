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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.ZoomEvent;
import model.Club.*;
import model.ClubDAOException;

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
    @FXML
    private PasswordField password;
    @FXML
    private TextField nick;
    @FXML
    private Label error;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handlebAccederOnAction(ActionEvent event) throws ClubDAOException, IOException {
        
        if (!model.Club.getInstance().existsLogin(nick.getText())) {
            error.setText("El usuario no existe");
            error.setVisible(true);
        }
        
        if (model.Club.getInstance().getMemberByCredentials(nick.getText(),password.getText())==null) {
            error.setText("Usuario o contraseña incorrecta");
            error.setVisible(true);
        }
    }

    @FXML
    private void handlebRegistrarseOnAction(ActionEvent event) {
    }

    @FXML
    private void handlebVerDisponibilidadOnAction(ActionEvent event) {
    }

    
}
