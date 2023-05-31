/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
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
    @FXML
    private Button a1;
    @FXML
    private Button a2;
    @FXML
    private Button a3;
    @FXML
    private Button a4;
    @FXML
    private Button a5;
    @FXML
    private Button a6;
    @FXML
    private Button a7;
    @FXML
    private Button a8;
    @FXML
    private Button a9;
    @FXML
    private Button a10;
    
    Button[] bAnular = new Button[10];
    @FXML
    private Label reservarPistas;
    @FXML
    private Label datosSocio;
    @FXML
    private Label desconectarse;
    
    
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
        bAnular[0] = a1;bAnular[1] = a2;bAnular[2] = a3;bAnular[3] = a4;bAnular[4] = a5;bAnular[5] = a6;bAnular[6] = a7;bAnular[7] = a8;bAnular[8] = a9;bAnular[9] = a10;
        
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
        
        
        
        for(int pos = 0; pos < 10 && pos < reservas.size(); pos++){
           
            
            Booking b = reservas.get(pos);
            
            int horaPista = b.getFromTime().getHour();
            String horaFormato = "";
            if(horaPista == 9)horaFormato="0";
            horaFormato+= horaPista + ":00";
            
            int diaCreada = b.getBookingDate().getDayOfMonth();
            int mesCreada = b.getBookingDate().getDayOfMonth();
            int anoCreada = b.getBookingDate().getYear();
            //String fechaCreacion = "Reserva realizada el " + diaCreada + "-" + mesCreada + "-" + anoCreada;
            
            String pista = b.getCourt().getName();
            int diaPista = b.getMadeForDay().getDayOfMonth();
            int mesPista = b.getMadeForDay().getMonthValue();
            int anoPista = b.getMadeForDay().getYear();
            String fechaDeLaReserva = "Reserva para la " + pista + " el " + diaPista + " - " + mesPista + " - " + anoPista 
                    +" a las " + horaFormato + " - " + (horaPista + 1) + ":00";
            
            
            String res = fechaDeLaReserva;
            if(b.getPaid()){
                res += " (pagada)";
            } else {
                res += " (no pagada)";
            }
            tInfo[pos].setText(res);
            
            
            boolean sePuedeAnula = false;
            sePuedeAnula = sePuedeAnula || LocalDate.now().plusDays(1).isBefore(b.getMadeForDay());
            sePuedeAnula = sePuedeAnula || (LocalDate.now().plusDays(1).isEqual(b.getMadeForDay()) &&  LocalDateTime.now().getHour() < horaPista);
            
            
            if(sePuedeAnula && tInfo[pos].getText().length() != 0){
                bAnular[pos].setVisible(true);
            } else {
                bAnular[pos].setVisible(false);
            }
        }
        
        
    }
    
    public void rellenarVacio(){
        for(int i = 0; i < 10; i++){
            tInfo[i].setText("");
            bAnular[i].setVisible(false);
        }
    }

    @FXML
    private void anular(ActionEvent event) {
        int numBotonAnular = Integer.parseInt(((Button) event.getSource()).getId().substring(1)) - 1;
        List<Booking> reservas = club.getUserBookings(m.getNickName());
        Booking reservaBorrar = reservas.get(numBotonAnular);
        String msg = tInfo[numBotonAnular].getText();
        if(!avisoAnularReserva(msg)){return;}
        try {
            if(Club.getInstance().removeBooking(reservaBorrar)){
                
                tInfo[numBotonAnular].setText("");
                bAnular[numBotonAnular].setVisible(false);
                rellenarVacio();
                rellenarTextos();
                avisoAnular(msg);
            } else {
                System.out.println("Reserva no borrada");

            }
        } catch (Exception ex) {
                System.out.println("Error en el club");
        } 
        
        
    }
    
    public void avisoAnular(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Anulación de reserva");
        alert.setHeaderText("Reserva anulada");
        alert.setContentText("La reserva '" + msg + "' ha sido anulada correctamente.");
        
        
        alert.showAndWait();
    }
    
    
    
    public boolean avisoAnularReserva(String reserva){
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeight(400);
        alert.setTitle("Anulación de reserva");
        alert.setHeaderText("Anulación de reserva");
        alert.setContentText("Se va a anular la siguiente reserva '"+ reserva + "'.\nDesea anular la reserva?");
        ButtonType buttonTypeYes = new ButtonType("Anular", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
       
        
        
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == buttonTypeYes;
    }



    @FXML
    private void reservarPistasMouseExited(MouseEvent event) {
         reservarPistas.setUnderline(false);
    
    }
    

    @FXML
    private void reservarPistasMouseEntered(MouseEvent event) {
                reservarPistas.setUnderline(true);

    }

    @FXML
    private void datosSocioMouseExited(MouseEvent event) {
                datosSocio.setUnderline(false);

    }

    @FXML
    private void datosSocioMouseEntered(MouseEvent event) {
        datosSocio.setUnderline(true);
    }

    @FXML
    private void desconectarseMouseExited(MouseEvent event) {
              desconectarse.setUnderline(false);
    }

    @FXML
    private void desconectarseMouseEntered(MouseEvent event) {
                desconectarse.setUnderline(true);

    }
    
}
