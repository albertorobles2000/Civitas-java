package civitas;

//import civitas.Jugador;

public class TituloPropiedad {
   
    //Atributos de referencia
    private Jugador propietario;
    
    //Atributos de clase
    static final float factorInteresesHipoteca = 1.1f;
    
    //Atributos de instancia
    private float alquilerBase;
    private float factorRevalorizacion;
    private float hipotecaBase;
    private boolean hipotecado;
    private String nombre;
    private int numCasas;
    private int numHoteles;
    private float precioCompra;
    private float precioEdificar;
    
    
    //-------------------------------------------------------------
    //Métodos
    void actualizaPropietarioPorConversion(Jugador jugador){
         propietario = jugador;
    }
    
    boolean cancelarHipoteca(Jugador jugador){
        boolean cancelada = false;
        
        if (getHipotecado() && esEsteElPropietario(jugador)){
            jugador.paga(getImporteCancelarHipoteca());
            hipotecado = false;
            cancelada = true;
        }
        
        return cancelada;
    }
    
    int cantidadCasasHoteles(){
        return getNumCasas()+getNumHoteles();
    }
    
    boolean comprar(Jugador jugador){
        boolean operacion = false;
        
        if (!tienePropietario()){
            jugador.paga(precioCompra);
            propietario = jugador;
            operacion = true;
        }
        
        return operacion;
    }
    
    boolean construirCasa (Jugador jugador){
        
        boolean construida = false;
        
        if(esEsteElPropietario(jugador)){
            jugador.paga(precioEdificar);
            numCasas++;
            construida = true;
        }
        
        return construida;
       
    }
    
    boolean construirHotel (Jugador jugador){
        boolean construido = false;
        
        if(esEsteElPropietario(jugador)){
            jugador.paga(precioEdificar);
            numHoteles++;
            construido = true;
        }
        
        return construido;
       
    }
    
    boolean derruirCasas( int n, Jugador jugador){
        
        boolean operacion_completada = false;
        
        if(esEsteElPropietario(jugador) && getNumCasas()>=n){
            numCasas-=n;
            operacion_completada = true;
        }
        
        return operacion_completada;
    }
    
    private boolean esEsteElPropietario(Jugador jugador){
        return propietario==jugador;
    }
    
    public boolean getHipotecado(){
        return hipotecado;
    }
    
    float getImporteCancelarHipoteca(){
        return getImporteHipoteca()*factorInteresesHipoteca;
    }
    
    private float getImporteHipoteca(){
        float importe;
        importe = hipotecaBase*((numCasas*0.5f)+1f+(2.5f*numHoteles));
        
        return importe;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getNumCasas(){
        return numCasas;
    }
    
    public int getNumHoteles(){
        return numHoteles;
    }
    
    private float getPrecioAlquiler(){
        float precio;
        precio = alquilerBase*(1f+(numCasas*0.5f)+(numHoteles*2.5f));
        return precio;
    }
    
    float getPrecioCompra(){
        return precioCompra;
    }
    
    float getPrecioEdificar(){
        return precioEdificar;
    }
    
    private float getPrecioVenta(){
        float precio_venta = precioCompra + (numCasas+5*numHoteles)
                                            * precioEdificar
                                            * factorRevalorizacion;
        
        return precio_venta;
    }
    
    Jugador getPropietario(){
        return propietario;
    }
    
    boolean hipotecar (Jugador jugador){
        boolean operacion_completada = false;
        
        if(!hipotecado && esEsteElPropietario(jugador)){
            jugador.recibe(getImporteHipoteca());
            hipotecado = true;
            operacion_completada = true;
        }
        
        return operacion_completada;
    }
    
    private boolean propietarioEncarcelado(){
        
        boolean encarcelado = false;
        
        if (tienePropietario())
            if (getPropietario().isEncarcelado())
                encarcelado = true;
        
        return encarcelado;
    }
    
    boolean tienePropietario(){
        return (propietario!=null);
    }
    
    TituloPropiedad(String nom, Float ab, Float fr, Float hb, Float pc, 
                Float pe)
    {
        nombre = nom;
        alquilerBase = ab;
        factorRevalorizacion = fr;
        hipotecaBase = hb;
        precioCompra = pc;
        precioEdificar = pe;
        hipotecado = false;
        numCasas = 0;
        numHoteles = 0;
        propietario = null;
    }
    
    @Override
    public String toString (){
        String representacion;
        
        representacion = "Titulo Propiedad: \n"
                        +"\t- Nombre = " + nombre + "\n"
                        +"\t- Alquiler base = " + alquilerBase + "\n"
                        +"\t- Factor de revalorización = " + factorRevalorizacion + "\n"
                        +"\t- Hipoteca base = " + hipotecaBase + "\n"
                        +"\t- Precio de compra = " + precioCompra + "\n"
                        +"\t- Precio de edificar = " + precioEdificar + "\n"
                        +"\t- Número de casas = " + numCasas + "\n"
                        +"\t- Número de hoteles = " + numHoteles + "\n";
        
        if (propietario == null)
            representacion += "\t- Propietario = sin propietario\n";
        
        else
            representacion+= "\t- Propietario = " + propietario.getNombre();
        
        return representacion;
    }
    
    void tramitarAlquiler(Jugador jugador){
        
        if (tienePropietario() && !esEsteElPropietario(jugador)){
            float alquiler = getPrecioAlquiler();
            jugador.pagaAlquiler(alquiler);
            getPropietario().recibe(alquiler);
        }
    }
    
    boolean vender(Jugador jugador){
       
        boolean operacion_completada = false;
        
        if (!hipotecado && 
                esEsteElPropietario(jugador)){
            
            //Recibo la venta
            propietario.recibe(getPrecioVenta());
            
            //Desvinculo la propiedad del jugador
            propietario = null;
            
            //Reinicio el titulo
            numCasas = 0;
            numHoteles = 0;
            
            operacion_completada = true;
        }
        
        return operacion_completada;

    }
    
    //-------------------------------------------------------------
    public static void main (String args[]){
        
        //Pruebo constructor
        System.out.println("*Probando constructor...");
        TituloPropiedad Titulo1 = new TituloPropiedad("Paseo del Prado",
                50.0f,100.0f,2000.0f,4000.0f,1000.0f);
        System.out.println(Titulo1.toString());
        
        //Get
        System.out.println("*Probando métodos get...");
        
        //Sigo en una clase así que puedo llamar a método privado
        System.out.println(Titulo1.getHipotecado());
        System.out.println(Titulo1.getImporteCancelarHipoteca());
        System.out.println(Titulo1.getImporteHipoteca());
        System.out.println(Titulo1.getNombre());
        System.out.println(Titulo1.getNumCasas());
        System.out.println(Titulo1.getNumHoteles());
        System.out.println(Titulo1.getPrecioAlquiler());
        System.out.println(Titulo1.getPrecioCompra());
        System.out.println(Titulo1.getPrecioEdificar());
        System.out.println(Titulo1.getPrecioVenta());
        System.out.println(Titulo1.getPropietario());
        
        //Métodos relacionados con jugador
        Jugador j1 = new Jugador("Jesus");
        Jugador j2 = new Jugador ("Nuria");
        
        //Resto de métodos
        
        Titulo1.actualizaPropietarioPorConversion(j1);
        System.out.println("*Actualizar propietario a jesus --> " 
                            + Titulo1.getPropietario());
        
        Titulo1.numCasas = 8;
        Titulo1.numHoteles = 9;
        System.out.println("*Cantidad de casas y hoteles--> " 
                            + Titulo1.cantidadCasasHoteles());
        
        
        System.out.println("*Propietario Encarcelado --> " 
                            + Titulo1.propietarioEncarcelado());
        
        System.out.println("*Tiene propietario --> " 
                            + Titulo1.tienePropietario());
        
        
        //Necesito que los jugadores no esten en la carcel y que j2 no sea propietario y j1 si
        j1.encarcelado = false;
        j2.encarcelado = false;
        Titulo1.tramitarAlquiler(j2);
        System.out.println("*Tramitar alquiler --> j2 cae en casilla de j1, aparecerá en diario.");
        
        System.out.println("*Hipotecar --> " 
                            + Titulo1.hipotecar(j1));
        
        System.out.println("*Es este el propietario j1 --> " 
                            + Titulo1.esEsteElPropietario(j1));
        
        System.out.println("*Derruir casas --> " 
                            + Titulo1.derruirCasas(7, j1));
        
        System.out.println("*Cosntruir hotel --> " 
                            + Titulo1.construirHotel(j1));
        
        System.out.println("*Construir casa --> " 
                            + Titulo1.construirCasa(j1));
        
        TituloPropiedad Titulo2 = new TituloPropiedad("Avenida Andalucía",
                50.0f,100.0f,2000.0f,4000.0f,1000.0f);
        System.out.println("*Comprar --> " 
                            + Titulo2.comprar(j1));
        
        System.out.println(Titulo1);
        System.out.println("*Hipotecado--> " + Titulo1.getHipotecado());
        System.out.println("*Cancelar Hipoteca --> " 
                            + Titulo1.cancelarHipoteca(j1));
        System.out.println("*Hipotecado--> " + Titulo1.getHipotecado());
        
        //Leer diario
        System.out.println("*Leyendo el diario....");
        while (Diario.getInstance().EventosPendientes())
            System.out.println(Diario.getInstance().LeerEvento());
    }
    //-------------------------------------------------------------
    //-------------------------------------------------------------
}