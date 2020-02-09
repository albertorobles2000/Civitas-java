/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
/**
 *
 * @author alberto
 */
public class JugadorEspeculador extends Jugador{
    
    private static final int FactorEspeculador=2;
    
    private int fianza;
    
    protected JugadorEspeculador(Jugador jugador, int fianza){
        super(jugador);
        this.fianza = fianza;
        for(int i=0; i<jugador.getPropiedades().size(); i++){
            jugador.getPropiedades().get(i).actualizaPropietarioPorConversion(this);
        }
    }
    
    @Override
    protected boolean debeSerEncarcelado() {
        return super.debeSerEncarcelado() && !pagarFianza();
    }
    
    private boolean pagarFianza() {
        boolean pagar_fianza = false;

        if(super.getSaldo() > fianza) {
            modificarSaldo(-fianza);
            pagar_fianza = true;
        }

        return pagar_fianza;
    }
    
    @Override
    protected boolean puedoEdificarCasa(TituloPropiedad titulo) {
        return (titulo.getNumCasas() < getCasasMax());
    }
    
    @Override
    protected boolean puedoEdificarHotel(TituloPropiedad titulo) {
        return (titulo.getNumCasas() > super.getCasasPorHotel() &&
                titulo.getNumHoteles() < getHotelesMax());
    }
    
    @Override
    boolean pagaImpuesto(float cantidad){
        return super.pagaImpuesto(cantidad/2);
    }
    
    @Override
    public int getHotelesMax(){
        return (super.getHotelesMax() * FactorEspeculador );
    }

    @Override
    public int getCasasMax(){
        return (super.getCasasMax() * FactorEspeculador );
    }
    
    @Override
    public boolean isEspeculador(){
        return true;
    }
    
}
