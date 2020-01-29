/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popOut;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author josie
 */
public class PopKill extends PopOut{
    
    public PopKill(Stage origen, Message mensaje, String title) {
        super(origen, mensaje, title);
        
        main.setOnCloseRequest(event -> {
            origen.close();
        });
    }
    
    @Override
    protected void createElements(){
        root.getChildren().add(mensaje.getImagen());
        HBox botones = new HBox();
        botones.setAlignment(Pos.CENTER);
        botones.setSpacing(200);

        Button salir = new Button("Salir");
        salir.setPadding(new Insets(5, 5, 5, 5));
        salir.setPrefSize(100, 50);
        salir.setOnAction((event) -> {
            origen.close();
            main.close();
        });

        Button reintentar = new Button("Juego nuevo");
        reintentar.setPadding(new Insets(5, 5, 5, 5));
        reintentar.setPrefSize(100, 50);
        reintentar.setOnAction((event) -> {
            proyectojuego.ProyectoJuego.pj.restart();
            main.close();
        });

        botones.getChildren().addAll(salir, reintentar);
        root.getChildren().add(botones);
    }
    
}
