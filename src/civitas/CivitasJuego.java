package civitas;

//import civitas.Jugador;
//import civitas.EstadosJuego;
//import civitas.GestorEstados;
//import civitas.Tablero;
//import civitas.MazoSorpresas;
import GUI.Dado;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.sort;
import java.util.Iterator;


public class CivitasJuego {
    
    //-------------------------------------------------------------

    //Atributos de referencia
    private MazoSorpresas mazo;
    private Tablero tablero;
    private GestorEstados gestorEstados;
    private EstadosJuego estado;
    private ArrayList<Jugador> jugadores;
    
    //Atributos de instancia
    private int indiceJugadorActual; 
    
    //-------------------------------------------------------------
    //Métodos
    public String actualizarInfo(){   //String
        
        String salida = "";
        
        //Muestra la información del jugador actual
        salida = "El jugador actual es el jugador " + indiceJugadorActual + "\n"
                  +jugadores.get(indiceJugadorActual).toString();
        
        //Muestra también información cuando un jugador cae en bancarrota
            //Para recorrer ranking
        int numJugadores = jugadores.size();
        ArrayList<Jugador> ranking = ranking();
        int i = 0;
        int pos = 1;
        int empatados = 1;
        
        
        //Busco si hay alguien en bancarrota
        if (finalDelJuego()){
            salida += "\nEl juego ha finalizado, ranking: \n";
            
            //Mostrar el ranking
            while(i<numJugadores){
                
                salida += "\t" + pos +"- "+ranking.get(i).getNombre()+ ", saldo: " + ranking.get(i).getSaldo()+"\n";
                
                if((i+1) <numJugadores ){
                    if (ranking.get(i).getSaldo() != ranking.get(i+1).getSaldo()){
                        pos+=empatados;
                        empatados = 1;
                    }
                    else
                        empatados++;
                }
                
                ++i;
            }  
        }
        
        return (salida);
        
    }
    
    private void avanzaJugador(){   //p3
        
        //Obtener jugador y su casilla
        Jugador jugadorActual = getJugadorActual();
        int posicionActual = jugadorActual.getNumCasillaActual();
        
        //Calcular tirada
        
        int tirada = Dado.getInstance().tirar();
        int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);
        
        //Obtener casilla actual
        Casilla casilla = tablero.getCasilla(posicionNueva);
        
        //[PRE]Contabilizar pasos por salida
        contabilizarPasosPorSalida(jugadorActual);
        
        //Mover a casilla
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(indiceJugadorActual, jugadores);
        //[POST]Contabilizar pasos por salida 
        contabilizarPasosPorSalida(jugadorActual);
        
    }
    
    public boolean cancelarHipoteca (int ip){  
       return jugadores.get(indiceJugadorActual).cancelarHipoteca(ip);
    }
    
    public CivitasJuego (ArrayList<String> nombres){
        
        //Inicializar jugadores
        jugadores = new ArrayList<>();
        int numJugadores = nombres.size();
        for (int i=0; i<numJugadores ; ++i){
            jugadores.add(new Jugador(nombres.get(i)));
        }
        
        //Creo el gestor de estados y fijo estado
        gestorEstados = new GestorEstados();
        estado = gestorEstados.estadoInicial();
        
        //Jugador actual --> quien empieza
        indiceJugadorActual = Dado.getInstance().quienEmpieza(nombres.size());
        
        //Creo mazo e inicializo tablero(también lo crea) y mazo
        mazo = new MazoSorpresas(true);
        inicializaTablero(mazo);
        inicializaMazoSorpresas(tablero);
        
      
    }
    
    public boolean comprar (){      //p3
        
        //1
        Jugador jugadorActual = getJugadorActual();
        
        //2 y 3
        CasillaCalle casilla = (CasillaCalle) getCasillaActual();
        
        //4
        TituloPropiedad titulo = casilla.getTituloPropiedad();
        
        //5
        boolean res = jugadorActual.comprar(titulo);
        
        return res;
    }
    
    public boolean construirCasa (int ip){
        return jugadores.get(indiceJugadorActual).construirCasa(ip);
    }
    
    public boolean construirHotel (int ip){
        return jugadores.get(indiceJugadorActual).construirHotel(ip);
    }
    
    private void contabilizarPasosPorSalida(Jugador jugadorActual){
        
        //Cobrar las veces que pasa por salida el jugador actual
        while(tablero.getPorSalida()>0){
            jugadorActual.pasaPorSalida();
        }
    }
    
    public boolean finalDelJuego(){
        boolean fin = false;
        int numJugadores = jugadores.size();
        int i= 0; 
        
        while (i<numJugadores && !fin){
            if (jugadores.get(i).enBancarrota())
                fin = true;
            ++i;
        }
        
        return fin;
    }
    
    
    public Casilla getCasillaActual (){
        return tablero.getCasilla(getJugadorActual().getNumCasillaActual());
    }
    
    public Jugador getJugadorActual (){
        return jugadores.get(indiceJugadorActual);
    }
    
    public boolean hipotecar (int ip){
        return jugadores.get(indiceJugadorActual).hipotecar(ip);
    }
    
    public String infoJugadorTexto(){
        String info_Jugador = getJugadorActual().toString();
        return info_Jugador;
    }
    
    private void inicializaMazoSorpresas (Tablero tablero){
        int posPaseoPrado = -1;
        int i = 0;
        boolean encontrada = false;
        Casilla casilla_actual = tablero.getCasilla(i);
        
        
        while (casilla_actual != null && !encontrada){
            if (casilla_actual.getNombre().equals("Paseo del Prado")){
                encontrada = true;
                posPaseoPrado = i;
            }
                
            ++i;
            casilla_actual = tablero.getCasilla(i);
        }
        
        mazo.alMazo(new SorpresaSalirCarcel(mazo));
        mazo.alMazo(new SorpresaIrCasilla(tablero,posPaseoPrado,"Ir a Paseo del Prado"));
        mazo.alMazo(new SorpresaIrCasilla(tablero,2,"Ir a calle Mesones"));
        mazo.alMazo(new SorpresaIrCasilla(tablero,tablero.getCarcel(),"Vas a la carcel de visita"));
        mazo.alMazo(new SorpresaIrCarcel(tablero));
        mazo.alMazo(new SorpresaPorCasaHotel(tablero, 50,"Paga por propiedades"));
        mazo.alMazo(new SorpresaPorCasaHotel(tablero, -50,"Te pagan por propiedades"));
        mazo.alMazo(new SorpresaPagarCobrar(-50,"Paga"));
        mazo.alMazo(new SorpresaPagarCobrar(50,"Te pagan"));
        mazo.alMazo(new SorpresaPorJugador(50,"Paga a todos/Cobra 1"));
        mazo.alMazo(new SorpresaPorJugador(-50,"Paga a todos/Cobra 1"));
        mazo.alMazo(new SorpresaEspeculador(100));
    }
    
    private void inicializaTablero (MazoSorpresas mazo){
        
        //Creo el tablero
        final int INDICECASILLACARCEL= 1;
        tablero = new Tablero(INDICECASILLACARCEL);
        
        
//        //Añado casillas
        tablero.añadeCasilla(new CasillaSorpresa(mazo,"Casilla sorpresa"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Mesones",
                                        25.0f,50.0f,1000.0f,2000.0f,500.0f)));
        tablero.añadeCasilla(new Casilla("Descanso"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Gran Via",
                                        25.0f,50.0f,1000.0f,2000.0f,500.0f)));
        tablero.añadeCasilla(new CasillaImpuesto("Impuesto por ser tan guapo", 1500.0f));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Paseo del Prado",
                                        50.0f,100.0f,2000.0f,4000.0f,1000.0f)));
        tablero.añadeCasilla(new CasillaSorpresa(mazo,"Casilla sorpresa2"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Pedro Antonio",
                                        25.0f,50.0f,1000.0f,2000.0f,500.0f)));
        tablero.añadeJuez();
    }
    
    private void pasarTurno (){
        int numJugadores = jugadores.size();
        
        indiceJugadorActual = (indiceJugadorActual + 1 )%numJugadores;
    }
    
   public ArrayList<Jugador> ranking (){
        ArrayList<Jugador> ranking = new ArrayList<>();
        
        //Meto en ranking todos los jugadores
        int numJugadores = jugadores.size();
        
        for (int i=0; i<numJugadores; ++i){
            ranking.add(jugadores.get(i));
        }
        
        //Ordeno el ranking sin alterar la lista de jugadores
        sort(ranking,Collections.reverseOrder());
        
        return ranking;
        
    }
    
    public boolean salirCarcelPagando(){
        return jugadores.get(indiceJugadorActual).salirCarcelPagando();
    }
    
    public boolean salirCarcelTirando(){
        return jugadores.get(indiceJugadorActual).salirCarcelTirando();
    }
    
    public OperacionesJuego siguientePaso (){   //p3
        Jugador jugadorActual = getJugadorActual();
        OperacionesJuego operacion = gestorEstados.operacionesPermitidas(jugadorActual,estado);
        
        if (operacion == OperacionesJuego.PASAR_TURNO){
            pasarTurno();
            siguientePasoCompletado(operacion);
        }
        
        else if (operacion == OperacionesJuego.AVANZAR){
            avanzaJugador();
            siguientePasoCompletado(operacion);
        }
         
        return operacion;
    }
    
    public void siguientePasoCompletado (OperacionesJuego operacion){
        estado = gestorEstados.siguienteEstado(jugadores.get(indiceJugadorActual), estado, operacion);
    }
        
    public boolean vender (int ip){
        return jugadores.get(indiceJugadorActual).vender(ip);
    }
   
    
    //-------------------------------------------------------------
    
    public static void main (String args[]){
        
        //Jugadores
        ArrayList<String> nombresJugadores = new ArrayList<>();
        nombresJugadores.add("Jesus");
//        nombresJugadores.add("Nuria");
//        nombresJugadores.add("Jose");
//        nombresJugadores.add("Julio");
//        nombresJugadores.add("Javilonguis");
        
        //Creo Juego
        CivitasJuego Juego = new CivitasJuego(nombresJugadores);
        
        
        //Probando Pasar turno
////        System.out.println("*Simulando pasar de turno 10 veces...");
////        for (int i=0; i<10; ++i){
////            System.out.println("\tTurno de " + Juego.jugadores.get(Juego.indiceJugadorActual).getNombre());
////            Juego.pasarTurno();
////        }
        
////        //Probando getJugadorActual
////        System.out.println("\n*El jugador actual es: " + Juego.getJugadorActual().getNombre());
        
////        //Probando casilla actual
////        System.out.println("\n*Casilla actual : " + Juego.getCasillaActual().getNombre());
        

        
////        //Probando contabilizar por salida
////        Juego.tablero.nuevaPosicion(0,10);
////        Juego.contabilizarPasosPorSalida(Juego.getJugadorActual());
////        while(Diario.getInstance().EventosPendientes())
////            System.out.println(Diario.getInstance().LeerEvento());
        
 
        
////        //Probando siguiente paso completado
////        System.out.println("\n*Probando siguiente paso completado");
////        Juego.siguientePasoCompletado(OperacionesJuego.AVANZAR);
////        while (Diario.getInstance().EventosPendientes())
////            System.out.println(Diario.getInstance().LeerEvento());
        
        //Probando getCasillaActual
////        System.out.println("\n*Probando get casilla actual: ");
////        System.out.println(Juego.getCasillaActual().toString());
        
        
        //Probando actualizar info y ranking y final del juego
////        System.out.println("\n*Probando actualizar info y ranking y final(jugador en bancarrota)");
////        Juego.jugadores.get(3).paga(750000);
////        Juego.jugadores.get(1).paga(2000);
////        System.out.println(Juego.actualizarInfo());

        //Probando hipotecar y cancelarlo
//        Juego.comprar();
//        Juego.hipotecar(0);
//        Juego.cancelarHipoteca(0);
//        
//        //Probando construir casa/hotel
//        Juego.getJugadorActual().paga(-99999);
//        Juego.construirCasa(0);
//        Juego.construirCasa(0);
//        Juego.construirCasa(0);
//        Juego.construirCasa(0);
//        
//        Juego.construirHotel(0);
       
        //System.out.println(Juego.getJugadorActual().cantidadCasasHoteles());
        
        //Probando recibeJugador
                
        Dado.getInstance().setDebug(true);
        
        //Juego.tablero.casillas.get(5).getTituloPropiedad().actualizaPropietarioPorConversion(new Jugador("Jorge"));
        
        
        Juego.avanzaJugador();//carcel
        Juego.avanzaJugador();//Caja de Comunidad
//        Juego.avanzaJugador();//descanso
//        Juego.avanzaJugador();//murcia
//        Juego.avanzaJugador();//impuesto
//        Juego.avanzaJugador();//paseo del prado
//        System.out.println("Comprada: " + Juego.comprar());
//        Juego.avanzaJugador();//juez
//        Juego.salirCarcelPagando();
        
    
        System.out.println(Juego.siguientePaso().name());
        
        //Leer diario
        while (Diario.getInstance().EventosPendientes())
            System.out.println("\t[DIARIO] - " + 
                                            Diario.getInstance().LeerEvento());
        
        //System.out.println("\n" + Juego.actualizarInfo());
    }
}