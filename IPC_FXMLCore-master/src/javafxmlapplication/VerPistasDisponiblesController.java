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
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class VerPistasDisponiblesController implements Initializable {

    
    
    @FXML
    private GridPane grid;
    @FXML
    private Button a9;
    @FXML
    private Button a10;
    @FXML
    private Button a11;
    @FXML
    private Button a12;
    @FXML
    private Button a13;
    @FXML
    private Button a14;
    @FXML
    private Button a16;
    @FXML
    private Button a17;
    @FXML
    private Button a18;
    @FXML
    private Button a19;
    @FXML
    private Button a20;
    @FXML
    private Button a21;
    @FXML
    private Button a15;
    
    public static String horaInicial = "9";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       
    
    }    

    @FXML
    private void verPistas(ActionEvent event) throws IOException {
        Button presionado = (Button)event.getSource();
        horaInicial = presionado.getId();
        horaInicial = horaInicial.substring(1,horaInicial.length());
        
        //System.out.println(horaInicial);
        
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("verPistasDisponiblesSeleccionada.fxml"));
        Parent root = miCargador.load();
        JavaFXMLApplication.setRoot(root);
       
       /*
        VerPistasDisponiblesSeleccionadaController controladorHora = miCargador.getController();
        controladorHora.pasarHoraInicio(Integer.parseInt(horaInicial));
*/

        System.out.println("hora antes de cambiar de vista " + horaInicial);
        
        
        JavaFXMLApplication.setRoot("verPistasDisponiblesSeleccionada");

    }
    
    public static int getHora(){
        return Integer.parseInt(horaInicial);
    }
}
