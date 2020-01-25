package Maps;

import java.util.LinkedList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import mainCharacters.Animal;
import mainCharacters.CONSTANTES;
import proyectojuego.ProyectoJuego;
import static mainCharacters.CONSTANTES.*;
import proyectojuego.MainPane;

/**
 *
 * @author Jocellyn Luna
 */
public abstract class MapPane {
    private final Pane gamePane;
    
    protected Rectangle pieza_colision;
    protected List<Shape> borders;
    protected List<Node> enemies;
    protected Animal animal;
    
    protected final double Xmax;
    protected final double Ymax;
    
    public MapPane(Animal animal, String routeImage){
        gamePane = new Pane();
        gamePane.setStyle("-fx-background-image: url('"+CONSTANTES.RUTA_IMAGENES+ routeImage +"');"
                + "-fx-background-repeat: stretch;"
                + "-fx-background-size: "+CONSTANTES.GAME_WIDTH+" "+(CONSTANTES.GAME_HEIGHT)+"; "
                + "-fx-background-position: center center;");
        gamePane.setPrefSize(CONSTANTES.GAME_WIDTH, CONSTANTES.GAME_HEIGHT);
        
        pieza_colision = new Rectangle(animal.getPosicionX(), animal.getPosicionY(),90,70);
        borders = new LinkedList<>();
        enemies = new LinkedList<>();
        this.animal = animal;
        moveKeyEvent();
        Xmax = CONSTANTES.GAME_WIDTH - ANIMAL_WIDTH;
        Ymax = CONSTANTES.GAME_HEIGHT - ANIMAL_HEIGHT;
    }
    
    //abstract void initialStart();
    abstract void generarLimites();
    abstract void generarEnemigos();
    abstract boolean isChangeMapUp(double x, double y);
    abstract boolean isChangeMapDown(double x, double y);
    abstract boolean isChangeMapLeft(double x, double y);
    abstract boolean isChangeMapRight(double x, double y);
    
    protected Shape generarBorde(double x, double y, double ancho , double largo){
        Rectangle r = new Rectangle(x,y, ancho, largo);
        //TODO: cambiar a transparente los limites
        //r.setFill(Color.TRANSPARENT);
        return r;
    }
    
    private void moveKeyEvent(){
        ProyectoJuego.escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    
                        if(!isCollision(0)){
                            
                            Double y1 = animal.getPosicionY() - CONSTANTES.DELTA_DESPLAZAMIENTO;
                            if(y1 < Ymax &&  y1 > 0){
                                animal.fijarPosicionObjeto(animal.getPosicionX(), y1);
                            }
                        }if(isEnemy()){
                            System.out.println("Ouch!");
                        }
                        break;
                    case DOWN: 
                       if(!isCollision(1)){
                            Double y2 = animal.getPosicionY() + CONSTANTES.DELTA_DESPLAZAMIENTO;
                            if(y2 < Ymax && y2 >0){
                                animal.fijarPosicionObjeto(animal.getPosicionX(), y2);
                            }
                        }
                        break;
                    
                    case RIGHT:  
                        if(!isCollision(2)){
                            Double x1 = animal.getPosicionX() + CONSTANTES.DELTA_DESPLAZAMIENTO;
                            if(x1 < Xmax &&  x1 > 0){
                            animal.fijarPosicionObjeto(x1, animal.getPosicionY());
                            }
                            isChangeMapRight(x1, animal.getPosicionY());
                        }
                        
                        break;
                    case LEFT:
                        if(!isCollision(3)){
                            Double x2 = animal.getPosicionX() - CONSTANTES.DELTA_DESPLAZAMIENTO;
                            if(x2 < Xmax &&  x2 > 0){
                                animal.fijarPosicionObjeto(x2, animal.getPosicionY());
                            }
                            isChangeMapLeft(x2, animal.getPosicionY());
                        }
                }
            }
        });
    }
    
    private boolean isEnemy(){
        for(Node n: enemies){
            if(isCollision(n, animal.getObjeto())){
                return true;
            }
        }
        return false;
    }
    
    protected boolean isCollision(int type){
        switch(type){
            case 0: //UP
                pieza_colision.setLayoutX(animal.getPosicionX());
                pieza_colision.setLayoutY(animal.getPosicionY()-10);
                break;
            case 1: //DOWN
                pieza_colision.setLayoutX(animal.getPosicionX());
                pieza_colision.setLayoutY(animal.getPosicionY()+10);
                break;
            case 2: //RIGHT
                pieza_colision.setLayoutX(animal.getPosicionX()+10);
                pieza_colision.setLayoutY(animal.getPosicionY());
                break;
            case 3: //LEFT
                pieza_colision.setLayoutX(animal.getPosicionX()-10);
                pieza_colision.setLayoutY(animal.getPosicionY());
                break;
        }
        
        for(Shape s: borders){
            if(isCollision(s, pieza_colision))
                return true;
        }
        return false;
    }
    
    public Pane getRoot() {
        return gamePane;
    }
    
    protected final void loadElementsPane(){
        gamePane.getChildren().addAll(borders);
        gamePane.getChildren().addAll(enemies);
        gamePane.getChildren().addAll(animal.getObjeto());
    }
    
    protected boolean changeMap(MapPane map){
        MainPane.root.setCenter(map.gamePane);
        return true;
    }
    
    public static boolean isCollision(Node n1, Node n2){
        Bounds b1 = n1.getBoundsInParent();
        Bounds b2 = n2.getBoundsInParent();
        return b1.intersects(b2);
    }
    
     /*
    private boolean chequearColisiones(){
        //chequeamos si hay una interseccion entre el astronauta y 
        //y algunas de las piezas, si lo hay debemos remover la pieza de la escena
        //y de la lista de piezas
        //como queremos iterar y remover a la vez, usamos un iterator
        Iterator<Shape> it = piezas.iterator();
        while(it.hasNext()){
            //obtenemos el siguiente elemento de la piezas
            Shape p = it.next();
            if(isCollision(p,animal.getObjeto())){
                return true;
            }
        }
        return false;
    }
    */
}
