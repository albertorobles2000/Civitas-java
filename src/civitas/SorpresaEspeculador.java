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
public class SorpresaEspeculador extends Sorpresa{
    private int fianza;
    
    SorpresaEspeculador(int valor){
        super("Jugador va a ser convertido en jugador especulador");
        fianza = valor;
    }
   
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            todos.set(actual, new JugadorEspeculador(todos.get(actual),fianza));
        }
    }
    
}
