package civitas;
import java.util.ArrayList;

public class CasillaJuez extends Casilla{
    
    //---------------------------------------------------------
    //Atributos
    private static int carcel = -1;
    
    //----------------------------------------------------------
    
    CasillaJuez (String nombre , int numCasillaCarcel){
        super(nombre);
        carcel = numCasillaCarcel;
    }
    
    @Override
    void recibeJugador (int actual, ArrayList<Jugador> todos ){
       
        if (jugadorCorrecto(actual,todos)){
            super.recibeJugador(actual,todos);
            todos.get(actual).encarcelar(carcel);
        }
    }
     
    
}