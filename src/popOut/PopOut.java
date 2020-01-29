/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popOut;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author josie
 */
public abstract class PopOut {
    protected Stage main;
    protected Stage origen;
    protected VBox root;
    protected Message mensaje;
    
    public PopOut(Stage origen, Message mensaje, String title){
        main = new Stage();
        this.origen = origen;
        this.mensaje = mensaje;
        main.initModality(Modality.WINDOW_MODAL);
        main.initOwner(origen);
        
        main.setTitle(title);
        root = new VBox();
        
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(550,350);
        root.setSpacing(20);
        root.setPadding(new Insets(10, 20, 20, 20));
        inizializarElementos();

        Scene scene = new Scene(root);
        main.setScene(scene);
        main.showAndWait();
    }
    
    private void inizializarElementos(){
        Label titulo = new Label(mensaje.getTitle());
        titulo.setStyle("-fx-font-weight: bold;-fx-font-size: 15pt");
        root.getChildren().add(titulo);
        
        createElements();
    }
    
    abstract void createElements();
}
