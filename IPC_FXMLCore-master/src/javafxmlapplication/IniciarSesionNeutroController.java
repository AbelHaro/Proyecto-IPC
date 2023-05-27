/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.ZoomEvent;
import javafx.stage.Stage;
import model.Club;
import model.ClubDAOException;
import model.Member;

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
    
    
    public Member m;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void handlebAccederOnAction(ActionEvent event) throws ClubDAOException, IOException {
        
        
        /*
        if (!error.visibleProperty().getValue() && !model.Club.getInstance().existsLogin(nick.getText())) {
            error.setText("El usuario no existe");
            error.setVisible(true);
            return;
        }
        */
        if(!isValidNick(nick.getText())){
                errorAcceder.setText("El usuario no es correcto");
                errorAcceder.setVisible(true);
                return;
        }   
        
        try{
             m = model.Club.getInstance().getMemberByCredentials(nick.getText(),password.getText());
        } catch(IOException | ClubDAOException e){
            System.out.println("Error en el inicio de sesión");
        } finally {
            if (m ==null) {
            errorAcceder.setText("Usuario o contraseña incorrecta");
            errorAcceder.setVisible(true);
            return;         
            }
        }
       
        
        
        
       Context i = Context.getInstance();
        i.setMember(m);
        

        try {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("Reservar.fxml"));
        Parent root = miCargador.load();
        JavaFXMLApplication.setRoot(root);

        } catch (IOException ex) {
        System.out.println("Escena no encontrada");
        }
      
        
        
        
    }

    @FXML
    private void handlebRegistrarseOnAction(ActionEvent event) {
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("RegistrarseV2.fxml"));
            Parent root = miCargador.load();
            JavaFXMLApplication.setRoot(root);
        } catch (IOException ex) {
            System.out.println("Escena no Encontrada");
        }
    }

    @FXML
    private void handlebVerDisponibilidadOnAction(ActionEvent event) {
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("VerPistasDisponibles.fxml"));
            Parent root;
            root = miCargador.load();
            JavaFXMLApplication.setRoot(root);
        } catch (IOException ex) {
            System.out.println("Escena no Encontrada");
        }
    }

    

    @FXML
    private void iniciarSesionDirecto(ActionEvent event) {
        
        try {
            Image image = new Image(getClass().getResourceAsStream("/images/perfil/CarlosAlcaraz.jpg"));
            m = Club.getInstance().registerMember("Abel", "Haro", "685018048", "Abel", "123456x", "", 0, image );
            
            /*
            m = Club.getInstance().getMemberByCredentials("user2", "123456x");
            */
        } catch (Exception ex) {
            System.out.println("Error al obtener el member");
        } 
        
        Context i = Context.getInstance();
        i.setMember(m);
        

        try {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("Reservar.fxml"));
        Parent root = miCargador.load();
        JavaFXMLApplication.setRoot(root);

        } catch (IOException ex) {
        
        }
        
        
    }
    public static boolean isValidNick(String str) {
        String expression = "^[0-9a-zA-Z]+"; 
        return str.matches(expression);        
    }
}
