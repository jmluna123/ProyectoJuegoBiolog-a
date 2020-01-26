package mainCharacters;

import Maps.MapPane;
import Utilitarios.CONSTANTES;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import static Utilitarios.CONSTANTES.RUTA_IMAGENES;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.application.Platform;
import proyectojuego.MainPane;

/**
 *
 * @author josie
 */
public class Enemigo {
    private final ImageView enemigo;
    private final Animal animal;
    private String nombre;
    private String ruta;
    private final boolean isHorizontal;
    private RunnableAttack hilo;
    private TranslateTransition animation;
    
    public Enemigo(String route, boolean isHorizontal, Animal animal ){
        this.animal = animal;
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
        this.isHorizontal = isHorizontal;
    }

    public void move(int setStart, int setEnd, int anchor){
        animation = new TranslateTransition(); 
      
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
        
        hilo = new RunnableAttack();
        Thread t = new Thread(hilo);
        t.start();
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

    public RunnableAttack getHilo() {
        return hilo;
    }
    
    public class RunnableAttack implements Runnable{
        private boolean isRunning = true;
        @Override
        public void run() {
            while(animation.getStatus() == Animation.Status.RUNNING){
                if(MapPane.isCollision(enemigo, animal.getObjeto())){
                    MainPane.vida_Animal--;
                    Platform.runLater(()->{
                        proyectojuego.ProyectoJuego.pj.generateHearts();;
                    });
                
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Enemigo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        public void parar(){
            animation.stop();
        }
    }
}
