/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainCharacters;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static Utilitarios.CONSTANTES.*;

/**
 *
 * @author Jocellyn Luna
 */
public class Animal {
    private ImageView animal;
    private String nombre;
    private String ruta;
    
    public Animal(String ruta){
        //creamos a imageView del animal
        //agregar la imagen
        //creando la imagen, le pasamos el ancho, el alto, si queremos mantener el 
        //radio entre el ancho y el alto y que s
        Image img = new Image(getClass().getResourceAsStream(ruta),
                                ANIMAL_WIDTH,
                                ANIMAL_HEIGHT,
                                true,
                                true);
        //agrega imagen al imageView
        animal = new ImageView(img);
        this.ruta =ruta;
    }
    
    public void fijarPosicionObjeto(double x, double y){
        //fija la poscion de x con respecto a X y Y usando 
        animal.setLayoutX(x);
        animal.setLayoutY(y);
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
