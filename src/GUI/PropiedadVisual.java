/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import civitas.TituloPropiedad;

/**
 *
 * @author alberto
 */
public class PropiedadVisual extends javax.swing.JPanel {
    private TituloPropiedad propiedad;
    /**
     * Creates new form PropiedadVisual
     */
    public PropiedadVisual() {
        initComponents();
    }

    public void setPropiedad(TituloPropiedad prop){
        propiedad = prop;
        prop_nombre.setText(prop.getNombre());
        prop_casas.setText(""+prop.getNumCasas());
        prop_hoteles.setText(""+prop.getNumHoteles());
       
        if(prop.getHipotecado()){
            prop_hipotecada.setText("SI");
        }
        else{
            prop_hipotecada.setText("NO");
        }
        
        repaint();
        revalidate();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        prop_nombre = new javax.swing.JLabel();
        prop_casas = new javax.swing.JLabel();
        prop_hoteles = new javax.swing.JLabel();
        prop_hipotecada = new javax.swing.JLabel();

        jLabel1.setText("PROPIEDAD:");
        jLabel1.setEnabled(false);

        jLabel2.setText("CASAS:");
        jLabel2.setEnabled(false);

        jLabel3.setText("HOTELES:");
        jLabel3.setEnabled(false);

        jLabel4.setText("HIPOTECADA:");
        jLabel4.setEnabled(false);

        prop_nombre.setText("jLabel5");

        prop_casas.setText("jLabel6");

        prop_hoteles.setText("jLabel7");

        prop_hipotecada.setText("jLabel8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prop_hipotecada))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prop_nombre))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prop_casas))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prop_hoteles))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(prop_nombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(prop_casas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(prop_hoteles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(prop_hipotecada)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel prop_casas;
    private javax.swing.JLabel prop_hipotecada;
    private javax.swing.JLabel prop_hoteles;
    private javax.swing.JLabel prop_nombre;
    // End of variables declaration//GEN-END:variables
}