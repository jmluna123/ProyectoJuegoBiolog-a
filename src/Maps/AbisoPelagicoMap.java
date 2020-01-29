package Maps;

import Utilitarios.CONSTANTES;
import static Utilitarios.CONSTANTES.RUTA_IM_ABISO;
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
import popOut.PopKill;
import popOut.PopMessage;
import popOut.PopOut;
import proyectojuego.ProyectoJuego;

/**
 *
 * @author josie
 */
public class AbisoPelagicoMap extends MapPane {
    ImageView cofre;
    public AbisoPelagicoMap(double x, double y) {
        super(new Animal(RUTA_IM_ABISO + "crab.png"),RUTA_IM_ABISO + "fondo-abisal.jpg");
        animal.fijarPosicionObjeto(x, y);
    }

    @Override
    protected final void generarLimites() {
        //TODO|
    }

    @Override
    protected final void generarEnemigos() {
        Message m1 = new Message("¡HAS SIDO DEVORADO!",""
            , RUTA_IM_ABISO + "crab-fish.jpg");
        Message m2 = new Message("¡HAS SIDO DEVORADO!",""
            , RUTA_IM_ABISO + "eel-crab.png");
        Message m3 = new Message("¡HAS SIDO DEVORADO!",""
            , RUTA_IM_ABISO + "avisal.jpg");
                
        super.enemies.add(new Enemigo(RUTA_IM_ABISO + "enemigo2.png", 972,143, m2));
        super.enemies.add(new Enemigo(RUTA_IM_ABISO + "enemigo4.png", 980,411, m3));
        super.enemies.add(new Enemigo(RUTA_IM_ABISO + "enemigo1.png", 825,145, m1));
        super.enemies.add(new Enemigo(RUTA_IM_ABISO + "enemigo1.png", 836,429, m1));
        super.enemies.add(new Enemigo(RUTA_IM_ABISO + "enemigo2.png", 605,83, m2));
        super.enemies.add(new Enemigo(RUTA_IM_ABISO + "enemigo3.png", 520,318, m2));
        super.enemies.add(new Enemigo(RUTA_IM_ABISO + "enemigo3.png", 625,408, m2));
        super.enemies.add(new Enemigo(RUTA_IM_ABISO + "enemigo1.png", 361,391, m1));
        super.enemies.add(new Enemigo(RUTA_IM_ABISO + "enemigo2.png", 210,391, m2));
        super.enemies.add(new Enemigo(RUTA_IM_ABISO + "enemigo4.png", 219,95, m3));
    }

    @Override
    Message createMessageFood() {
        return new Message("VIDA EXTRA"
            , "ADAPTARSE, SOBREVIVIR Y comer \n"
            + "=D… Has regenerado 1 corazón.",
        RUTA_IM_ABISO + "alimentos.png");

    }

    @Override
    Message createMessageEnemy() {
        return new Message("TE ATACAN!"
            , "No necesito ojos para saber que\n"
            + " me quieren en su estómago…   \n"
            + "Cuidado con los depredadores.  \n"
            + "Has perdido 1 corazón.",
        RUTA_IM_ABISO + "enemigos.png");
    }

    @Override
    protected final boolean isObject() {
        if(isCollision(cofre, animal.getObjeto())){
            PopKill pop = new PopKill(proyectojuego.ProyectoJuego.stageMain,
            new Message("HAS GANADO!!", "Has encontrado el Tesoro\n"
                                        + " en lo más profundo del \n"
                    + "océano.", RUTA_IM_ABISO+ "cofre-abierto.png"),"WIN" );
        }
        return false;
    }

    @Override
    protected final void generarVidas() {   
        health.add(generarImagen(170, 270, RUTA_IM_ABISO+ "comida3.png"));
    }
    
     @Override
    void generarLetrero() {
        cofre = new ImageView(new Image(getClass().getResourceAsStream(RUTA_IM_ABISO + "cofre.png"),
                                140,
                                170,
                                true,
                                true));
        cofre.setLayoutX(30);
        cofre.setLayoutY(355);
        gamePane.getChildren().addAll(cofre);
    }
    
    @Override
    boolean isChangeMapDown(double x, double y) {
        return false;
    }
    
    @Override
    boolean isChangeMapLeft(double x, double y) {
        return false;
    }
    
    @Override
    void isEvolve() {
        PopOut pop = new PopMessage(ProyectoJuego.stageMain, 
        new Message("HAS EVOLUCIONADO!",
                 "Has descubierto la zona Abisal\n"
               + "Tuviste que adaptarte al nuevo \n"
               + "clima para sobrevivir!.       \n"
               + " > Cuerpo Blando (menos quitina)\n"
               + " > Metabolismo muy lento", RUTA_IM_ABISO+ "crab.png"), "Evolución");
        CONSTANTES.DELTA_DESPLAZAMIENTO = 6;    
    }
    @Override
    public VBox buildInformation() {
        VBox main = new VBox();
        main.setPadding(new Insets(10, 20, 20, 20));
        main.setSpacing(10);
        Label titulo = new Label("Zona Abisal: ");
        titulo.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt");
        main.getChildren().add(titulo);
        
        HBox grids = new HBox();
        grids.setSpacing(20);
        GridPane g1 = new GridPane();
        g1.add(new Label("Profundad:"), 0, 0);
        g1.add(new Label("Desde los 4000 metros a 6000metros (Arenas, y otros, 2008)"), 1, 0);
        g1.add(new Label("Salinidad: "), 0, 1);
        g1.add(new Label("34.7 g/L (Graco, Ledesma, Flores, & Girón, 2007)"), 1, 1);
        g1.add(new Label("Presión Hidrostática: "), 0, 2);
        g1.add(new Label("Elevada (función lineal de 10m equivale 1atm) (Carney, 2005)"), 1, 2);
        main.getChildren().add(g1);
        
        GridPane g2 = new GridPane();
        g2.add(new Label("Oxígeno Disuelto: "), 0, 0);
        g2.add(new Label("entre 0 y 0,2 g/L"), 1, 0);
        g2.add(new Label("Presencia Luz: "), 0, 1);
        g2.add(new Label("No hay luz, por ende no hay productividad primaria."), 1, 1);
        g2.add(new Label("Temperatura: "), 0, 2);
        g2.add(new Label("0 a 3°C (Lizano, s.f.)"), 1, 2);
        grids.getChildren().addAll(g1,g2);
        main.getChildren().add(grids);
        
        return main;
    }
}
