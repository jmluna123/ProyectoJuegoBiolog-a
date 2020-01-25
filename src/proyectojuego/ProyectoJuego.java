/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojuego;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author josie
 */
public class ProyectoJuego extends Application {
    
    public static Scene escena;
    public static MainPane pj;
    
    @Override
    public void start(Stage primaryStage) {
        escena = new Scene(new Pane());
        pj = new MainPane();
        escena.setRoot(pj.getRoot());
        primaryStage.setScene(escena);
        
        
        primaryStage.setTitle("ExploraBIO");
        //mostramos la pantalla
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void stop(){
        //pj.hiloTiempo.parar();
    }
}
