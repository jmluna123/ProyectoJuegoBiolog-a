/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popOut;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author josie
 */
public class Message {
    private String title;
    private String text;
    private String rutaImagen;
    private ImageView imagen;
    
    public Message(String title, String text, String route){
        this.title = title;
        this.text = text;
        this.rutaImagen = route;
        Image img = new Image(getClass().getResourceAsStream(route),
                                250,
                                250,
                                true,
                                true);
        imagen = new ImageView(img);
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public ImageView getImagen() {
        return imagen;
    }
}
