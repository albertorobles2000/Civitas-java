package civitas;
import java.util.ArrayList;

public class CasillaCalle extends Casilla{
    
    //---------------------------------------------------------
    //Atributos
    private TituloPropiedad tituloPropiedad;
    
    
    //----------------------------------------------------------
    
    CasillaCalle (TituloPropiedad titulo){
        super(titulo.getNombre());
        tituloPropiedad = titulo;
    }
    
    TituloPropiedad getTituloPropiedad (){
        return tituloPropiedad;
    }
    
    @Override
    void recibeJugador(int actual, ArrayList<Jugador> todos ){
 
        if (jugadorCorrecto(actual,todos)){
            super.recibeJugador(actual, todos);

            if (!tituloPropiedad.tienePropietario() )
            {
                todos.get(actual).puedeComprarCasilla();
            }
            
            else  
            {
                 tituloPropiedad.tramitarAlquiler(todos.get(actual)); 
            }
            
        }
    }
    
    @Override
    public String toString () {
        String representacion;
        
        representacion = "Casilla Calle: \n\tNombre: " + nombre;
        
        if (tituloPropiedad != null)
            representacion += "\n\t- Titulo de propiedad = " + tituloPropiedad.toString() + "\n";

        
        return representacion;
    }
    
    
    
}
