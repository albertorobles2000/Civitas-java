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
public class SorpresaPorCasaHotel extends Sorpresa{
    private Tablero tablero;
    private int valor;
    SorpresaPorCasaHotel(Tablero tablero, int valor, String texto){
        super(texto);
        this.tablero = tablero;
        this.valor = valor;
    }
    
   @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            informe(actual,todos);
            
            //Modifico el saldo del jugador en funcion del numero de propiedades
            Jugador j = todos.get(actual);
            j.modificarSaldo(j.cantidadCasasHoteles()*valor);
        }
    } 
}
