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
public class SorpresaSalirCarcel extends Sorpresa{
    private MazoSorpresas mazo;
    SorpresaSalirCarcel(MazoSorpresas mazo){
        super("Sorpresa para obtener salvoconducto");
        this.mazo = mazo;
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
         if (jugadorCorrecto(actual, todos)){
            informe(actual,todos);
            
            
            //Pregunto por el salvoconducto
            boolean alguienTieneSV = false;
            int numJugadores = todos.size();
            
            for (int i=0; i<numJugadores; ++i)
                if (todos.get(i).tieneSalvoconducto())
                    alguienTieneSV = true;
            
            //Si nadie lo tiene lo obtiene actual y se sale del mazo
            if (!alguienTieneSV){
                todos.get(actual).obtenerSalvoconducto(this);
                this.salirDelMazo();
            }
                
        }
    }
    
    void salirDelMazo (){    
        mazo.inhabilitarCartaEspecial(this);
    }
    
    void usada(){
        mazo.habilitarCartaEspecial(this);
    }
}
