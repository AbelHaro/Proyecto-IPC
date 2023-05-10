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

//ihbkygvb
//grgrgr
public class JavaFXMLApplication extends Application {
    
    FXMLLoader loader;
    Parent root;
    private static Scene scene;
    private static HashMap<String, Parent> roots = new HashMap<>();
    
    
    @Override
    public void start(Stage stage) throws Exception {
        //======================================================================
        
        inicializarVistas();
        
        //======================================================================
        
        
        //======================================================================
        // 3- asiganación de la escena al Stage que recibe el metodo 
        //     - configuracion del stage
        //     - se muestra el stage de manera no modal mediante el metodo show()
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("start PROJECT - IPC:");
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }

    /*
    Método público accesible desde los controladores para cambiar la vista
    Ejemplo de uso desde una clase controladora:
        setRoot("IniciarSesion");
    Muestra por pantalla la vista de IniciarSesion
    */
    public static void setRoot(String clave){
        Parent rootL = roots.get(clave);
        if(rootL != null){
            setRoot(rootL);
        } else {
            System.err.printf("No se encuentra la escena %s", clave);
        }
    }
    
    /*
    Método privado auxiliar para cambiar la vista
    */
    private static void setRoot(Parent root){
        scene.setRoot(root);
    }
    
    /*
    Método para inicializar precargando todas las vistas
    Para añadir más vistas copiar y pegar:
    
        loader = new  FXMLLoader(getClass().getResource("IniciarSesionNeutro.fxml"));
        root = loader.load();
        roots.put("InciarSesion", root);
    
    Utilizar la ruta de la vista y un nombre para utilizar de clave en el mapa
    IMMPORTANTE: La última vista en precargar será la que se muestre al iniciar el programa
    */
    private void inicializarVistas() throws IOException{
        loader = new  FXMLLoader(getClass().getResource("IniciarSesionNeutro.fxml"));
        root = loader.load();
        roots.put("IniciarSesion", root);
        
        loader = new  FXMLLoader(getClass().getResource("Principal.fxml"));
        root = loader.load();
        roots.put("Principal", root);
    }
}
