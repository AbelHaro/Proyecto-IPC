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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
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
    
   
    
    //public ArrayList<Booking> getForDayBookings(LocalDate forDay)
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            // TODO
            club = Club.getInstance();
           
            LocalDate dia = LocalDate.now();
            System.out.println("Dia del mes: " + dia.getDayOfMonth());
           
            reservas = club.getForDayBookings(dia);
            
            
            inicializarPistas();
            
            
            

        } catch (IOException | ClubDAOException ex) {
            System.out.println("Error en instanciar el club");
        } 
        
        
    }    
    
    
    public void inicializarPistas(){
        String horaInicio = menu.getText();
        System.out.println(horaInicio.length());
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
                System.out.println("hora de la reserva desde el objeto: " + reserva.getFromTime().getHour());
                System.out.println("pista de la reserva desde el objeto: " + reserva.getCourt().getName());
            }
            
        }
        
    }
    
    public void rellenarTextos(Booking reserva){
        int horaInicio = reserva.getFromTime().getHour();
        String aux = (horaInicio + ":00 - " + (horaInicio + 1) +":00           ");
        String pista = reserva.getCourt().getName();
        if(pista.equals("Pista 1")){
            t1.setText(aux + "PISTA 1        RESERVADO POR " + reserva.getMember().getNickName());
        }
        if(pista.equals("Pista 2")){
            t2.setText(aux + "PISTA 2        RESERVADO POR " + reserva.getMember().getNickName());
        }
        if(pista.equals("Pista 3")){
            t3.setText(aux + "PISTA 3        RESERVADO POR " + reserva.getMember().getNickName());
        }
        if(pista.equals("Pista 4")){
            t4.setText(aux + "PISTA 4        RESERVADO POR " + reserva.getMember().getNickName());
        }
        if(pista.equals("Pista 5")){
            t5.setText(aux + "PISTA 5        RESERVADO POR " + reserva.getMember().getNickName());
        }
        if(pista.equals("Pista 6")){
            t5.setText(aux + "PISTA 5        RESERVADO POR " + reserva.getMember().getNickName());
        }
        
    }
    
    public void inicializarVacio(int horaInicio){
        t1.setText(horaInicio + ":00 - " + (horaInicio + 1) + ":00           PISTA 1        NO RESERVADA");
        t2.setText(horaInicio + ":00 - " + (horaInicio + 1) + ":00           PISTA 2        NO RESERVADA");
        t3.setText(horaInicio + ":00 - " + (horaInicio + 1) + ":00           PISTA 3        NO RESERVADA");
        t4.setText(horaInicio + ":00 - " + (horaInicio + 1) + ":00           PISTA 4        NO RESERVADA");
        t5.setText(horaInicio + ":00 - " + (horaInicio + 1) + ":00           PISTA 5        NO RESERVADA");
        t6.setText(horaInicio + ":00 - " + (horaInicio + 1) + ":00           PISTA 6        NO RESERVADA");
    }
    
    
    
    
    
    
}
