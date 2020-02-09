package civitas;

import java.util.ArrayList;


public class Diario {
    
    //Atributos de clase
    static final private Diario INSTANCE = new Diario ();
    
    //Atributos de instancia
    private ArrayList<String> eventos;
    
    //Constructor
    private Diario (){
        eventos = new ArrayList<>();
    }
    
    //Método de clase
    static public Diario getInstance () {
        return INSTANCE;
    }
    
    //Métodos de instancia
    void OcurreEvento(String e) {
      eventos.add(e);
    }

    public boolean EventosPendientes () {
      return (eventos.size() > 0);
    }

    public String LeerEvento () {
      
        //Debo equiparar a shift de ruby
        //Hace que el array actue como una cola (FIFO)
      String e = eventos.get(0);
      eventos.remove(0);
      return e;
    }
}
