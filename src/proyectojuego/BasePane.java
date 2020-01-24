/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojuego;


import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Jocellyn Luna
 */
public class BasePane {
    Pane gamePane;
    private Rectangle colision_pieces;
    List<Shape>borders;
    List<ImageView> enemies;
    Animal animal;
    double Xmax;
    double Ymax;
    
    public BasePane(String routeImage){
        gamePane = new Pane();
        gamePane.setStyle("-fx-background-image: url('"+CONSTANTES.RUTA_IMAGENES+"imagen_fondo1.png');"
                + "-fx-background-repeat: stretch;"
                + "-fx-background-size: "+CONSTANTES.GAME_WIDTH+" "+(CONSTANTES.GAME_HEIGHT)+"; "
                + "-fx-background-position: center center;");
        gamePane.setPrefSize(CONSTANTES.GAME_WIDTH, CONSTANTES.GAME_HEIGHT);
        
        Xmax = CONSTANTES.GAME_WIDTH - 70;
        Ymax = CONSTANTES.GAME_HEIGHT - 90;
    }
    
    /**
     * el animal se pueda mover a traves de la pantalla
     */
    public void moverAnimal(){
        ProyectoJuego.escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    
                        if(!isCollision(0)){
                            
                            Double y1 = animal.getPosicionY() - CONSTANTES.DELTA_DESPLAZAMIENTO;
                            if(y1 < Ymax &&  y1 > 0){
                                animal.fijarPosicionObjeto(animal.getPosicionX(), y1);
                            }
                        }
                        break;
                    case DOWN: 
                       if(!isCollision(1)){
                            Double y2 = animal.getPosicionY() + CONSTANTES.DELTA_DESPLAZAMIENTO;
                            animal.fijarPosicionObjeto(animal.getPosicionX(), y2);
                        }
                        break;
                    
                    case RIGHT:  
                        if(!isCollision(2)){
                            Double x1 = animal.getPosicionX() + CONSTANTES.DELTA_DESPLAZAMIENTO;
                            if(x1 < Xmax &&  x1 > 0){
                            animal.fijarPosicionObjeto(x1, animal.getPosicionY());
                            }
                        }
                        break;
                    case LEFT:
                        if(!isCollision(3)){
                            Double x2 = animal.getPosicionX() - CONSTANTES.DELTA_DESPLAZAMIENTO;
                            if(x2 < Xmax &&  x2 > 0){
                                animal.fijarPosicionObjeto(x2, animal.getPosicionY());
                            }
                        }
                }
            }
        });
    }
    
    public boolean isCollision(int type){
        switch(type){
            case 0: //UP
                colision_pieces.setLayoutX(animal.getPosicionX());
                colision_pieces.setLayoutY(animal.getPosicionY()-10);
                break;
            case 1: //DOWN
                colision_pieces.setLayoutX(animal.getPosicionX());
                colision_pieces.setLayoutY(animal.getPosicionY()+10);
                break;
            case 2: //RIGHT
                colision_pieces.setLayoutX(animal.getPosicionX()+10);
                colision_pieces.setLayoutY(animal.getPosicionY());
                break;
            case 3: //LEFT
                colision_pieces.setLayoutX(animal.getPosicionX()-10);
                colision_pieces.setLayoutY(animal.getPosicionY());
                break;
        }
        Bounds b1 = colision_pieces.getBoundsInParent();
        for(Shape s: borders){
            Bounds b2 = s.getBoundsInParent();
            if(b1.intersects(b2))
                return true;
        }
        return false;
    }
    
    public void setEmenents(List<Shape>borders, List<ImageView> enemies, Animal animal){
        colision_pieces = new Rectangle(animal.getPosicionX(), animal.getPosicionY(),90,70);
        this.borders = borders;
        this.enemies = enemies;
        this.animal = animal;
    }
    
    public Pane getRoot(){
        return gamePane;
    }

    public Rectangle getColision_pieces() {
        return colision_pieces;
    }

    public List<Shape> getBorders() {
        return borders;
    }

    public List<ImageView> getEnemies() {
        return enemies;
    }

    public Animal getAnimal() {
        return animal;
    }
    
    
}
