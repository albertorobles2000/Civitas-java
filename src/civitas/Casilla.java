package civitas;

//import civitas.TipoCasilla;
//import civitas.TituloPropiedad;
//import civitas.Sorpresa;
//import civitas.MazoSorpresas;

import java.util.ArrayList;

public class Casilla {

    //Atributos de instancia
    protected String nombre;


    //-------------------------------------------------------------
    //Métodos
    
    Casilla (String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    
    private void informe (int actual, ArrayList<Jugador> todos){
        
        String evento = "El jugador " + todos.get(actual).getNombre()
                + " ha caido en la casilla " + this.getNombre();
        
        //Informar al diario de que he caido en la casilla
        Diario.getInstance().OcurreEvento(evento);
    }
    
    
    public boolean jugadorCorrecto (int actual, ArrayList<Jugador> todos){
        return (todos.size()>actual) && (actual>=0);
    }
    
    void recibeJugador (int actual , ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
        }    
    }

    
  
    @Override
    public String toString () {
        String representacion;
        
        representacion = "Casilla: \n"
                        +"\t-Nombre: " + nombre + "\n";
        

        
        return representacion;
    }
    
    //-------------------------------------------------------------
    
   /* public static void main (String args[]){
        
        
        //Declaracion de variables
        TituloPropiedad Titulo1 = new TituloPropiedad("Paseo del Prado",
                50.0f,100.0f,2000.0f,4000.0f,1000.0f);
        MazoSorpresas mazo1 = new MazoSorpresas ();
        
        Casilla descanso = new Casilla("Descanso");
        Casilla calle = new Casilla(Titulo1);
        Casilla impuesto = new Casilla("Impuesto por ser tan guapo", 1500);
        Casilla juez = new Casilla("Juzgados", 3);
        Casilla sorpresa = new Casilla(mazo1, "Quedas libre de la carcel");
        
        
        //Probando constructores
        System.out.println("*Probando constructores...");

            //Probando métodos get
            System.out.println("\t\n-Con métodos get...\n");
            System.out.println("\t*Descanso: ");
                System.out.println("\t\tNombre: " + descanso.getNombre());
                System.out.println("\t\tTitulo de Propiedad: " + descanso.getTituloPropiedad());
            System.out.println("\t*Calle: ");
                System.out.println("\t\tNombre: " + calle.getNombre());
                System.out.println("\t\tTitulo de Propiedad: " + calle.getTituloPropiedad());
            System.out.println("\t*Impuesto: ");
                System.out.println("\t\tNombre: " + impuesto.getNombre());
                System.out.println("\t\tTitulo de Propiedad: " + impuesto.getTituloPropiedad());
            System.out.println("\t*Juez: ");
                System.out.println("\t\tNombre: " + juez.getNombre());
                System.out.println("\t\tTitulo de Propiedad: " + juez.getTituloPropiedad());
            System.out.println("\t*Sorpresa: ");
                System.out.println("\t\tNombre: " + sorpresa.getNombre());
                System.out.println("\t\tTitulo de Propiedad: " + sorpresa.getTituloPropiedad());
                
            //Probando to string
            System.out.println("\t\n-Y con to String...\n");
            System.out.println("\t\t*Descanso..." + descanso.toString());
            System.out.println("\t\t*Calle..." + calle.toString());
            System.out.println("\t\t*Impuesto..." + impuesto.toString());
            System.out.println("\t\t*Juez..." + juez.toString());
            System.out.println("\t\t*Sorpresa..." + sorpresa.toString());
            
        //Resto de métodos
            //Declaracion de variables
        Jugador j1 = new Jugador ("Jesus");
        Jugador j2 = new Jugador ("Nuria");
        Jugador j3 = new Jugador ("Jose Manu");
        Jugador j4 = new Jugador ("Julio");
        
        ArrayList<Jugador> todos = new ArrayList<>();
        todos.add(j1);todos.add(j2);todos.add(j3);todos.add(j4);
        
            //Métodos recibe+jugadorcorrecto+informe
        impuesto.recibeJugador_impuesto(0, todos);
        juez.recibeJugador_juez(0, todos);
        
        System.out.println("Leyendo diario...");
        while(Diario.getInstance().EventosPendientes())
                System.out.println(Diario.getInstance().LeerEvento());
        
        
        
        
    }*/
}