package Maps;

import Utilitarios.CONSTANTES;
import static Utilitarios.CONSTANTES.RUTA_IM_SUPRA;
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
import popOut.Message;
import popOut.PopMessage;
import popOut.PopOut;
import proyectojuego.ProyectoJuego;

/**
 *
 * @author josie
 */
public class SupraLitoralMap extends MapPane {
    private boolean isObjeto;
    
    public SupraLitoralMap(double x, double y) {
        super(new Animal(RUTA_IM_SUPRA + "crab.gif"),RUTA_IM_SUPRA + "imagen_fondo.jpg");
        isObjeto = true;
        animal.fijarPosicionObjeto(x, y);
    }
    
    @Override
    protected void generarLetrero(){
        Casa c1 = new Casa(404,20, CONSTANTES.RUTA_IM_SUPRA + "objeto.png");
        super.objetos.add(c1);
        ImageView im = new ImageView(new Image(getClass().getResourceAsStream(RUTA_IM_SUPRA + "letrero.png"),
                                140,
                                170,
                                true,
                                true));
        im.setLayoutX(30);
        im.setLayoutY(355);
        
        gamePane.getChildren().addAll(c1.getObjeto(), im);
    }

    @Override
    protected final void generarLimites() {
        //2,3 cm es -> 100
    }

    @Override
    protected final void generarEnemigos() {
        Message m1 = new Message("¡HAS SIDO DEVORADO!",""
            , CONSTANTES.RUTA_IM_SUPRA + "cangrejo-comido.jpg");
        Message m2 = new Message("¡HAS SIDO DEVORADO!",""
            , CONSTANTES.RUTA_IM_SUPRA + "mapache-cangrejo.PNG");
                
        super.enemies.add(new Enemigo(RUTA_IM_SUPRA + "enemigo.gif", 174,20, m1));
        super.enemies.add(new Enemigo(RUTA_IM_SUPRA + "enemigo.gif",45,217, m1));
        super.enemies.add(new Enemigo(RUTA_IM_SUPRA + "enemigo.gif",966,375, m1));
        super.enemies.add(new Enemigo(RUTA_IM_SUPRA + "enemigo.gif",631,374, m1));
        
        super.enemies.add(new Enemigo(RUTA_IM_SUPRA + "Mapache.png",670,25, m2));
        super.enemies.add(new Enemigo(RUTA_IM_SUPRA + "Mapache.png",948,281, m2));
    }

    @Override
    boolean isChangeMapDown(double x, double y) {
        //[clicked] X: 201.0,Y: 574.0.
        if( y >= Ymax && x < 200){
            super.changeMap(new LitoralMap(999, 27));
            return true;     
        }
        return false;
    }
    
    @Override
    boolean isChangeMapLeft(double x, double y) {
        return false;
    }

    @Override
    Message createMessageFood() {
        return new Message("VIDA EXTRA",
               "¡¡¡Algas para el corazón!!!\n"
            + "Has regenerado 1 corazón.",
        RUTA_IM_SUPRA + "alimentos.png");

    }

    @Override
    Message createMessageEnemy() {
        return new Message("TE ATACAN!"
            , "No te acerques a los depredadores!\n"
            + "El cangrejo no puede defenderse \n"
            + "contra ellos. Recupera vida comiendo.",
        RUTA_IM_SUPRA + "enemigos.png");
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
                        , "Los cangrejos crean cuevas para \n"
                        + "protegerse de los enemigos.\n"
                        + "Descansa y regenera un corazón.",
                            RUTA_IM_SUPRA + "objeto.png");
                    PopOut pop = new PopMessage(ProyectoJuego.stageMain, m, "Refugios");
                    isObjeto = false;
                }
            }
        }
        return false;
    }

    @Override
    protected final void generarVidas() {   
        
        health.add(generarImagen(170, 370, RUTA_IM_SUPRA+ "alimento1.png"));
        health.add(generarImagen(500, 20, RUTA_IM_SUPRA+ "alimento1.png"));
        health.add(generarImagen(812, 348, RUTA_IM_SUPRA+ "alimento1.png"));
        health.add(generarImagen(634, 282, RUTA_IM_SUPRA+ "alimento1.png"));
        
        health.add(generarImagen(34, 21, RUTA_IM_SUPRA+ "alimento2.png"));
        health.add(generarImagen(269, 365, RUTA_IM_SUPRA+ "alimento2.png"));
        health.add(generarImagen(183, 234, RUTA_IM_SUPRA+ "alimento2.png"));
    }

    @Override
    void isEvolve() {
        PopOut pop = new PopMessage(ProyectoJuego.stageMain, 
                new Message("UNA NUEVA CRIATURA!",
                         "Bienvenido a las playas de  \n"
                       + "la zona supraLitoral. Explora\n"
                       + ",descubre y diviertete en las\n"
                       + "profundidades de los mares.", RUTA_IM_SUPRA+ "crab.gif"), "Bienvenido");
        CONSTANTES.ANIMAL_HEIGHT = CONSTANTES.ANIMAL_HEIGHT + CONSTANTES.GROWTH;
        CONSTANTES.ANIMAL_WIDTH = CONSTANTES.ANIMAL_WIDTH + CONSTANTES.GROWTH;
    }

    @Override
    public VBox buildInformation() {
        VBox main = new VBox();
        main.setPadding(new Insets(10, 20, 20, 20));
        main.setSpacing(10);
        Label titulo = new Label("Zona Supra Litoral:");
        titulo.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt");
        main.getChildren().add(titulo);
        
        GridPane g1 = new GridPane();
        g1.add(new Label("Ubicación:"), 0, 0);
        g1.add(new Label("Por encima de la línea de Marea Alta"), 1, 0);
        g1.add(new Label("Temperatura: "), 0, 1);
        g1.add(new Label("Ambiente mayor a 25°C"), 1, 1);
        g1.add(new Label("Interacción:"), 0, 2);
        g1.add(new Label("Antropogénicas"), 1, 2);
        main.getChildren().add(g1);
        return main;
    }

}
