/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
public class ReservarController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private MenuButton menu;
    @FXML
    private Text t1;
    @FXML
    private Button b1;
    @FXML
    private Text t2;
    @FXML
    private Button b2;
    @FXML
    private Text t3;
    @FXML
    private Button b3;
    @FXML
    private Text t4;
    @FXML
    private Button b4;
    @FXML
    private Text t5;
    @FXML
    private Button b5;
    @FXML
    private Text t6;
    @FXML
    private Button b6;
    
    
    
    Button[] bReservar =  new Button[6];
    Text[] tInfo = new Text[6];
    
    Club club;
    List<Booking> reservas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bReservar[0] = b1;bReservar[1] = b2;bReservar[2] = b3;bReservar[3] = b4;bReservar[4] = b5;bReservar[5] = b6;
        tInfo[0] = t1;tInfo[1] = t2;tInfo[2] = t3;tInfo[3] = t4;tInfo[4] = t5;tInfo[5] = t6;
        datePicker.setValue(LocalDate.now());
        inicializarGeneral();
        menu.textProperty().addListener((obs, oldValue, newValue) -> {inicializarGeneral();});
        datePicker.valueProperty().addListener((obs, oldValue, newValue) -> {inicializarGeneral();});
    }    

    public void inicializarGeneral(){
        try {
            club = Club.getInstance(); 
           
            
            LocalDate dia = datePicker.getValue();
            reservas = club.getForDayBookings(dia);
            
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
        bReservar[numeroPista-1].setVisible(false);
        
        
    }
    
    public void inicializarVacio(int horaInicio){
        
        String aux;
        if(horaInicio == 9){
            aux = ("  " + horaInicio + ":00 - " + (horaInicio + 1) +":00           ");
        } else {
            aux = (horaInicio + ":00 - " + (horaInicio + 1) +":00           ");
        }
        
        for(int i = 0; i < tInfo.length; i++){
            tInfo[i].setText(aux + "PISTA " + i + "        NO RESERVADA");
            bReservar[i].setVisible(true);
            }
        
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

    @FXML
    private void cambiarHorarioDesplegable(ActionEvent event) {
        MenuItem m = (MenuItem) event.getSource();
        this.menu.setText(m.getText()); 
    }

    @FXML
    private void reservar(ActionEvent event) {
    }
    
}
