package proyectojuego;

import Maps.BeachMap;
import mainCharacters.Animal;
import Maps.MapPane;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import static mainCharacters.CONSTANTES.*;

/**
 *
 * @author Jocellyn Luna
 */
public class MainPane { 
    private MapPane gamePane;   
    public static final BorderPane root = new BorderPane();
    private Label marcador;
    private int points;
    private HBox vida;
    
    public MainPane(){
        gamePane = new BeachMap(0,0);
        vida = new HBox();
        marcador = new Label(String.valueOf(points));
        
        root.setTop(createTop());
        root.setCenter(gamePane.getRoot());
        //TODO: se debe crear un botton para las instrucciones del juego, y una imagen del animal con el que se juega
        //ROOT.setBottom(buildControllers());
    }
    
    private AnchorPane createTop(){
        generateHearts();
        AnchorPane top = new AnchorPane();
        
        Label lm = new Label("Marcador");
        lm.setFont(FONT);
        
        marcador.setFont(FONT);
        
        top.getChildren().addAll(vida,lm,marcador);
        
        AnchorPane.setLeftAnchor(vida, 10.0);
        AnchorPane.setRightAnchor(lm, 50.0);
        AnchorPane.setRightAnchor(marcador, 10.0);
                
        return top;
    }
    
    public Pane getRoot(){
        return root;
    }
    
    private List<ImageView> calculateHearts(){
        LinkedList<ImageView> hearts = new LinkedList<>();
        int count = Animal.vida/2;
        
        for(int i = 0; i < count; i++){
            hearts.add(obtainImage("heart_complete.png"));
        }
        if(Animal.vida%2==1){
            hearts.add(obtainImage("heart_half.png"));
        }
        
        if(count < 0){
            count = 0;
        }
        for(int i = 0; i < VIDA/2 - count; i++){
                hearts.add(obtainImage("heart_empty.png"));
            }
        
        return hearts;
    }
    
    public void generateHearts(){
        vida.getChildren().clear();
        vida.getChildren().addAll(calculateHearts());
    }
    
    private ImageView obtainImage(String route){
        return new ImageView(new Image(getClass().getResourceAsStream(
                        RUTA_IMAGENES+ route),
                                35,
                                35,
                                true,
                                true));
    }
}
