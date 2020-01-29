package proyectojuego;

import Maps.BatiPelagicoMap;
import popOut.PopOut;
import Maps.SupraLitoralMap;
import Maps.MapPane;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import static Utilitarios.CONSTANTES.*;
import javafx.scene.layout.GridPane;
import mainCharacters.Enemigo;
import popOut.PopKill;

/**
 *
 * @author Jocellyn Luna
 */
public class MainPane { 
    private MapPane gamePane;   
    public static final BorderPane root = new BorderPane();
    private HBox vida;
    public static int vida_Animal = VIDA;
    private boolean isEnd;
    private static Enemigo enemigoKIll; 
    
    public MainPane(){
        isEnd = true;
        gamePane = new SupraLitoralMap(900, 29);
        vida = new HBox();
        root.setTop(createTop());
        root.setCenter(gamePane.getRoot());
        root.setBottom(gamePane.buildInformation());
    }
    
    private AnchorPane createTop(){
        generateHearts();
        AnchorPane top = new AnchorPane();
        top.getChildren().addAll(vida);
        AnchorPane.setLeftAnchor(vida, 10.0);
        return top;
    }
    
    public Pane getRoot(){
        return root;
    }
    
    private List<ImageView> calculateHearts(){
        LinkedList<ImageView> hearts = new LinkedList<>();
        int count = vida_Animal;
        for(int i = 0; i < count; i++){
            hearts.add(obtainImage("heart_complete.png"));
        }
        if(count < 0){
            count = 0;
        }
        System.out.println(count);
        for(int i = 0; i < VIDA - count; i++){
                hearts.add(obtainImage("heart_empty.png"));
            }
        
        return hearts;
    }
    
    public void generateHearts(){
        vida.getChildren().clear();
        vida.getChildren().addAll(calculateHearts());
        if(vida_Animal <= 0 && isEnd){
            isEnd = false;
            PopOut pop = new PopKill(proyectojuego.ProyectoJuego.stageMain, enemigoKIll.getMensaje(), "Game Over");
        }
    }
    
    private ImageView obtainImage(String route){
        return new ImageView(new Image(getClass().getResourceAsStream(
                        RUTA_IMAGENES+ route),
                                35,
                                35,
                                true,
                                true));
    }
    
    public MapPane getMapPane(){
        return gamePane;
    }

    public static void setEnemigoKIll(Enemigo enemigoKIll) {
        MainPane.enemigoKIll = enemigoKIll;
    }
    
    public void restart(){
        isEnd = true;
        vida_Animal = VIDA;
        generateHearts();
        gamePane.changeMap(new SupraLitoralMap(1000,50));  
        ANIMAL_WIDTH= 70;
        ANIMAL_HEIGHT= 50;
        DELTA_DESPLAZAMIENTO = 15;
    }
}
