/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import model.Booking;
import model.Club;
import model.ClubDAOException;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class VerPistasDisponiblesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Text t1;
    @FXML
    private Text t2;
    @FXML
    private Text t3;
    @FXML
    private Text t4;
    @FXML
    private Text t5;
    @FXML
    private Text t6;
    
    Club club;
    List<Booking> reservas;
    @FXML
    private MenuButton menu;
    Text[] tInfo = new Text[6];
   
    
    //public ArrayList<Booking> getForDayBookings(LocalDate forDay)
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tInfo[0] = t1;tInfo[1] = t2;tInfo[2] = t3;tInfo[3] = t4;tInfo[4] = t5;tInfo[5] = t6;
        inicializarGeneral();
        menu.textProperty().addListener((obs, oldValue, newValue) -> {inicializarGeneral();});
    }    
    
    public void inicializarGeneral(){
        try {
            club = Club.getInstance(); 
           
            reservas = club.getForDayBookings(LocalDate.now());
            
            inicializarPistas();

        } catch (IOException | ClubDAOException ex) {
            System.out.println("Error en instanciar el club");
        } 
        
    }
    
    public void inicializarPistas(){
        String horaInicio = menu.getText();
        if(horaInicio.length() == 12){ // String con hora 9
            horaInicio = horaInicio.substring(0,1);
        } else {
            horaInicio = horaInicio.substring(0,2);
        }
        int horaInicioReserva = Integer.parseInt(horaInicio);
        
        inicializarVacio(horaInicioReserva);
        
        for(int i = 0; i < reservas.size(); i++){
            Booking reserva = reservas.get(i);
            if(reserva != null && horaInicioReserva == reserva.getFromTime().getHour()){
                rellenarTextos(reserva);
            }
            
        }
        
    }
    
    public void rellenarTextos(Booking reserva){
        int horaInicio = reserva.getFromTime().getHour();
        String aux;
        if(horaInicio == 9){
            aux = ("  " + horaInicio + ":00 - " + (horaInicio + 1) +":00           ");
        } else {
            aux = (horaInicio + ":00 - " + (horaInicio + 1) +":00           ");
        }
        String pista = reserva.getCourt().getName();
        int numeroPista = Integer.parseInt(pista.substring(pista.length()-1));
        tInfo[numeroPista-1].setText(aux + pista.toUpperCase() + "        RESERVADO POR " + reserva.getMember().getNickName());
    }
    
    public void inicializarVacio(int horaInicio){
        
        String aux;
        if(horaInicio == 9){
            aux = ("  " + horaInicio + ":00 - " + (horaInicio + 1) +":00           ");
        } else {
            aux = (horaInicio + ":00 - " + (horaInicio + 1) +":00           ");
        }
        
        for(int i = 0; i < tInfo.length; i++){
            tInfo[i].setText(aux + "PISTA " + (i+1) + "        NO RESERVADA");
            }
        }
    

    @FXML
    private void cambiarHorarioDesplegable(ActionEvent event) {
        MenuItem m = (MenuItem) event.getSource();
        this.menu.setText(m.getText()); 
        
    }

    @FXML
    private void cancelar(ActionEvent event) {
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("Principal.fxml"));
            Parent root;
            root = miCargador.load();
            JavaFXMLApplication.setRoot(root);
        } catch (IOException ex) {
            System.out.println("Escena no Encontrada");
        }
        
    }
    
    
    
    
    
    
}
