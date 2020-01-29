package Maps;

import java.util.LinkedList;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import mainCharacters.Animal;
import Utilitarios.CONSTANTES;
import proyectojuego.ProyectoJuego;
import static Utilitarios.CONSTANTES.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import mainCharacters.Casa;
import mainCharacters.Enemigo;
import popOut.Message;
import popOut.PopOut;
import popOut.PopMessage;
import proyectojuego.MainPane;

/**
 *
 * @author Jocellyn Luna
 */
public abstract class MapPane {
    protected final Pane gamePane;
    
    protected List<ImageView> health;
    protected List<Shape> borders;
    protected List<Enemigo> enemies;
    protected final List<Casa> objetos;
    protected Animal animal;
    
    private boolean isComida;
    private boolean isEnemigo;
    
    protected final double Xmax;
    protected final double Ymax;
    
    public MapPane(Animal animal, String routeImage){ 
        objetos = new LinkedList<>();
        gamePane = new Pane();
        Xmax = CONSTANTES.GAME_WIDTH - ANIMAL_WIDTH;
        Ymax = CONSTANTES.GAME_HEIGHT - ANIMAL_HEIGHT;
        gamePane.setStyle("-fx-background-image: url('"+ routeImage +"');"
                + "-fx-background-repeat: stretch;"
                + "-fx-background-size: "+CONSTANTES.GAME_WIDTH+" "+(CONSTANTES.GAME_HEIGHT)+"; "
                + "-fx-background-position: center center;");
        gamePane.setPrefSize(CONSTANTES.GAME_WIDTH, CONSTANTES.GAME_HEIGHT);
        
        borders = new LinkedList<>();
        enemies = new LinkedList<>();
        health = new LinkedList<>();
        this.animal = animal;
        moveKeyEvent();
        isComida = true;
        isEnemigo = true;
        generarLetrero();
        generarLimites();
        generarEnemigos();
        generarVidas();
        loadElementsPane();
        isEvolve();
        getInfo();
        changeInfo();
    }
    
    abstract void generarLimites();
    abstract void generarEnemigos();
    abstract Message createMessageFood();
    abstract Message createMessageEnemy();
    abstract void generarVidas();
    abstract void generarLetrero();
    abstract boolean isObject();
    abstract void isEvolve();
    public abstract VBox buildInformation();
    
    //abstract boolean isChangeMapUp(double x, double y);
    abstract boolean isChangeMapDown(double x, double y);
    abstract boolean isChangeMapLeft(double x, double y);
    //abstract boolean isChangeMapRight(double x, double y);
    
    protected Shape generarBorde(double x, double y, double ancho , double largo){
        Rectangle r = new Rectangle(x,y, ancho, largo);
        //TODO: cambiar a transparente los limites
        //r.setFill(Color.TRANSPARENT);
        return r;
    }
    
    private void moveKeyEvent(){
        ProyectoJuego.escena.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:
                     
                    Double y1 = animal.getPosicionY() - CONSTANTES.DELTA_DESPLAZAMIENTO;
                    if(y1 < Ymax &&  y1 > 0){
                        animal.fijarPosicionObjeto(animal.getPosicionX(), y1);
                        
                    }
                    colisionDetector(animal.getPosicionX(), y1 + DELTA_DESPLAZAMIENTO + 8);

                    break;
                case DOWN:
                    Double y2 = animal.getPosicionY() + CONSTANTES.DELTA_DESPLAZAMIENTO;
                    if(y2 < Ymax && y2 >0){
                        animal.fijarPosicionObjeto(animal.getPosicionX(), y2);
                    }
                    colisionDetector(animal.getPosicionX(), y2 - DELTA_DESPLAZAMIENTO - 8);
                    isChangeMapDown(animal.getPosicionX(), y2);
                    break;
                    
                case RIGHT:
                    Double x1 = animal.getPosicionX() + CONSTANTES.DELTA_DESPLAZAMIENTO;
                    if(x1 < Xmax &&  x1 > 0){
                        animal.fijarPosicionObjeto(x1, animal.getPosicionY());
                    }
                    colisionDetector(x1 - DELTA_DESPLAZAMIENTO - 8 , animal.getPosicionY());
                    break;
                case LEFT:
                    Double x2 = animal.getPosicionX() - CONSTANTES.DELTA_DESPLAZAMIENTO;
                    if(x2 < Xmax &&  x2 > 0){
                        animal.fijarPosicionObjeto(x2, animal.getPosicionY());
                    }
                    colisionDetector(x2 + DELTA_DESPLAZAMIENTO + 8 , animal.getPosicionY());
                    isChangeMapLeft(x2, animal.getPosicionY());
            }
        });
    }
    
    public Pane getRoot() {
        return gamePane;
    }
    
    private void colisionDetector(double x, double y){
        if(isCollision(health)){
            if(isComida){
                isComida = false;
                PopOut pop = new PopMessage(ProyectoJuego.stageMain, createMessageFood(), "Comida");
            }
        }if(isHurtingEnemy()){
            proyectojuego.MainPane.vida_Animal--;
            proyectojuego.ProyectoJuego.pj.generateHearts();
            if(isEnemigo){
                isEnemigo = false;
                PopOut pop = new PopMessage(ProyectoJuego.stageMain, createMessageEnemy(), "Enemigos");
            }
            animal.fijarPosicionObjeto(x,y);
        }
        isObject();
    }
    
    protected final void loadElementsPane(){
        gamePane.getChildren().addAll(borders);
        gamePane.getChildren().addAll(getNodesEnemies());
        gamePane.getChildren().addAll(animal.getObjeto());
        gamePane.getChildren().addAll(health);
    }
    
    private List<ImageView> getNodesEnemies(){
        LinkedList<ImageView> enemyNodes = new LinkedList<>();
        for(Enemigo e: enemies){
            enemyNodes.add(e.getObjeto());
        }
        return enemyNodes;
    }
    
    public boolean changeMap(MapPane map){
        MainPane.root.setCenter(map.gamePane);
        return true;
    }
    
    public static boolean isCollision(Node n1, Node n2){
        Bounds b1 = n1.getBoundsInParent();
        Bounds b2 = n2.getBoundsInParent();
        return b1.intersects(b2);
    }
    
    protected boolean isCollision(List<ImageView> im){
        for(ImageView i :im){
            if(isCollision(i, animal.getObjeto())){
                if(proyectojuego.MainPane.vida_Animal < 10){
                    im.remove(i);
                    gamePane.getChildren().remove(i);
                    proyectojuego.MainPane.vida_Animal++;
                    proyectojuego.ProyectoJuego.pj.generateHearts();
                }
                return true;
            }
        }
        return false;
    }
    
    protected boolean isHurtingEnemy(){
        for(Enemigo e: enemies){
            if(isCollision(e.getObjeto(), animal.getObjeto())){
                MainPane.setEnemigoKIll(e);
                return true;
            }
        }
        return false;
    }

    public List<Enemigo> getEnemies() {
        return enemies;
    }
    
    public void getInfo(){
        gamePane.setOnMouseClicked(event ->{
            System.out.println("X: "+ event.getX() + "Y:" + event.getY());
        });
    }
    
     protected ImageView generarImagen(double x, double y, String ruta){
        ImageView i = new ImageView(new Image(getClass().getResourceAsStream(ruta),
                                100,
                                100,
                                true,
                                true));
        i.setLayoutX(x);
        i.setLayoutY(y);
        return i;
    }
     
    private void changeInfo(){
        MainPane.root.setBottom(buildInformation());
    }
}
