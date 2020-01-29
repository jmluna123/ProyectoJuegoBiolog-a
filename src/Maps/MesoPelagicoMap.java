package Maps;

import static Utilitarios.CONSTANTES.RUTA_IM_MESO;
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
public class MesoPelagicoMap extends MapPane {

    public MesoPelagicoMap(double x, double y) {
        super(new Animal(RUTA_IM_MESO + "crab.png"),RUTA_IM_MESO + "meso.PNG");
        animal.fijarPosicionObjeto(x, y);
    }

    @Override
    protected final void generarLimites() {
        //TODO
    }

    @Override
    protected final void generarEnemigos() {
         Message m1 = new Message("¡HAS SIDO DEVORADO!",""
            , RUTA_IM_MESO + "crab-octo.jpg");
        Message m2 = new Message("¡HAS SIDO DEVORADO!",""
            , RUTA_IM_MESO + "eel-crab.png");
        Message m3 = new Message("¡HAS SIDO DEVORADO!",""
            , RUTA_IM_MESO + "crab-fish.jpg");
                
        super.enemies.add(new Enemigo(RUTA_IM_MESO + "MP8_Blooper.png", 913,223, m1));
        super.enemies.add(new Enemigo(RUTA_IM_MESO + "eel.png", 165,72, m2));
        super.enemies.add(new Enemigo(RUTA_IM_MESO + "pez.png",725,121 , m3));
        super.enemies.add(new Enemigo(RUTA_IM_MESO + "eel.png", 382,327, m2));
        super.enemies.add(new Enemigo(RUTA_IM_MESO + "eel.png", 470,60, m2));
        super.enemies.add(new Enemigo(RUTA_IM_MESO + "pez.png",232, 397, m3));
        super.enemies.add(new Enemigo(RUTA_IM_MESO + "MP8_Blooper.png", 731,224, m1));
    }

    @Override
    boolean isChangeMapDown(double x, double y) {
        return false;
    }
     @Override
    boolean isChangeMapLeft(double x, double y) {
        if( y >= 267 && x <= 0){
            super.changeMap(new BatiPelagicoMap(967, 261));
            return true;     
        }
        return false;
    }
    
    @Override
    Message createMessageFood() {
        return new Message("VIDA EXTRA"
            , "¡Zooooooooooooplancton para \n"
            + "hacer crecer las tenazas :3! \n"
            + "Me voy a carroñear.",
        RUTA_IM_MESO + "alimentos.png");

    }

    @Override
    Message createMessageEnemy() {
        return new Message("TE ATACAN!"
            , "Peces grandes…Anguilas…Calamares…\n"
            + "No debería acercarme o me comerán!!\n"
            + "Has perdido 1 corazón.",
        RUTA_IM_MESO + "enemigos.png");
    }

    @Override
    protected final boolean isObject() {
        return false;
    }

    @Override
    protected final void generarVidas() { 
        health.add(generarImagen(308,200, RUTA_IM_MESO+ "Anguila.png"));
        health.add(generarImagen(558, 299, RUTA_IM_MESO+ "main.png"));
        health.add(generarImagen(567, 393, RUTA_IM_MESO+ "Anguila.png"));
    }
    
     @Override
    void generarLetrero() {
        ImageView im = new ImageView(new Image(getClass().getResourceAsStream(RUTA_IM_MESO + "letrero.png"),
                                140,
                                170,
                                true,
                                true));
        im.setLayoutX(30);
        im.setLayoutY(390);
        gamePane.getChildren().addAll( im);
    }
    
    @Override
    void isEvolve() {
        PopOut pop = new PopMessage(ProyectoJuego.stageMain, 
        new Message("HAS EVOLUCIONADO!",
                 "Has descubierto la zona mesopelágica\n"
               + "Tuviste que adaptarte al nuevo \n"
               + "clima para sobrevivir!.       \n"
               + " > Mayor tamaño de Tenaza\n"
               + " > Ojos más grandes\n"
               + " > Producción de óxido de trimetalina\n"
               + "   para presiones altas\n", RUTA_IM_MESO+ "crab.png"), "Evolución");
            
    }
    
    @Override
    public VBox buildInformation() {
        VBox main = new VBox();
        main.setPadding(new Insets(10, 20, 20, 20));
        main.setSpacing(10);
        Label titulo = new Label("Zona Mesopelágica:");
        titulo.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt");
        main.getChildren().add(titulo);
        
        HBox grids = new HBox();
        grids.setSpacing(20);
        GridPane g1 = new GridPane();
        g1.add(new Label("Profundidad: "), 0, 0);
        g1.add(new Label("Desde los 200 metros hasta 1000 metros (Arenas, y otros, 2008)"), 1, 0);
        g1.add(new Label("Salinidad: "), 0, 1);
        g1.add(new Label("(34.5 a 35.5 g/L)  (Graco, Ledesma, Flores, & Girón, 2007)"), 1, 1);
        g1.add(new Label("Presencia Luz: "), 0, 2);
        g1.add(new Label("Poca luz (escasa fotosíntesis)"), 1, 2);
        main.getChildren().add(g1);
        
        GridPane g2 = new GridPane();
        g2.add(new Label("Oxígeno Disuelto: "), 0, 0);
        g2.add(new Label("0 a 0,5 g/L (Lizano, s.f.)"), 1, 0);
        g2.add(new Label("Temperatura: "), 0, 1);
        g2.add(new Label("5 a 9°C (Lizano, s.f.)"), 1, 1);
        grids.getChildren().addAll(g1,g2);
        main.getChildren().add(grids);
        
        return main;
    }
}
