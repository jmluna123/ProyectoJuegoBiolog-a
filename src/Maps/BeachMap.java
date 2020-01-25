/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maps;

import mainCharacters.Animal;
import mainCharacters.Enemigo;

/**
 *
 * @author josie
 */
public class BeachMap extends MapPane {
    
    public BeachMap(double x, double y) {
        super(new Animal(),"imagen_fondo1.png");
        animal.fijarPosicionObjeto(x, y);
        generarLimites();
        generarEnemigos();
        super.loadElementsPane();
    }

    @Override
    protected final void generarLimites() {
        //2,3 cm es -> 100
        super.borders.add(super.generarBorde(143.48,0, 586.96, 90));
        super.borders.add(super.generarBorde(143.48, 185, 586, 80));
    }

    @Override
    protected final void generarEnemigos() {
        Enemigo e = new Enemigo("gaviota.png", 120, 350, 1000, false);
        super.enemies.add(e.getObjeto());
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
        if(x <= 0){
            super.changeMap(new MesolitoralMap(super.Xmax, y));
            return true;
        }
        return false;
    }

    @Override
    boolean isChangeMapRight(double x, double y) {
        return false;
    }
}
