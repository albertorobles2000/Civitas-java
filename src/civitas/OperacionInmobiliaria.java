package civitas;

//import civitas.GestionesInmobiliarias

public class OperacionInmobiliaria {
    
    //Atributos de instancia
    private int numPropiedad;
    private GestionesInmobiliarias gestion;
    
    
    //Constructor
    public OperacionInmobiliaria(GestionesInmobiliarias gest, int ip){
        numPropiedad = ip;
        gestion = gest;
    }
    
    //Consultores
    public GestionesInmobiliarias getGestion(){
        return gestion;
    }
    
    public int getNumPropiedad(){
        return numPropiedad;
    }
}