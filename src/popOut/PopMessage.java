/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popOut;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author josie
 */
public class PopMessage extends PopOut {

    public PopMessage(Stage origen, Message mensaje, String title) {
        super(origen, mensaje, title);
    }

    @Override
    void createElements() {
        HBox centro = new HBox();
        centro.setSpacing(25);
        centro.setAlignment(Pos.CENTER);
        centro.getChildren().add(mensaje.getImagen());

        Text texto = new Text(mensaje.getText());
        texto.setStyle("-fx-font-size: 12pt");
        centro.getChildren().add(texto);
        root.getChildren().add(centro);
    }
    
}
