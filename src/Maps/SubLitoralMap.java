/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maps;

import static Utilitarios.CONSTANTES.RUTA_IM_SUB;
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
public class SubLitoralMap extends MapPane {
    
    public SubLitoralMap(double x, double y) {
        super(new Animal(RUTA_IM_SUB + "crab.png"),RUTA_IM_SUB + "asub.PNG");
        animal.fijarPosicionObjeto(x, y);
    }

    @Override
    protected final void generarLimites() {
        //TODO
    }

    @Override
    protected final void generarEnemigos() {
        Message m1 = new Message("¡HAS SIDO DEVORADO!",""
            , RUTA_IM_SUB + "crab-fish.jpg");
             
        super.enemies.add(new Enemigo(RUTA_IM_SUB + "pez1.png", 209,206, m1));
        super.enemies.add(new Enemigo(RUTA_IM_SUB + "pez2.png", 182,411, m1));
        super.enemies.add(new Enemigo(RUTA_IM_SUB + "pez1.png", 517,313, m1));
        super.enemies.add(new Enemigo(RUTA_IM_SUB + "pez2.png", 717,107, m1));
        super.enemies.add(new Enemigo(RUTA_IM_SUB + "pez2.png", 878,392, m1));
    }

     @Override
    boolean isChangeMapDown(double x, double y) {
        return false;
    }
    
    @Override
    boolean isChangeMapLeft(double x, double y) {
        if( y >= 374 && x <= 0){
            super.changeMap(new MesoPelagicoMap(971, 361));
            return true;     
        }
        return false;
    }
    
    @Override
    Message createMessageFood() {
        return new Message("VIDA EXTRA"
            , "¡¡Ese plancton se ve muy apetitoso,\n"
            + "al igual que ese animal muerto :D!!\n"
            + " Has regenerado 1 corazón.",
        RUTA_IM_SUB + "alimentos.png");
    }

    @Override
    Message createMessageEnemy() {
        return new Message("TE ATACAN!"
            , "Esos peces parecen hambrientos…\n"
            + "Me están viendo….. Ehm….. Mejor\n"
            + " no me acerco Has perdido 1 corazón.",
        RUTA_IM_SUB + "enemigos.png");
    }

    @Override
    protected final boolean isObject() {
        return false;
    }

    @Override
    protected final void generarVidas() {  
        health.add(generarImagen(384, 313, RUTA_IM_SUB+ "alimento1.png"));
        health.add(generarImagen(645, 388, RUTA_IM_SUB+ "alimento2.jpg"));
        health.add(generarImagen(778, 246, RUTA_IM_SUB+ "alimento1.png"));
    }

    @Override
    void generarLetrero() {
        ImageView im = new ImageView(new Image(getClass().getResourceAsStream(RUTA_IM_SUB + "letrero.png"),
                                140,
                                170,
                                true,
                                true));
        im.setLayoutX(30);
        im.setLayoutY(396);
        gamePane.getChildren().addAll( im);
    }
    
    @Override
    void isEvolve() {
        PopOut pop = new PopMessage(ProyectoJuego.stageMain, 
        new Message("HAS EVOLUCIONADO!",
                 "Has descubierto la zona sublitoral\n"
               + "Tuviste que adaptarte al nuevo \n"
               + "clima para sobrevivir!.       \n"
               + " > Cambio de color al ambiente\n"
               + " > Gana Tamaño", RUTA_IM_SUB+ "crab.png"), "Evolución");
            
    }
    @Override
    public VBox buildInformation() {
        VBox main = new VBox();
        main.setPadding(new Insets(10, 20, 20, 20));
        main.setSpacing(10);
        Label titulo = new Label("Zona Sublitoral:");
        titulo.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt");
        main.getChildren().add(titulo);
        
        HBox grids = new HBox();
        grids.setSpacing(20);
        GridPane g1 = new GridPane();
        g1.add(new Label("Profundidad: "), 0, 0);
        g1.add(new Label("Desde los 0 metros hasta 200 metros (Arenas, y otros, 2008)"), 1, 0);
        g1.add(new Label("Salinidad: "), 0, 1);
        g1.add(new Label("(35.5 a 36 g/L) (Graco, Ledesma, Flores, & Girón, 2007)"), 1, 1);
        g1.add(new Label("Productividad: "), 0, 2);
        g1.add(new Label("Alta Productividad primaria"), 1, 2);
        main.getChildren().add(g1);
        
        GridPane g2 = new GridPane();
        g2.add(new Label("Oxígeno Disuelto: "), 0, 0);
        g2.add(new Label("0,5 a 5 g/L (Lizano, s.f.)"), 1, 0);
        g2.add(new Label("Temperatura: "), 0, 1);
        g2.add(new Label(" 9 a 20°C (Lizano, s.f.)"), 1, 1);
        grids.getChildren().addAll(g1,g2);
        main.getChildren().add(grids);
        return main;
    }
}
