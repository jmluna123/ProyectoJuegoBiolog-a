/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maps;

import Utilitarios.CONSTANTES;
import static Utilitarios.CONSTANTES.RUTA_IM_BATI;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mainCharacters.Animal;
import mainCharacters.Enemigo;
import popOut.Message;
import popOut.PopMessage;
import popOut.PopOut;
import proyectojuego.ProyectoJuego;

/**
 *
 * @author josie
 */
public class BatiPelagicoMap extends MapPane {
    public BatiPelagicoMap(double x, double y) {
        super(new Animal(RUTA_IM_BATI + "crab.png"),RUTA_IM_BATI + "zona-batial.png");
        animal.fijarPosicionObjeto(x, y);
    }

    @Override
    protected final void generarLimites() {
        //TODO
    }

    @Override
    protected final void generarEnemigos() {
        Message m1 = new Message("¡HAS SIDO DEVORADO!",""
            , RUTA_IM_BATI + "avisal.jpg");
        Message m2 = new Message("¡HAS SIDO DEVORADO!",""
            , RUTA_IM_BATI + "crab-octo.jpg");
        Message m3 = new Message("¡HAS SIDO DEVORADO!",""
            , RUTA_IM_BATI + "crab-fish.jpg");
                
        super.enemies.add(new Enemigo(RUTA_IM_BATI + "pez.png", 768,133, m3));
        super.enemies.add(new Enemigo(RUTA_IM_BATI + "a.png", 606,161, m1));
        super.enemies.add(new Enemigo(RUTA_IM_BATI + "pulpo.png", 447,151, m2));
        super.enemies.add(new Enemigo(RUTA_IM_BATI + "pez.png", 285,180, m3));
        super.enemies.add(new Enemigo(RUTA_IM_BATI + "pez2.png", 233,357, m3));
        super.enemies.add(new Enemigo(RUTA_IM_BATI + "a.png", 368,418, m1));
    }

    @Override
    boolean isChangeMapDown(double x, double y) {
        return false;
    }
     @Override
    boolean isChangeMapLeft(double x, double y) {
        if( y >= 267 && x <= 0){
            //E[clicked] X: 981.0,Y: 404.0.
            super.changeMap(new AbisoPelagicoMap(981, 304));
            return true;     
        }
        return false;
    }
    @Override
    Message createMessageFood() {
        return new Message("VIDA EXTRA"
            , "Quien necesita una hembra, si \n"
            + "tienes bacterias en detrito \n"
            + " :3 Has regenerado 1 corazón.",
        RUTA_IM_BATI + "alimentos.png");

    }

    @Override
    Message createMessageEnemy() {
        return new Message("TE ATACAN!"
            , "Estos animales son agresivos, \n"
            + "pareciera que soy la única comida\n"
            + "aquí… Ten cuidado o te comerán. \n"
            + " Has perdido 1 corazón.",
        RUTA_IM_BATI + "enemigos.png");
    }

    @Override
    protected final boolean isObject() {
        return false;
    }

    @Override
    protected final void generarVidas() {   
        health.add(generarImagen(509, 417, RUTA_IM_BATI+ "variety-of-nanoparticles.png"));
        health.add(generarImagen(209, 147, RUTA_IM_BATI+ "1883951.png"));
        health.add(generarImagen(753, 407, RUTA_IM_BATI+ "variety-of-nanoparticles.png"));
    }
    
     @Override
    void generarLetrero() {
        ImageView im = new ImageView(new Image(getClass().getResourceAsStream(RUTA_IM_BATI + "letrero.png"),
                                140,
                                170,
                                true,
                                true));
        im.setLayoutX(30);
        im.setLayoutY(355);
        gamePane.getChildren().addAll( im);
    }
    
    @Override
    void isEvolve() {
        PopOut pop = new PopMessage(ProyectoJuego.stageMain, 
        new Message("HAS EVOLUCIONADO!",
                 "Has descubierto la zona batial\n"
               + "Tuviste que adaptarte al nuevo \n"
               + "clima para sobrevivir!.       \n"
               + " > Disminuye tasa metabólica\n"
               + " > Vejiga Flotadora\n"
               + " > No necesitan ojos", RUTA_IM_BATI+ "crab.png"), "Evolución");
        CONSTANTES.DELTA_DESPLAZAMIENTO = 6;    
    }
    @Override
    public VBox buildInformation() {
        VBox main = new VBox();
        main.setPadding(new Insets(10, 20, 20, 20));
        main.setSpacing(10);
        Label titulo = new Label("Zona Batial:");
        titulo.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt");
        main.getChildren().add(titulo);
        
        HBox grids = new HBox();
        grids.setSpacing(20);
        GridPane g1 = new GridPane();
        g1.add(new Label("Profundad:"), 0, 0);
        g1.add(new Label("Desde los 1000 metros hasta 4000 metros (Arenas, y otros, 2008)"), 1, 0);
        g1.add(new Label("Salinidad: "), 0, 1);
        g1.add(new Label("34.7 g/L (Graco, Ledesma, Flores, & Girón, 2007)"), 1, 1);
        g1.add(new Label("Presión Hidrostática: "), 0, 2);
        g1.add(new Label("Elevada (función lineal de 10m equivale 1atm) (Carney, 2005)"), 1, 2);
        main.getChildren().add(g1);
        
        GridPane g2 = new GridPane();
        g2.add(new Label("Oxígeno Disuelto: "), 0, 0);
        g2.add(new Label("alrededor 5 g/L"), 1, 0);
        g2.add(new Label("Presencia Luz: "), 0, 1);
        g2.add(new Label("No hay luz, por ende no hay productividad primaria."), 1, 1);
        g2.add(new Label("Temperatura: "), 0, 2);
        g2.add(new Label("0 a 5°C (Lizano, s.f.)"), 1, 2);
        grids.getChildren().addAll(g1,g2);
        main.getChildren().add(grids);
        
        return main;
    }
}
