/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectojuego;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import static proyectojuego.CONSTANTES.*;

/**
 *
 * @author Jocellyn Luna
 */
public class PaneInicio { 
    private Rectangle pieza_colision;
       
    private final BorderPane root;
    private Label marcador;
    private int points = 0;
    private Pane gamePane;
    private HBox vida;
    
    //animal
    private Animal animal;
    private double initX;
    private double initY;
    private double Xmax;
    private double Ymax;
    
    //piezas de la nave
    private ArrayList<Shape> piezas = new ArrayList<>();
    
    public PaneInicio(){
        root = new BorderPane();
        gamePane = new Pane();
        vida = new HBox();
        marcador = new Label(String.valueOf(points));
        //creamos el animal
        animal = new Animal(this);
        gamePane.getChildren().add(animal.getObjeto());
        
        pieza_colision = new Rectangle(animal.getPosicionX(), animal.getPosicionY(),90,70);
        
        initX = 0;
        initY = 0;
        
        root.setTop(createTop());
        root.setBottom(buildControllers());
        root.setCenter(createCenter());
    }
    
    private AnchorPane createTop(){
        //creamos un objeto de tipo FONT
        //esto se hace si se quiere cambiar el tipo y tamano de letra usado 
        //en un label
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
    
    private HBox buildControllers(){
        HBox fondo = new HBox();
        Button start = new Button("Iniciar");
        
        fondo.getChildren().addAll(start);
        fondo.setSpacing(20);
        fondo.setAlignment(Pos.CENTER);
        
        
        return fondo;
    }
    
    private Pane createCenter(){
        //usamos como contenedor un Pane porque queremos colocar los elementos
        //de forma libre a los largo del Pane
        gamePane.setStyle("-fx-background-image: url('"+CONSTANTES.RUTA_IMAGENES+"imagen_fondo1.png');"
                + "-fx-background-repeat: stretch;"
                + "-fx-background-size: "+CONSTANTES.GAME_WIDTH+" "+(CONSTANTES.GAME_HEIGHT)+"; "
                + "-fx-background-position: center center;");
        gamePane.setPrefSize(CONSTANTES.GAME_WIDTH, CONSTANTES.GAME_HEIGHT);
        
        moveKeyEvent();
        generarBordes();
        generarEnemigos();
        
        //TODO: fijar al animal en un punto exacto, no al azar
        //generar x , y de forma aletorio
        Xmax = CONSTANTES.GAME_WIDTH - ANIMAL_WIDTH;
        Ymax = CONSTANTES.GAME_HEIGHT - ANIMAL_HEIGHT;
        
        return gamePane;
    }
    
    private void generarBordes(){
        //2,3 cm es -> 100
        generarBorde(143.48,0, 586.96, 90);
        generarBorde(143.48, 185, 586, 80);
    }
    
    private void generarBorde(double x, double y, double ancho , double largo){
        Rectangle p1 = new Rectangle(x,y, ancho, largo);
        //p1.setFill(Color.TRANSPARENT);
        gamePane.getChildren().add(p1);
        piezas.add(p1);
    }
    
    private void generarEnemigos(){
        Enemigo e = new Enemigo("gaviota.png", 120, 350, 1000, false);
        gamePane.getChildren().add(e.getObjeto());
    }
    
    /**
     * el animal se pueda mover a traves de la pantalla
     */
    public void moveKeyEvent(){
        ProyectoJuego.escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
                animal.moveAnimal(event);
            }
        });
    }
    
    public boolean isCollision(int type){
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
        Bounds b1 = pieza_colision.getBoundsInParent();
        for(Shape s: piezas){
            Bounds b2 = s.getBoundsInParent();
            if(b1.intersects(b2))
                return true;
        }
        return false;
    }
    
    public Pane getRoot(){
        return root;
    }
    
    private void hurt(){
        
    }
    
    private List<ImageView> calculateHearts(){
        LinkedList<ImageView> hearts = new LinkedList<>();
        int count = Animal.vida;
        
        for(int i = 0; i < count; i++){
            hearts.add(obtainImage("heart_complete.png"));
        }
        if(count < 0){
            count = 0;
        }
        for(int i = 0; i < VIDA - count; i++){
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
    
    public Rectangle getPieza_colision() {
        return pieza_colision;
    }

    public double getXmax() {
        return Xmax;
    }

    public double getYmax() {
        return Ymax;
    }
}
