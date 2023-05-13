/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarImagenes();
         
    
    }    

    @FXML
    private void prev(ActionEvent event) {
    }

    @FXML
    private void siguiente(ActionEvent event) {
    }
    
    
    public void inicializarImagenes(){
        avatares[0] = new Image(getClass().getResourceAsStream("/images/Default.png"));
        avatares[1] = new Image(getClass().getResourceAsStream("/images/CarlosAlcaraz.jpg"));
        avatares[2] = new Image(getClass().getResourceAsStream("/images/RafaNadal.jpg"));
        
        
        
        image.imageProperty().setValue(avatares[1]);
    }
}
