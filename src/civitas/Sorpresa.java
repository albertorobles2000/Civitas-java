package civitas;

//import civitas.Tablero;
//import civitas.MazoSorpresas;
//import civitas.TipoSorpresa;

import java.util.ArrayList;


public abstract class Sorpresa {
    
    //Atributos de instancia
    protected String texto;
  
    
    
    //-------------------------------------------------------------
    //Métodos
    
    abstract void aplicarAJugador (int actual, ArrayList<Jugador> todos);
    
    protected void informe (int actual, ArrayList<Jugador> todos){
        String evento = "Se esta aplicando la sorpresa " + "'"+this.toString()
                    + "'"+ " al jugador " + todos.get(actual).getNombre();
        Diario.getInstance().OcurreEvento(evento);
    }
   
    
    public boolean jugadorCorrecto (int actual, ArrayList<Jugador> todos){
        return (todos.size()>actual) && (actual>=0);
    }
    
    Sorpresa(String texto){
        this.texto = texto;
    }
    
    @Override
    public String toString () {
        return texto;
    }
    
   
    
    //-------------------------------------------------------------
    
   /* public static void main (String args[]){
        
        //Pruebo constructores
        
            //Creo Casilla
        TituloPropiedad TituloPaseoDelPrado = new TituloPropiedad("Paseo del Prado",
                50.0f,100.0f,2000.0f,4000.0f,1000.0f);
        Casilla PaseoDelPrado = new Casilla (TituloPaseoDelPrado);
            
            //Creo tablero correcto
        Tablero tablero = new Tablero (1);
        tablero.añadeCasilla(PaseoDelPrado);
        tablero.añadeJuez();
        
            //Creo Mazo
        MazoSorpresas mazo1 = new MazoSorpresas();
        
        System.out.println("*Probando constructores....");
        Sorpresa libreCarcel = new Sorpresa(TipoSorpresa.SALIRCARCEL,mazo1); 
        Sorpresa irAPaseoDelPrado = new Sorpresa (TipoSorpresa.IRCASILLA, tablero,1,"Ir a Paseo del Prado");
        Sorpresa veCarcel = new Sorpresa (TipoSorpresa.IRCARCEL, tablero);
        Sorpresa impuestoPropiedades = new Sorpresa (TipoSorpresa.PORCASAHOTEL,tablero, 50,"Paga por propiedades");
        Sorpresa paga = new Sorpresa (TipoSorpresa.PAGARCOBRAR,tablero, -50,"Paga");
        Sorpresa pagaTodos = new Sorpresa (TipoSorpresa.PORJUGADOR,tablero, 50,"Paga a todos/Cobra 1");
       
            //Añado al mazo la sorpresa libre carcel
        mazo1.alMazo(libreCarcel);
        
        
        //Probando to string
        System.out.println("*Probando to string...");
        System.out.println("\t1-" + libreCarcel.toString());
        System.out.println("\t2-" + irAPaseoDelPrado.toString());
        System.out.println("\t3-" + veCarcel.toString());
        System.out.println("\t4-" + impuestoPropiedades.toString());
        System.out.println("\t5-" + paga.toString());
        System.out.println("\t6-" + pagaTodos.toString());
        
        //Probando usada
        System.out.println("*Probando usada....");
        libreCarcel.usada();
        irAPaseoDelPrado.usada();
        veCarcel.usada();
        impuestoPropiedades.usada();
        paga.usada();
        pagaTodos.usada();

        System.out.println("\tLeyendo diario...");
        while(Diario.getInstance().EventosPendientes())
            System.out.println("\t\t" + Diario.getInstance().LeerEvento());

        //Probando salir del mazo
        System.out.println("*Probando salir del mazo....");
        libreCarcel.salirDelMazo();
        irAPaseoDelPrado.salirDelMazo();
        veCarcel.salirDelMazo();
        impuestoPropiedades.salirDelMazo();
        paga.salirDelMazo();
        pagaTodos.salirDelMazo();

        System.out.println("\tLeyendo diario...");
        while(Diario.getInstance().EventosPendientes())
            System.out.println("\t\t" + Diario.getInstance().LeerEvento());
        
        //Probando aplicar a jugador
        Jugador j1 = new Jugador ("Jesus");
        Jugador j2 = new Jugador ("Nuria");
        Jugador j3 = new Jugador ("Jose Manu");
        Jugador j4 = new Jugador ("Julio");
        
        ArrayList<Jugador> todos = new ArrayList<>();
        todos.add(j1);todos.add(j2);todos.add(j3);todos.add(j4);

        //Probando aplicar a jugador
        System.out.println("*Probando aplicar a jugador....");
        libreCarcel.aplicarAJugador(0,todos);
//p3        irAPaseoDelPrado.aplicarAJugador(0,todos);
///        veCarcel.aplicarAJugador(0,todos);
   //     impuestoPropiedades.aplicarAJugador(0,todos);
     //   paga.aplicarAJugador(0,todos);
       // pagaTodos.aplicarAJugador(0,todos);
        
        System.out.println("\tLeyendo diario...");
        while(Diario.getInstance().EventosPendientes())
            System.out.println("\t\t" + Diario.getInstance().LeerEvento());
        

        //Probando informe
        System.out.println("*Probando informe....");
        libreCarcel.informe(1,todos);
        irAPaseoDelPrado.informe(0,todos);
        veCarcel.informe(0,todos);
        impuestoPropiedades.informe(0,todos);
        paga.informe(0,todos);
        pagaTodos.informe(0,todos);

        System.out.println("\tLeyendo diario...");
        while(Diario.getInstance().EventosPendientes())
            System.out.println("\t\t" + Diario.getInstance().LeerEvento());
        
        //Probando jugador correcto
        System.out.println("*Probando jugador correcto....");
        System.out.println("\t-4 --> " + libreCarcel.jugadorCorrecto(4, todos));
        System.out.println("\t-0 --> " +libreCarcel.jugadorCorrecto(-5, todos));
        System.out.println("\t-(-5) --> " +libreCarcel.jugadorCorrecto(0, todos));
        System.out.println("\t-3 --> "+ libreCarcel.jugadorCorrecto(3, todos));
    }*/
}