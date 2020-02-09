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


public class SorpresaPorJugador extends Sorpresa{
    
    private int valor;
    
    SorpresaPorJugador(int valor, String texto){
        super(texto);
        this.valor = valor;
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            informe(actual,todos);
            
    
            //Todos los jugadores pagan al jugador actual
           
            int numJugadores = todos.size();
            
            Sorpresa a_todos = new SorpresaPagarCobrar(-valor,"Todos menos actual");
            Sorpresa a_actual = new SorpresaPagarCobrar((numJugadores-1)*valor,"actual");
            //Aplico pagos
           
            for (int i=0; i<numJugadores; ++i){
                if (i != actual)
                    a_todos.aplicarAJugador(i,todos);
            }
            
            //Aplico cobro
            a_actual.aplicarAJugador(actual, todos);
            
        }
    }
}
