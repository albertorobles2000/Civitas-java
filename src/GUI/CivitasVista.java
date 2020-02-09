/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author albertorobles
 */

package GUI;

import civitas.CivitasJuego;
import javax.swing.JOptionPane;
import civitas.SalidasCarcel;
import java.util.ArrayList;
import civitas.Jugador;
import java.awt.Color;

public class CivitasVista extends javax.swing.JFrame {
    CivitasJuego juego;
    JugadorVista jugadorvista;
    GestionarDialog gestionarD;
    DiarioDialog diarioD;

    /**
     * Creates new form CivitasVista
     */
    public CivitasVista() {
        initComponents();
        jugadorvista = new JugadorVista();
        contenedorvistajugador.add (jugadorvista);
        repaint();
        revalidate();
    }
    
    
    void ActualizarVista(){
        this.getContentPane().setBackground(Color.lightGray);
        jugadorvista.setJugador(juego.getJugadorActual());
        casilla.setText(juego.getCasillaActual().toString());
        siguiente.setText(""+juego.siguientePaso());
        ranking.setVisible(false);
        etiqueta_ranking.setVisible(false);
        if(juego.finalDelJuego()){
           ArrayList<Jugador> v_ranking = juego.ranking();
           String salida = "";
           for(int i=0; i<v_ranking.size(); i++){
               salida += (i+1) + "º -> " + v_ranking.get(i).getNombre() + "\n";
           }
           ranking.setText(salida);
           ranking.setVisible(true);
           etiqueta_ranking.setVisible(true); 
        }
        mostrarEventos();
        repaint();
        revalidate();
    }
    
    void gestionar(){
        gestionarD = new GestionarDialog(this);
        gestionarD.gestionar(juego.getJugadorActual());
        gestionarD.pack();
        gestionarD.repaint();
        gestionarD.revalidate();
        gestionarD.setVisible(true);
    }
    
    int getGestion(){
        return gestionarD.getGestion();
    }
    
    int getPropiedad(){
        return gestionarD.getPropiedad();
    }

    void setCivitasJuego(CivitasJuego jug){
        juego = jug;
        setVisible(true);
    }
    
    void mostrarEventos() {
            diarioD = new DiarioDialog(this); //crea la ventana del diario
    }
    
    Respuestas comprar(){
        int opcion= JOptionPane.showConfirmDialog(null, "¿Quieres comprar la calle actual?",
        "Compra", JOptionPane.YES_NO_OPTION);
        if(opcion == 0)
            return Respuestas.SI;
        else
            return Respuestas.NO;
    }
    
    SalidasCarcel salirCarcel(){
        String[] opciones= {"Pagando", "Tirando"};
        int respuesta= JOptionPane.showOptionDialog(null, "¿Cómo quieres salir de la cárcel?",
        "Salir de la cárcel", JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,null, opciones, opciones[0] );
        if(respuesta == 0)
            return SalidasCarcel.PAGANDO;
        else
            return SalidasCarcel.TIRANDO;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        contenedorvistajugador = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        siguiente = new javax.swing.JLabel();
        etiqueta_ranking = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        casilla = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        ranking = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setEnabled(false);

        titulo.setBackground(new java.awt.Color(0, 238, 238));
        titulo.setForeground(new java.awt.Color(40, 40, 40));
        titulo.setText("CIVITAS");
        titulo.setToolTipText("");
        titulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        contenedorvistajugador.setForeground(new java.awt.Color(100, 0, 51));

        jLabel1.setText("CASILLA ACTUAL:");
        jLabel1.setEnabled(false);

        jLabel2.setText("SIGUIENTE OP.:");
        jLabel2.setEnabled(false);

        siguiente.setText("jLabel3");

        etiqueta_ranking.setText("RANKING:");
        etiqueta_ranking.setEnabled(false);

        casilla.setColumns(20);
        casilla.setRows(5);
        jScrollPane1.setViewportView(casilla);

        ranking.setColumns(20);
        ranking.setRows(5);
        jScrollPane2.setViewportView(ranking);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenedorvistajugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(etiqueta_ranking))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 159, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenedorvistajugador, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siguiente)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiqueta_ranking)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        titulo.getAccessibleContext().setAccessibleName("titulo");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CivitasVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CivitasVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CivitasVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CivitasVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CivitasVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea casilla;
    private javax.swing.JPanel contenedorvistajugador;
    private javax.swing.JLabel etiqueta_ranking;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea ranking;
    private javax.swing.JLabel siguiente;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}