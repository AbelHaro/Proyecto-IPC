/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Court;
import model.Member;

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
    Member m;
    @FXML
    private Label datosSocio;
    @FXML
    private ImageView fotoPerfil;
    @FXML
    private Label idUsuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
           
            m = Context.getInstance().getMember();
            fotoPerfil.setImage(m.getImage());
            idUsuario.setText(m.getNickName());

        }catch(Exception e){
            System.out.println("Error al member");
        }
        
        bReservar[0] = b1;bReservar[1] = b2;bReservar[2] = b3;bReservar[3] = b4;bReservar[4] = b5;bReservar[5] = b6;
        tInfo[0] = t1;tInfo[1] = t2;tInfo[2] = t3;tInfo[3] = t4;tInfo[4] = t5;tInfo[5] = t6;
        datePicker.setValue(LocalDate.now());
        //fotoPerfil.setValue(m.getImage());
        inicializarGeneral();
        menu.textProperty().addListener((obs, oldValue, newValue) -> {inicializarGeneral();});
        datePicker.valueProperty().addListener((obs, oldValue, newValue) -> {inicializarGeneral();});
        
        datosSocio.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue){datosSocio.setUnderline(true);}
            else {datosSocio.setUnderline(false);}
        });

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
            tInfo[i].setText(aux + "PISTA " +( i + 1 )+ "        NO RESERVADA");
            bReservar[i].setVisible(true);
            }
        
        }
    
    
    
    
    private void cancelar(ActionEvent event) {
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("Principal.fxml"));
            Parent root;
            root = miCargador.load();
            JavaFXMLApplication.setRoot(root);
        } catch (IOException ex) {
            System.out.println("Error al cargar la escena");
        }
    }

    @FXML
    private void cambiarHorarioDesplegable(ActionEvent event) {
        MenuItem m = (MenuItem) event.getSource();
        this.menu.setText(m.getText()); 
    }

    @FXML
    private void reservar(ActionEvent event) {
        
        try {
            Button b = (Button) event.getSource();
            String pista = "Pista " + b.getId().substring(1);
            
            System.out.println("Pista elegida" + pista);
            
            LocalDate dia = datePicker.getValue();
            List<Booking> reservasPista = club.getCourtBookings(pista, dia);
            
            String horaInicio = menu.getText();
            if(horaInicio.length() == 12){ // String con hora 9
                horaInicio = horaInicio.substring(0,1);
            } else {
                horaInicio = horaInicio.substring(0,2);
            }
            int horaInicioReserva = Integer.parseInt(horaInicio);
            
            
            System.out.println("Hora inicio reserva" + horaInicioReserva);
            
            int[] horas = new int[22];
            horas[horaInicioReserva]= 1;
            
            for(int i = 0; i < reservasPista.size(); i++){
                if(reservasPista.get(i).getMember().equals(m)){
                    horas[reservasPista.get(i).getFromTime().getHour()]= 1;
                }
            }
            
            
            int cont = 0;
            for(int i = 0; i < horas.length; i++){
                if(horas[i]== 0){cont = 0;}
                else {cont++;}
                System.out.println("Valor de horas " + horas[i] + " iteración " + i + " valor de cont " + cont);
                if(cont >= 3){errorReservar();return;}
                
            }
            
            
            LocalDateTime bookingDate = LocalDateTime.now();
            LocalTime fromTime = LocalTime.of(horaInicioReserva,0);
            Court pistaC = new Court(pista);
            boolean paid = true;
            if(m.getCreditCard().equals("")){paid = false;}
            Booking reserva = club.registerBooking(bookingDate, dia,fromTime,paid,pistaC,m );
            System.out.println(reserva.getBookingDate().toString() + "AQUI");
            inicializarGeneral();
            completadaReserva(reserva);
        } catch (ClubDAOException ex) {
            System.out.println("error en la reserva");
        }
        
        
        
    }
    
    
    public void setMember(Member m){this.m = m;}
    
    
    public void errorReservar(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Reserva de pista");
        alert.setHeaderText("Reserva no completada");
        alert.setContentText("La reserva no se pudo confirmar ya que el usuario " + m.getNickName() + 
                " ya posee dos reservas seguidas en la misma pista");
        
        alert.showAndWait();
    }
    
    
    public void completadaReserva(Booking reserva){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmación de reserva");
        alert.setHeaderText("Reserva creada");
        String res = "La reserva se ha creado correctamente.\n"
                + "Dia " + reserva.getMadeForDay().toString() + " Hora " + reserva.getFromTime().toString() +
                " en la pista " + reserva.getCourt().getName()+ "\n";
        if(m.checkHasCreditInfo()){res += "Reserva pagada de forma online";}
        else {res += "La reserva deberá ser abonada antes de jugar";}
        alert.setContentText(res);
        alert.showAndWait();
    }

    @FXML
    private void irAEditarPerfil(MouseEvent event) {
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("EditarPerfil.fxml"));
            Parent root = miCargador.load();
            // Pasar parámetros entres escenas--------------------------------------------
            EditarPerfilController controladorEditarPerfil = miCargador.getController();
            controladorEditarPerfil.setMember(m);
            //----------------------------------------------------------------------------
            JavaFXMLApplication.setRoot(root);
        } catch (IOException ex) {
            System.out.println("Escena no Encontrada");
        }
    }

    @FXML
    private void irAMisReservas(MouseEvent event) {
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("VerMisReservas.fxml"));
            Parent root = miCargador.load();
            // Pasar parámetros entres escenas--------------------------------------------
            VerMisReservasController controladorVerMisReservas = miCargador.getController();
            controladorVerMisReservas.setMember(m);
            //----------------------------------------------------------------------------
            JavaFXMLApplication.setRoot(root);
        } catch (IOException ex) {
            System.out.println("Escena no Encontrada");
        }
    }

    @FXML
    private void desconectarse(MouseEvent event) {
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("IniciarSesionNeutro.fxml"));
            Parent root = miCargador.load();
            // Pasar parámetros entres escenas--------------------------------------------
            
            //----------------------------------------------------------------------------
            JavaFXMLApplication.setRoot(root);
        } catch (IOException ex) {
            System.out.println("Escena no Encontrada");
        }
    }

    
    
    
    
    
    
    
    
}
