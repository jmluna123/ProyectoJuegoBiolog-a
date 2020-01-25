/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maps;

import mainCharacters.Animal;

/**
 *
 * @author josie
 */
public class MesolitoralMap extends MapPane {

    public MesolitoralMap(double x, double y) {
        super(new Animal(), "imagen_fondo2.jpg");
        animal.fijarPosicionObjeto(x, y);
        generarLimites();
        generarEnemigos();
        super.loadElementsPane();
    }

    @Override
    void generarLimites() {
        //TODO
    }

    @Override
    void generarEnemigos() {
        //TODO
    }

    @Override
    boolean isChangeMapUp(double x, double y) {
        return false;
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
    boolean isChangeMapRight(double x, double y) {
        if( x >= super.Xmax){
            super.changeMap(new BeachMap(0, y));
            return true;
        }
        return false;
    }
    
}
