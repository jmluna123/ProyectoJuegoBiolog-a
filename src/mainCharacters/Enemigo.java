package mainCharacters;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import static mainCharacters.CONSTANTES.RUTA_IMAGENES;

/**
 *
 * @author josie
 */
public class Enemigo {
    private ImageView enemigo;
    private String nombre;
    private String ruta;
    
    public Enemigo(String route, int setStart, int setEnd, int height, boolean isHorizontal){
        //creamos a imageView del enemigo
        //agregar la imagen
        //creando la imagen, le pasamos el ancho, el alto, si queremos mantener el 
        //radio entre el ancho y el alto y que s
        Image img = new Image(getClass().getResourceAsStream(
                        RUTA_IMAGENES+ route),
                                CONSTANTES.ANIMAL_WIDTH,
                                CONSTANTES.ANIMAL_HEIGHT,
                                true,
                                true);
        //agrega imagen al imageView
        enemigo = new ImageView(img);
        
        move(setStart, setEnd, height, isHorizontal);
    }

    private void move(int setStart, int setEnd, int anchor, boolean isHorizontal){
        TranslateTransition animation = new TranslateTransition(); 
      
        //Setting the duration of the transition  
        animation.setDuration(Duration.millis(3000)); 

        //Setting the node for the transition 
        animation.setNode(enemigo); 
        
        if(isHorizontal){
            animation.setFromY(120);
            animation.setToY(350);
            animation.setFromX(1000);
            animation.setToX(1000);
        }else{
            animation.setFromY(120);
            animation.setToY(350);
            animation.setFromX(1000);
            animation.setToX(1000);
        }
        //Setting the cycle count for the transition 
        animation.setCycleCount(50); 
        animation.setAutoReverse(true);
        animation.play(); 
        
    }
    
    public double getPosicionX(){
        return enemigo.getLayoutX();
    }
    
    public double getPosicionY(){
        return enemigo.getLayoutY();
    }
    
    public Node getObjeto(){
        return enemigo;
    }
}
