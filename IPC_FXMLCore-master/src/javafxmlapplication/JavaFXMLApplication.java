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
import tenisclubtest.TCDataGenerator;
import model.Club.*;

//ihbkygvb
//grgrgr
public class JavaFXMLApplication extends Application {
    
    
    FXMLLoader loader;
    Parent root;
    private static Scene scene;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        //======================================================================
        // Ejecuta el programa para llenar la base de datos con ejemplos
<<<<<<< Updated upstream
        TCDataGenerator tcd = new TCDataGenerator();
        tcd.main(null);
        model.Club.getInstance();
=======
       
        TCDataGenerator.main(null);
        
>>>>>>> Stashed changes
        
        // Carga la vista principal
        loader = new  FXMLLoader(getClass().getResource("Principal.fxml"));
        root = loader.load();
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("GREENBALL");
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }

    
    
    
    /*
    Método privado auxiliar para cambiar la vista
    */
    public static void setRoot(Parent root){
        scene.setRoot(root);
    }
    
    
}
