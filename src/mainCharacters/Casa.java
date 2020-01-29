/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainCharacters;

import Utilitarios.CONSTANTES;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author josie
 */
public class Casa {
    private boolean isVisited;
    private final ImageView imagen;
    
    public Casa(double x, double y, String ruta){
        isVisited = false;
        Image img = new Image(getClass().getResourceAsStream(ruta),
                                100,
                                100,
                                true,
                                true);
        imagen = new ImageView(img);
        imagen.setLayoutX(x);
        imagen.setLayoutY(y);
    }
    
    public ImageView getObjeto(){
        return imagen;
    }
    
    public boolean getVisited(){
        return isVisited;
    }
    
    public void setVisited(boolean isVisited){
        this.isVisited = isVisited;
    }
}
