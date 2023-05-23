/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.io.IOException;
import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Club;
import model.Member;
import tenisclubtest.TCDataGenerator;

//ihbkygvb
//grgrgr
public class JavaFXMLApplication extends Application {
    
    
    FXMLLoader loader;
    Parent root;
    private static Scene scene;
    Club club;
    Member m;
    
    @Override
    public void start(Stage stage) throws Exception {
        //======================================================================
        // Ejecuta el programa para llenar la base de datos con ejemplos

        
        

       
        //TCDataGenerator.main(null);
        //club = model.Club.getInstance();

        
        // Carga la vista principal
        loader = new  FXMLLoader(getClass().getResource("IniciarSesionNeutro.fxml"));
        root = loader.load();
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setTitle("GREENBALL");
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }

    
    
    
    
    
    
    public Club getClub(){return this.club;}
    
    /*
    Método privado auxiliar para cambiar la vista
    */
    public static void setRoot(Parent root){
        scene.setRoot(root);
    }
    
    
}
