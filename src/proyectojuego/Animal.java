/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojuego;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import static proyectojuego.CONSTANTES.*;

/**
 *
 * @author Jocellyn Luna
 */
public class Animal {
    public ImageView animal;
    private PaneInicio root;
    private String nombre;
    private String ruta;
    public static int vida = VIDA;
    
    public Animal(PaneInicio root){
        //creamos a imageView del animal
        //agregar la imagen
        //creando la imagen, le pasamos el ancho, el alto, si queremos mantener el 
        //radio entre el ancho y el alto y que s
        Image img = new Image(getClass().getResourceAsStream(
                        RUTA_IMAGENES+ ANIMAL_ROUTE),
                                ANIMAL_WIDTH,
                                ANIMAL_HEIGHT,
                                true,
                                true);
        //agrega imagen al imageView
        animal = new ImageView(img);
        this.root = root;
    }
    
    public void fijarPosicionObjeto(double x, double y){
        //fija la poscion de x con respecto a X y Y usando 
        animal.setLayoutX(x);
        animal.setLayoutY(y);
    }
    
    public void moveAnimal(KeyEvent event){
        switch (event.getCode()) {
            case UP:    
                if(!root.isCollision(0)){

                    Double y1 = getPosicionY() - CONSTANTES.DELTA_DESPLAZAMIENTO;
                    if(y1 < root.getYmax() &&  y1 > 0){
                        fijarPosicionObjeto(getPosicionX(), y1);
                        vida--;
                        root.generateHearts();
                    }
                }
                break;
            case DOWN: 
               if(!root.isCollision(1)){
                    Double y2 = getPosicionY() + CONSTANTES.DELTA_DESPLAZAMIENTO;
                    if(y2 < root.getYmax() &&  y2 > 0){
                        fijarPosicionObjeto(getPosicionX(), y2);
                    }
                }
                break;

            case RIGHT:  
                if(!root.isCollision(2)){
                    Double x1 = getPosicionX() + CONSTANTES.DELTA_DESPLAZAMIENTO;
                    if(x1 < root.getXmax() &&  x1 > 0){
                    fijarPosicionObjeto(x1, getPosicionY());
                    }
                }
                break;
            case LEFT:
                if(!root.isCollision(3)){
                    Double x2 = getPosicionX() - CONSTANTES.DELTA_DESPLAZAMIENTO;
                    if(x2 < root.getXmax() &&  x2 > 0){
                        fijarPosicionObjeto(x2, getPosicionY());
                    }
                }
        }
    }
    
    public double getPosicionX(){
        return animal.getLayoutX();
    }
    
    public double getPosicionY(){
        return animal.getLayoutY();
    }
    
    public Node getObjeto(){
        return animal;
    }  
}
