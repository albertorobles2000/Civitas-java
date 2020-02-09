package civitas;
import java.util.ArrayList;

public class CasillaSorpresa extends Casilla{
    
    //---------------------------------------------------------
    //Atributos
    private MazoSorpresas mazo;
    private Sorpresa sorpresa;
    
    //----------------------------------------------------------
    
    CasillaSorpresa (MazoSorpresas mazo, String nombre ) {
        super(nombre);
        this.mazo = mazo;
    }
    
    @Override
    void recibeJugador (int actual, ArrayList<Jugador> todos ){
       
        if (jugadorCorrecto(actual,todos)){
            super.recibeJugador(actual,todos);
            //1
            sorpresa = mazo.siguiente();

            sorpresa.aplicarAJugador(actual, todos);
        }
    }
    
     @Override
    public String toString () {
        String representacion;
        
        representacion = "Casilla Sorpresa: \n"
                        +"\t-Nombre: " + nombre + "\n\tSorpresa: " + sorpresa.toString();
        

        
        return representacion;
    }
}