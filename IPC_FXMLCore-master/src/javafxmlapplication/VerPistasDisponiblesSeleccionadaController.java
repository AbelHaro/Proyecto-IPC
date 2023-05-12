/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class VerPistasDisponiblesSeleccionadaController implements Initializable {

    @FXML
    private Text textoHorario;
    
    
    int horaInicio;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("hora tras cambiar de vista " + VerPistasDisponiblesController.horaInicial);
        int hora = VerPistasDisponiblesController.getHora();
        textoHorario.setText("hora seleccionada " + VerPistasDisponiblesController.horaInicial);
        
    }
    
    public void pasarHoraInicio(int h){this.horaInicio = h;}
}
