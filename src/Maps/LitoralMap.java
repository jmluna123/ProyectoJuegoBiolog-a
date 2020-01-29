package Maps;

import Utilitarios.CONSTANTES;
import static Utilitarios.CONSTANTES.RUTA_IM_LITO;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mainCharacters.Animal;
import mainCharacters.Casa;
import mainCharacters.Enemigo;
import popOut.*;
import proyectojuego.ProyectoJuego;

/**
 *
 * @author josie
 */
public class LitoralMap extends MapPane {
    private boolean isObjeto;
    
    public LitoralMap(double x, double y) {
        super(new Animal(RUTA_IM_LITO + "crab.png"),RUTA_IM_LITO + "lito.PNG");
        isObjeto = true;
        animal.fijarPosicionObjeto(x, y);
    }

    @Override
    protected void generarLetrero(){
        Casa c1 = new Casa(981,333, RUTA_IM_LITO + "hueco-png-1.png");
        objetos.add(c1);
        Casa c2 = new Casa(342,422, RUTA_IM_LITO + "hueco-png-1.png");
        objetos.add(c2);
        ImageView im = new ImageView(new Image(getClass().getResourceAsStream(RUTA_IM_LITO + "letrero.png"),
                                140,
                                170,
                                true,
                                true));
        im.setLayoutX(56);
        im.setLayoutY(360);
        
        gamePane.getChildren().addAll(c1.getObjeto(), c2.getObjeto(), im);
    }
    
    @Override
    protected final void generarLimites() {
        //TODO
    }

    @Override
    protected final void generarEnemigos() {
        Message m1 = new Message("¡HAS SIDO DEVORADO!",""
            , RUTA_IM_LITO + "crab-crab.png");
                
        super.enemies.add(new Enemigo(RUTA_IM_LITO + "elcangrejodespistado.gif", 313,285, m1));
        super.enemies.add(new Enemigo(RUTA_IM_LITO + "elcangrejodespistado.gif",653,240, m1));

    }

    @Override
    boolean isChangeMapDown(double x, double y) {    
        if( y >= Ymax && x < 240){
            super.changeMap(new SubLitoralMap(951, 237));
            return true;     
        }
        return false;
    }
    
    @Override
    Message createMessageFood() {
        return new Message("VIDA EXTRA"
            ,"¡¡¡¡¡Que rico!!!!!, has encontrado \n"
            + "alimento. Te sientes fuerte, invencible\n"
            + "e independiente, continúa nutriéndote.\n"
            + "Has regenerado 1 corazón.",
        RUTA_IM_LITO + "pez_muerto.png");

    }

    @Override
    Message createMessageEnemy() {
        return new Message("TE ATACAN!"
            , "No estoy en una novela para que\n"
            + " me ataque mi propia familia :c\n"
            + "A correr!!!Has perdido 1 corazón.",
        RUTA_IM_LITO + "elcangrejodespistado.gif");
    }
    
    @Override
    protected final boolean isObject() {
        for(Casa c: objetos){
            if(isCollision(c.getObjeto(), animal.getObjeto()) && !c.getVisited()){
                if(proyectojuego.MainPane.vida_Animal <10){
                    proyectojuego.MainPane.vida_Animal++;
                    proyectojuego.ProyectoJuego.pj.generateHearts();
                    c.setVisited(true);
                }if(isObjeto){
                    Message m = new Message("REFUGIOS"
                        , "En la zona Litoral los cangrejos\n"
                        + "se esconden en grietas o huecos.\n"
                        + "Descansa y regenera un corazón.",
                            RUTA_IM_LITO + "hueco-png-1.png");
                    PopOut pop = new PopMessage(ProyectoJuego.stageMain, m, "Refugios");
                    isObjeto = false;
                }
            }
        }
        return false;
    }

    @Override
    protected final void generarVidas() {   
        health.add(generarImagen(787, 415, RUTA_IM_LITO+ "pez_muerto.png"));
        health.add(generarImagen(781, 150, RUTA_IM_LITO+ "pez_muerto.png"));
    }

    @Override
    boolean isChangeMapLeft(double x, double y) {
        return false;
    }

    @Override
    void isEvolve() {
        PopOut pop = new PopMessage(ProyectoJuego.stageMain, 
        new Message("HAS EVOLUCIONADO!",
                 "Has descubierto la zona litoral\n"
               + "Tuviste que adaptarte al nuevo \n"
               + "clima para sobrevivir!.       \n"
               + " > Menos velocidad\n"
               + " > Más Tamaño", RUTA_IM_LITO+ "crab.png"), "Evolución");
        CONSTANTES.DELTA_DESPLAZAMIENTO = 10;
        CONSTANTES.ANIMAL_HEIGHT = CONSTANTES.ANIMAL_HEIGHT + CONSTANTES.GROWTH;
        CONSTANTES.ANIMAL_WIDTH = CONSTANTES.ANIMAL_WIDTH + CONSTANTES.GROWTH;
    }
    
    @Override
    public VBox buildInformation() {
        VBox main = new VBox();
        main.setPadding(new Insets(10, 20, 20, 20));
        main.setSpacing(10);
        Label titulo = new Label("Zona Litoral:");
        titulo.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt");
        main.getChildren().add(titulo);
        
        HBox grids = new HBox();
        grids.setSpacing(20);
        GridPane g1 = new GridPane();
        g1.add(new Label("Ubicación:"), 0, 0);
        g1.add(new Label("Entre Línea de Marea Alta y Línea de Marea Baja (Arenas, y otros, 2008)"), 1, 0);
        g1.add(new Label("Salinidad: "), 0, 1);
        g1.add(new Label("(35.5 a 36 g/L) (Graco, Ledesma, Flores, & Girón, 2007)"), 1, 1);
        g1.add(new Label("Interacción: "), 0, 2);
        g1.add(new Label("Antropogénicas"), 1, 2);
        main.getChildren().add(g1);
        
        GridPane g2 = new GridPane();
        g2.add(new Label("Oxígeno Disuelto: "), 0, 0);
        g2.add(new Label("alrededor 5 g/L"), 1, 0);
        g2.add(new Label("Marea: "), 0, 1);
        g2.add(new Label("Arrastre de Marea"), 1, 1);
        g2.add(new Label("Temperatura: "), 0, 2);
        g2.add(new Label("Ambiente mayor a 20°C"), 1, 2);
        grids.getChildren().addAll(g1,g2);
        main.getChildren().add(grids);
        
        return main;
    }
}
