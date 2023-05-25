/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Member;



/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class VerMisReservasController implements Initializable {

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

    
    Member m;
    @FXML
    private ImageView fotoPerfil;
    @FXML
    private Label idUsuario;
    @FXML
    private Text t7;
    @FXML
    private Text t8;
    @FXML
    private Text t9;
    @FXML
    private Text t10;
    
    Text[] tInfo = new Text[10];
    Club club;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        m = Context.getInstance().getMember();
        fotoPerfil.setImage(m.getImage());
        
        idUsuario.setText(m.getNickName());
        
        try {
            club = Club.getInstance();
        } catch (Exception ex) {
            System.out.println("Error al instanciar el club");
        } 
        
        tInfo[0] = t1;tInfo[1] = t2;tInfo[2] = t3;tInfo[3] = t4;tInfo[4] = t5;tInfo[5] = t6;tInfo[6] = t7;tInfo[7] = t8;tInfo[8] = t9;tInfo[9] = t10;
        
        
        rellenarTextos();


    }    
    
    
    public void setMember(Member m){this.m = m;}

    @FXML
    private void irAReservar(MouseEvent event) {
        try{
         FXMLLoader miCargador = new FXMLLoader(getClass().getResource("Reservar.fxml"));
            Parent root = miCargador.load();
            // Pasar parámetros entres escenas--------------------------------------------
            ReservarController controladorReservar = miCargador.getController();
            controladorReservar.setMember(m);
            //----------------------------------------------------------------------------
            JavaFXMLApplication.setRoot(root);
        } catch (IOException ex) {
            System.out.println("Escena no Encontrada");
        }
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
    
    
    public void rellenarTextos(){
        List<Booking> reservas = club.getUserBookings(m.getNickName());
        int guarda = reservas.size() - 10;
        guarda = Math.max(guarda, 0);
        
        
        for(int i = reservas.size() - 1, pos = 0; i >= guarda; i--,pos++){
           
            
            Booking b = reservas.get(i);
            
            int horaPista = b.getFromTime().getHour();
            String horaFormato = "";
            if(horaPista == 9)horaFormato="0";
            horaFormato+= horaPista + ":00";
            
            int diaCreada = b.getBookingDate().getDayOfMonth();
            int mesCreada = b.getBookingDate().getDayOfMonth();
            int anoCreada = b.getBookingDate().getYear();
            String fechaCreacion = "Reserva realizada el " + diaCreada + "-" + mesCreada + "-" + anoCreada;
            
            String pista = b.getCourt().getName();
            int diaPista = b.getMadeForDay().getDayOfMonth();
            int mesPista = b.getMadeForDay().getMonthValue();
            int anoPista = b.getMadeForDay().getYear();
            System.out.println(anoPista);
            String fechaDeLaReserva = " para la " + pista + " el " + diaPista + "-" + mesPista + "-" + anoPista 
                    +" a las " + horaFormato + "-" + (horaPista + 1) + ":00";
            
            
            
           
            
            
        
            String res = fechaCreacion + fechaDeLaReserva;
            tInfo[pos].setText(res);
        }
        
        
    }
    
}
