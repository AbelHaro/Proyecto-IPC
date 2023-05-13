/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class RegistrarseV2Controller implements Initializable {

    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    Image[] avatares = new Image[3];
    int pos = 0;
    int nombre = 1,apellido = 1,telefono = 1, nick = 1, password = 1;
    @FXML
    private TextField campoNombre;
    @FXML
    private Text errorNombre;
    @FXML
    private TextField campoApellido;
    @FXML
    private Text errorApellido;
    @FXML
    private TextField campoTelefono;
    @FXML
    private Text errorTelefono;
    @FXML
    private TextField campoNick;
    @FXML
    private Text errorNick;
    @FXML
    private PasswordField campoPassword;
    @FXML
    private Text errorPassword;
    @FXML
    private TextField campoTarjeta1;
    @FXML
    private TextField campoTarjeta2;
    @FXML
    private TextField campoTarjeta3;
    @FXML
    private TextField campoTarjeta4;
    @FXML
    private Text errorTarjeta;
    @FXML
    private TextField campoSVC;
    @FXML
    private Text errorSVC;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarImagenes();
        
        campoNombre.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!isFullName(campoNombre.getText()) && nombre > 0){
                errorNombre.setVisible(true);
                nombre--;
            } else {
                errorNombre.setVisible(false);
            }
        });
        
        campoApellido.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!isFullName(campoApellido.getText()) && apellido > 0){
                errorApellido.setVisible(true);
                apellido--;
            } else {
                errorApellido.setVisible(false);
            }
        });
        
        campoTelefono.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!isValidTelefono(campoTelefono.getText()) && telefono > 0){
                errorTelefono.setVisible(true);
                telefono--;
            } else {
                errorTelefono.setVisible(false);
            }
        });
        
        
    
        
        
        
        campoTarjeta1.textProperty().addListener((obs, oldValue, newValue) -> {
            if(campoTarjeta1.getText().length() > 4){campoTarjeta1.setText(campoTarjeta1.getText().substring(1));}
        });
        campoTarjeta2.textProperty().addListener((obs, oldValue, newValue) -> {
            if(campoTarjeta2.getText().length() > 4){campoTarjeta2.setText(campoTarjeta2.getText().substring(1));}
        });
        campoTarjeta3.textProperty().addListener((obs, oldValue, newValue) -> {
            if(campoTarjeta3.getText().length() > 4){campoTarjeta3.setText(campoTarjeta3.getText().substring(1));}
        });
        campoTarjeta4.textProperty().addListener((obs, oldValue, newValue) -> {
            if(campoTarjeta4.getText().length() > 4){campoTarjeta4.setText(campoTarjeta4.getText().substring(1));}
        });
        campoSVC.textProperty().addListener((obs, oldValue, newValue) -> {
            if(campoSVC.getText().length() > 3){campoSVC.setText(campoSVC.getText().substring(1));}
        });
        campoTelefono.textProperty().addListener((obs, oldValue, newValue) -> {
            if(campoTelefono.getText().length() > 9){campoTelefono.setText(campoTelefono.getText().substring(1));}
        });
        
    
    }    

    
    
    public void inicializarImagenes(){
        avatares[0] = new Image(getClass().getResourceAsStream("/images/perfil/Default.png"));
        avatares[1] = new Image(getClass().getResourceAsStream("/images/perfil/CarlosAlcaraz.jpg"));
        avatares[2] = new Image(getClass().getResourceAsStream("/images/perfil/RafaNadal.jpg"));
        
        
        
        image.imageProperty().setValue(avatares[pos]);
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void registrarse(ActionEvent event) {
    }


    @FXML
    private void txtCelularKeyTyped(KeyEvent event) {
        if(campoTarjeta1.getText().length() >= 4){event.consume();}
    }

    @FXML
    private void izq(ActionEvent event) {image.imageProperty().setValue(avatares[Math.abs((--pos) % avatares.length)]);}

    @FXML
    private void der(ActionEvent event) {image.imageProperty().setValue(avatares[Math.abs((++pos) % avatares.length)]);}
    
    
    public static boolean isFullName(String str) {
    String expression = "^[a-zA-Z\\s]+"; 
    return str.matches(expression);        
    }
    
    public static boolean isValidTelefono(String str) {
    String expression = "^[0-9]+"; 
    return str.matches(expression);        
}
}
