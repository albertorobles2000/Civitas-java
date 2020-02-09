package civitas;

//import civitas.Sorpresa;

import java.util.ArrayList;
import java.util.Collections;


public class MazoSorpresas {
   
    //Atributos de instancia privados
    private ArrayList<Sorpresa> sorpresas;
    private boolean barajada;
    private int usadas;
    private boolean debug;
    private ArrayList<Sorpresa> cartasEspeciales;
    private Sorpresa ultimaSorpresa;
    
    //------------------------------------------------------
    //Metodos privados
    private void init(){
        sorpresas = new ArrayList<> ();
        cartasEspeciales = new ArrayList<> ();
        barajada = false;
        usadas = 0;
    }
 
    //------------------------------------------------------
    //Constructor
    MazoSorpresas (boolean d){
        debug = d;
        init();
        
        if (d){
            Diario.getInstance().OcurreEvento("El mazo sorpresas ahora está"+
                                " en modo " + d);
            
        }
    }
    
    MazoSorpresas (){
        init();
        debug = false;
    }
    
    //------------------------------------------------------
    //Resto de metodos
    void alMazo(Sorpresa s){
        if (!barajada)
            sorpresas.add(s);
    }
    
    Sorpresa getUltimaSorpresa(){
        return ultimaSorpresa;
    }
    
    Sorpresa siguiente(){
        if ((!barajada || usadas==(sorpresas.size() + cartasEspeciales.size()))
                &&(!debug)){
            Collections.shuffle(sorpresas);
            usadas =0;
            barajada = true;
        }
        
        usadas++;
        
        //Retiro primera carta y la añado al final
        ultimaSorpresa = sorpresas.remove(0);
        sorpresas.add(ultimaSorpresa);
        
        return ultimaSorpresa;
    }
    
    void inhabilitarCartaEspecial(Sorpresa sorpresa){
        
        int tam = sorpresas.size();
        int pos = sorpresas.indexOf(sorpresa);
        
        if (pos!=-1){
            sorpresas.remove(pos);
        
            Diario.getInstance().OcurreEvento("Elimino de sorpresas la carta"
                    + " " + sorpresa.toString());

            cartasEspeciales.add(sorpresa);
        }
    }
    
    void habilitarCartaEspecial (Sorpresa sorpresa){
        
        int tam = cartasEspeciales.size();
        int pos = cartasEspeciales.indexOf(sorpresa);
        
        if (pos!=-1){
            cartasEspeciales.remove(pos);
            sorpresas.add(sorpresa);
            Diario.getInstance().OcurreEvento("Se ha añadido la sorpresa"+
                        " " + sorpresa.toString() + " al mazo");
            
        }
    }
    //------------------------------------------------------
    //------------------------------------------------------
    //------------------------------------------------------
  
}