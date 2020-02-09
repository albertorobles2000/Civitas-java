/*
Esta clase sólo tiene main porque será la que use para probar el juego,
la vista y el controlador
*/

package GUI;

import civitas.CivitasJuego;
//import JuegoTexto.VistaTextual
//import JuegoTexto.Controlador
import java.util.ArrayList;

public class ProgramaPrincipal {
    
    
    public static void main (String args[]){
        
        //Creo jugadores
        CivitasVista civitas_vista = new CivitasVista();
        Dado.createInstance(civitas_vista);
        CapturaNombres captura = new CapturaNombres(civitas_vista,true);        
        
        ArrayList<String> nombresJugadores;
        nombresJugadores = captura.getNombres();
        
      
        //Creo juego en modo debug
        CivitasJuego juego = new CivitasJuego(nombresJugadores);
        Dado.getInstance().setDebug(true);
        //Mazo puesto a true dentro del constructror de CivitasJuego();
        
        //Jugar
        CivitasVista interfaz = new CivitasVista();
        Controlador controlador = new Controlador(juego,civitas_vista);
        civitas_vista.setCivitasJuego(juego);
        civitas_vista.ActualizarVista();
        controlador.juega();
       
        
        
        
    }
}
