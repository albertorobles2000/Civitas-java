package civitas;
import java.util.ArrayList;

public class CasillaImpuesto extends Casilla{
    
    //---------------------------------------------------------
    //Atributos
    private float importe;
    
    //----------------------------------------------------------
    
    CasillaImpuesto (String nombre , float cantidad){
        super(nombre);
        importe = cantidad;
    }
    
    @Override
    void recibeJugador (int actual, ArrayList<Jugador> todos ){
       
        if (jugadorCorrecto(actual,todos)){
            super.recibeJugador(actual,todos);
            todos.get(actual).pagaImpuesto(importe);
        }
    }
    
    @Override
    public String toString () {
        String representacion;
        
        representacion = "Casilla Impuesto: \n\tNombre: " + nombre + "\n\tImporte: "
                + importe;
        
        return representacion;
    }
     
    
}