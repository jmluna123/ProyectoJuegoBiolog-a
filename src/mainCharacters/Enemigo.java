package mainCharacters;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import popOut.Message;

/**
 *
 * @author josie
 */
public class Enemigo {
    private final ImageView enemigo;
    private final Message mensaje;
    
    public Enemigo(String route,double x, double y, Message mensaje){
        Image img = new Image(getClass().getResourceAsStream(route),
                                100,
                                100,
                                true,
                                true);
        enemigo = new ImageView(img);
        this.mensaje = mensaje;
        enemigo.setLayoutX(x);
        enemigo.setLayoutY(y);
    }
    
    public double getPosicionX(){
        return enemigo.getLayoutX();
    }
    
    public double getPosicionY(){
        return enemigo.getLayoutY();
    }
    
    public ImageView getObjeto(){
        return enemigo;
    }

    public Message getMensaje(){
        return mensaje;
    } 
}
