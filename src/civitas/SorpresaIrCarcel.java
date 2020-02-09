/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package civitas;

import java.util.ArrayList;

/**
 *
 * @author alberto
 */
public class SorpresaIrCarcel extends Sorpresa{
    private Tablero tablero;
    SorpresaIrCarcel(Tablero tablero){
        super("Vas a la carcel");
        this.tablero = tablero;
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            super.informe(actual,todos);
            todos.get(actual).encarcelar(tablero.getCarcel());
        }
    }
}
