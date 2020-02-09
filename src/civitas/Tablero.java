package civitas;

//import civitas.Casilla;


import java.util.ArrayList;


public class Tablero {
    
    //--------------------------------------
    //Atributos de instancia
    //--------------------------------------
    private int numCasillaCarcel;
    private ArrayList<Casilla> casillas;
    private int porSalida;      //en un turno
    private boolean tieneJuez;
    
    private static final int INDICECARCELDEFECTO = 1;
    private static final boolean JUEZDEFECTO = false;
    private static final int SALIDADEFECTO = 0;
    
    //--------------------------------------
    //Constructor
    //--------------------------------------
    Tablero(int miCasillaCarcel){
        
        //inicializo numCasillaCarcel
        if (miCasillaCarcel>=INDICECARCELDEFECTO){
            numCasillaCarcel = miCasillaCarcel;
        }
        else{
            numCasillaCarcel = INDICECARCELDEFECTO;
        }
        
        //inicializo casillas
        casillas = new ArrayList<Casilla>();
        casillas.add(new Casilla("Salida"));
        
        //inicializo porSalida y tieneJuez
        porSalida = SALIDADEFECTO;
        tieneJuez = JUEZDEFECTO;
         
    }
    
    //--------------------------------------
    //Metodos de instancia privados
    //--------------------------------------
    private boolean correcto() {
        return (casillas.size()>numCasillaCarcel); //&&tieneJuez
    }
    
    private boolean correcto(int numCasilla){
        return (correcto() && (casillas.size() > numCasilla));  //>=
    }
   
    //--------------------------------------
    //Consultores
    //--------------------------------------
    
    int getCarcel() {
        return numCasillaCarcel;
    }
    
    int getPorSalida(){
        
        int vecesPorSalida = porSalida;
        
        if (porSalida>0){
            --porSalida;
        }
        
        return vecesPorSalida;     //devuelvo el valor antes de ser decrementado
    }
    
    Casilla getCasilla (int numCasilla) {
        
        //correcto --> casillas[numCasilla]
        //incorrecto --> null
        
        return correcto(numCasilla)? (casillas.get(numCasilla)) : null;
    }
    
    //--------------------------------------
    //Métodos para añadir
    //--------------------------------------

    void añadeCasilla (Casilla casilla){
        
        if(casillas.size() == numCasillaCarcel){
            casillas.add(new Casilla("Cárcel"));
        }
        
        casillas.add(casilla);
        
        if (casillas.size() == numCasillaCarcel) {
            casillas.add(new Casilla("Cárcel"));
        }
    }
    
    void añadeJuez () {
        if (!tieneJuez)
            añadeCasilla (new CasillaJuez ("Juez",numCasillaCarcel));
        
        tieneJuez = true;
    }

    //--------------------------------------
    //Métodos relacionados con el dado
    //--------------------------------------

    int nuevaPosicion (int actual, int tirada){
        
        int posFinal = -1;
        
        
        if (correcto()){
            posFinal = ( actual + tirada ) % casillas.size();
            int vecesPorSalida = ( actual + tirada ) / casillas.size(); 
            porSalida += vecesPorSalida;
        }
    
        return posFinal;
    }
    
    int calcularTirada (int origen, int destino){
        
        return ((destino-origen) + porSalida*casillas.size() );
 
    }
    
    
}