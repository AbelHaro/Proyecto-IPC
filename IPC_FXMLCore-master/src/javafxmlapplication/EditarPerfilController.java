/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import static javafxmlapplication.RegistrarseV2Controller.isFullName;
import static javafxmlapplication.RegistrarseV2Controller.isValidTarjeta;
import static javafxmlapplication.RegistrarseV2Controller.isValidTelefono;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class EditarPerfilController implements Initializable {

    @FXML
    private CheckBox check;
    @FXML
    private TextField campoNombre;
    @FXML
    private Text errorNombre;
    @FXML
    private TextField campoApellido;
    @FXML
    private Text errorApellido;
    @FXML
    private TextField campoTelefono;
    @FXML
    private Text errorTelefono;
    @FXML
    private TextField campoNick;
    @FXML
    private Text errorNick;
    @FXML
    private PasswordField campoPassword;
    @FXML
    private Text errorPassword;
    @FXML
    private ImageView image;
    @FXML
    private TextField campoTarjeta1;
    @FXML
    private TextField campoTarjeta2;
    @FXML
    private TextField campoTarjeta3;
    @FXML
    private TextField campoTarjeta4;
    @FXML
    private Text errorTarjeta;
    @FXML
    private TextField campoSVC;
    @FXML
    private Text errorSVC;
    
    Member m;
    
    Image[] avatares = new Image[8];
    int pos = 0;
    Club club;
    List <Member> miembros;
    @FXML
    private Label reservarPista;
    @FXML
    private ImageView fotoPerfil;
    @FXML
    private Label idUsuario;
    @FXML
    private Label desconectarse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        m = Context.getInstance().getMember();
        fotoPerfil.setImage(m.getImage());
        idUsuario.setText(m.getNickName());
        reservarPista.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue){reservarPista.setUnderline(true);}
            else {reservarPista.setUnderline(false);}
        });
        
        
        campoNombre.setText(m.getName());
        campoApellido.setText(m.getSurname());
        campoTelefono.setText(m.getTelephone());
        campoNick.setText(m.getNickName());
        campoPassword.setText(m.getPassword());
        campoTarjeta1.setText(m.getCreditCard().substring(0,4));
        campoTarjeta2.setText(m.getCreditCard().substring(4,8));
        campoTarjeta3.setText(m.getCreditCard().substring(8,12));
        campoTarjeta4.setText(m.getCreditCard().substring(12,16));
        campoSVC.setText("" + m.getSvc());
        image.setImage(m.getImage());
        check.setSelected(true);
        
        
        
        inicializarImagenes();
        try {
            club = Club.getInstance();
        } catch (ClubDAOException | IOException ex) {
            System.out.println("Error al instanciar el club");
        }
        
        campoNombre.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!isFullName(campoNombre.getText())){errorNombre.setVisible(true);
            }else{errorNombre.setVisible(false);}
        });
        campoNombre.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue && campoNombre.getText().length() <= 0 )errorNombre.setVisible(true);});
        
        
        
        
        campoApellido.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!isFullName(campoApellido.getText())){errorApellido.setVisible(true);
            }else{errorApellido.setVisible(false);}
        });
        campoApellido.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue && campoApellido.getText().length() <= 0) errorApellido.setVisible(true);});
        
        
        
        campoTelefono.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!isValidTelefono(campoTelefono.getText())){errorTelefono.setVisible(true);
            }else{errorTelefono.setVisible(false);}
        });
        campoTelefono.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue && campoTelefono.getText().length() != 9 ) errorTelefono.setVisible(true);});
        
        
        
        
        campoNick.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (isValidNick(campoNick.getText())) {
                case 1:
                    errorNick.setText("Nick no válido");
                    errorNick.setVisible(true);
                    break;
                case 2:
                    errorNick.setText("Nick en uso");
                    errorNick.setVisible(true);
                    break;
                default:
                    errorNick.setVisible(false);
                    break;
            }
        });
        campoNick.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue && campoNick.getText().length() == 0 ){
                errorNick.setText("Nick no válido");
                errorNick.setVisible(true);
        }});
        
        
        
        campoPassword.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (isValidPassword(campoPassword.getText())) {
                case 1:
                    errorPassword.setText("Sólo caracteres y números");
                    errorPassword.setVisible(true);
                    break;
                case 2:
                    errorPassword.setText("Mínimo 6 caracteres");
                    errorPassword.setVisible(true);
                    break;
                default:
                    errorPassword.setVisible(false);
                    break;
            }
        });
        
        campoPassword.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue && campoPassword.getText().length() < 6 ){
                errorPassword.setText("Mínimo 6 caracteres");
                errorPassword.setVisible(true);
        }});
        
        
        
        
        
        campoTarjeta1.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!isValidTarjeta(campoTarjeta1.getText())){errorTarjeta.setVisible(true);
            }else{errorTarjeta.setVisible(false);}
        });
        campoTarjeta1.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue && campoTarjeta1.getText().length() != 4 ) errorTarjeta.setVisible(true);});
        
        
        
        campoTarjeta2.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!isValidTarjeta(campoTarjeta2.getText())){errorTarjeta.setVisible(true);
            }else{errorTarjeta.setVisible(false);}
        });
        campoTarjeta2.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue && campoTarjeta2.getText().length() != 4 ) errorTarjeta.setVisible(true);});
        
        
        
        campoTarjeta3.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!isValidTarjeta(campoTarjeta2.getText())){errorTarjeta.setVisible(true);
            }else{errorTarjeta.setVisible(false);}
        });
        campoTarjeta3.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue && campoTarjeta3.getText().length() != 4 ) errorTarjeta.setVisible(true);});
        
        
        
        
        campoTarjeta4.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!isValidTarjeta(campoTarjeta4.getText())){errorTarjeta.setVisible(true);} 
            else{errorTarjeta.setVisible(false);}
        });
        campoTarjeta4.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue && campoTarjeta4.getText().length() != 4 ) errorTarjeta.setVisible(true);});
        
       
        campoSVC.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!isValidTarjeta(campoSVC.getText())){errorSVC.setVisible(true);
            }else{errorSVC.setVisible(false);}
        });
        
        campoSVC.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue && campoSVC.getText().length() != 3 ) errorSVC.setVisible(true);});
            
        
        
        
        campoTarjeta1.textProperty().addListener((obs, oldValue, newValue) -> {
            if(campoTarjeta1.getText().length() > 4){campoTarjeta1.setText(campoTarjeta1.getText().substring(0, campoTarjeta1.getText().length()-1));}
        });
        campoTarjeta2.textProperty().addListener((obs, oldValue, newValue) -> {
            if(campoTarjeta2.getText().length() > 4){campoTarjeta2.setText(campoTarjeta2.getText().substring(0, campoTarjeta2.getText().length()-1));}
        });
        campoTarjeta3.textProperty().addListener((obs, oldValue, newValue) -> {
            if(campoTarjeta3.getText().length() > 4){campoTarjeta3.setText(campoTarjeta3.getText().substring(0, campoTarjeta3.getText().length()-1));}
        });
        campoTarjeta4.textProperty().addListener((obs, oldValue, newValue) -> {
            if(campoTarjeta4.getText().length() > 4){campoTarjeta4.setText(campoTarjeta4.getText().substring(0,campoTarjeta4.getText().length()-1));}
        });
        campoSVC.textProperty().addListener((obs, oldValue, newValue) -> {
            if(campoSVC.getText().length() > 3){campoSVC.setText(campoSVC.getText().substring(0, campoSVC.getText().length()-1));}
        });
        campoTelefono.textProperty().addListener((obs, oldValue, newValue) -> {
            if(campoTelefono.getText().length() > 9){campoTelefono.setText(campoTelefono.getText().substring(0, campoTelefono.getText().length()-1));}
        });
        
        
        
        
    }    
    

    @FXML
    private void registrarse(ActionEvent event) {
        if(errorNombre.visibleProperty().getValue() || errorApellido.visibleProperty().getValue()
                || errorTelefono.visibleProperty().getValue() || errorNick.visibleProperty().getValue()
                || errorPassword.visibleProperty().getValue() || !check.isSelected() ){
            avisoCampos();
            return;
        }
                
                
        
        
        
        boolean pago = true;
        if(campoTarjeta1.getText().length() != 4 || campoTarjeta2.getText().length() != 4
                || campoTarjeta3.getText().length() != 4 || campoTarjeta4.getText().length() != 4
                || errorTarjeta.visibleProperty().getValue()){
                
            errorTarjeta.setVisible(true);
            pago = false;
        }
        if(campoSVC.getText().length() != 3 || errorSVC.visibleProperty().getValue()){
            errorSVC.setVisible(true);
            pago = false;
        }
        
        if(!pago && !avisoPago())return;
        
        
        String name = campoNombre.getText();
        String apellido = campoApellido.getText();
        String telefono = campoTelefono.getText();
        String nick = campoNick.getText();
        String password = campoPassword.getText();
        Image img = image.getImage();
        String tarjeta = "";
        int svc = 0;
        if(pago){
            tarjeta = campoTarjeta1.getText() + campoTarjeta2.getText() + campoTarjeta3.getText() + campoTarjeta4.getText();
            svc = Integer.parseInt(campoSVC.getText());
        }
        
        try {
            //Member m = club.registerMember(name, apellido, telefono, nick, password, tarjeta, svc, img);
           
            
            Member m = club.getMemberByCredentials(nick, password);
            m.setName(name);
            m.setSurname(apellido);
            m.setTelephone(telefono);
            m.setPassword(password);
            m.setTelephone(tarjeta);
            m.setSvc(svc);
            m.setImage(img);
            
            avisoRegistroCorrecto(name);
            
            
            initialize(null, null);
        
        } catch (Exception ex) {
            System.out.println("Error al cargar la escena");
        }
    }

    @FXML
    private void izq(ActionEvent event) {image.imageProperty().setValue(avatares[Math.abs(--pos) % avatares.length]);}

    @FXML
    private void der(ActionEvent event) {image.imageProperty().setValue(avatares[Math.abs(++pos) % avatares.length]);}

    @FXML
    private void txtCelularKeyTyped(KeyEvent event) {
    }
    
    public void setMember(Member m){this.m = m;}
    
    
     public static boolean isFullName(String str) {
        String expression = "^[a-zA-Z\\s]+"; 
        return str.matches(expression);        
    }
    
    public static boolean isValidTelefono(String str) {
        String expression = "^[0-9]+"; 
        return str.matches(expression);        
    }
    
    public int isValidNick(String str) {
        String expression = "^[0-9a-zA-Z]+";
        if(!str.matches(expression)){return 1;}
        miembros = club.getMembers();
        for(int i = 0; i < miembros.size(); i++){
            Member m = miembros.get(i);
            if(m.getNickName().equals(str)){return 2;}
        }
        return 3;
    }
    
    public int isValidPassword(String str) {
        String expression = "^[0-9a-zA-Z]+";
        if(!str.matches(expression)){return 1;}
        else if(str.length() < 6){return 2;}
        else return 3;
    }
    
    public static boolean isValidTarjeta(String str){
        String expression = "^[0-9]+"; 
        return str.matches(expression);
    }
    
    public boolean avisoPago(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de registro");
        alert.setHeaderText("Datos de pago");
        alert.setContentText("Los datos de pago introducidos son incorrectos o incompletos.\nDesea confirmar el registro sin estos datos?");
        ButtonType buttonTypeYes = new ButtonType("Aceptar", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            return result.get() == buttonTypeYes;
        }
        return true; 
    }
    
    public void avisoCampos(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Confirmación de registro");
        alert.setHeaderText("Campos incorrectos");
        alert.setContentText("Los campos deben ser correctos y se ha de aceptar el tratamiento de los datos para confirmar el registro.");
        
        
        alert.showAndWait();
    }
    
    public void avisoRegistroCorrecto(String n){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmación de registro");
        alert.setHeaderText("Registro correctamente");
        alert.setContentText("El registro se ha completado correctamente.\nBIENVENIDO/A " + n.toUpperCase() +"!");
        
        
        alert.showAndWait();
    }
    
    public void inicializarImagenes(){
        avatares[0] = new Image(getClass().getResourceAsStream("/images/perfil/Default.png"));
        avatares[1] = new Image(getClass().getResourceAsStream("/images/perfil/CarlosAlcaraz.jpg"));
        avatares[2] = new Image(getClass().getResourceAsStream("/images/perfil/RafaNadal.jpg"));
        avatares[3] = new Image(getClass().getResourceAsStream("/images/perfil/Djokovic.jpg"));
        avatares[4] = new Image(getClass().getResourceAsStream("/images/perfil/Federer.jpg"));
        avatares[5] = new Image(getClass().getResourceAsStream("/images/perfil/Paula.jpg"));
        avatares[6] = new Image(getClass().getResourceAsStream("/images/perfil/Serena.jpg"));
        avatares[7] = new Image(getClass().getResourceAsStream("/images/perfil/Fernando.jpg"));
        
        
        image.imageProperty().setValue(avatares[pos]);
    }

    @FXML
    private void irAReservar(MouseEvent event) {try {
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
