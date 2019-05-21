/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;
import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author master
 */
public class frameManuale extends javax.swing.JFrame {

    /**
     * Creates new form frameManuale
     */
    OpenCVFrameGrabber grabber;
    GestioneTCP gestione;

    public frameManuale() {
        initComponents();
        gestione = new GestioneTCP();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonHome = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonSu = new javax.swing.JButton();
        jButtonGiu = new javax.swing.JButton();
        jButtonDestra = new javax.swing.JButton();
        jButtonSinistra = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jButtonHome.setText("HOME");
        jButtonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHomeActionPerformed(evt);
            }
        });

        jLabel1.setText("MANUALE");

        jButtonSu.setText("SU");
        jButtonSu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuActionPerformed(evt);
            }
        });

        jButtonGiu.setText("GIU");
        jButtonGiu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGiuActionPerformed(evt);
            }
        });

        jButtonDestra.setText("DESTRA");
        jButtonDestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDestraActionPerformed(evt);
            }
        });

        jButtonSinistra.setText("SINISTRA");
        jButtonSinistra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSinistraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(jLabel1)
                        .addGap(0, 318, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonSu, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jButtonGiu, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDestra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSinistra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonHome)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHome)
                    .addComponent(jButtonDestra)
                    .addComponent(jButtonSinistra)
                    .addComponent(jButtonGiu)
                    .addComponent(jButtonSu))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHomeActionPerformed
        // BOTTONE HOME           

        Frame frameHome = new home();
        frameHome.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonHomeActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Thread t = new Thread() {
            @Override
            public void run() {
                startCam("rtsp://192.168.1.4:554/MediaInput/h264", jLabel2);
            }
        };
        t.start();
    }//GEN-LAST:event_formWindowOpened

    
    public void mandaMessaggio(String messaggio)
    {
       gestione.ClientManda(messaggio);
    }
    
    
    private void jButtonSuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuActionPerformed
        // TODO add your handling code here:
        //su         
        gestione.MyTCPClient("192.168.43.66" ,3333);
        mandaMessaggio("MUOVI,w");


    }//GEN-LAST:event_jButtonSuActionPerformed

    private void jButtonGiuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGiuActionPerformed
        // TODO add your handling code here:
        //giu     
        gestione.MyTCPClient("192.168.43.66" ,3333);
        mandaMessaggio("MUOVI,s");       
        
    }//GEN-LAST:event_jButtonGiuActionPerformed

    private void jButtonDestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDestraActionPerformed
        // TODO add your handling code here:        
        //destra             
        gestione.MyTCPClient("192.168.43.66" ,3333);
        mandaMessaggio("MUOVI,d");
        
    }//GEN-LAST:event_jButtonDestraActionPerformed

    private void jButtonSinistraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSinistraActionPerformed
        // TODO add your handling code here:
        //sinistra
        gestione.MyTCPClient("192.168.43.66" ,3333);
        mandaMessaggio("MUOVI,a");
        
    }//GEN-LAST:event_jButtonSinistraActionPerformed

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        
        gestione.MyTCPClient("192.168.43.66" ,3333);
        mandaMessaggio("MODALITA,0");
    }//GEN-LAST:event_formWindowDeactivated

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

        gestione.MyTCPClient("192.168.43.66" ,3333);
        mandaMessaggio("MODALITA,1");
                        
    }//GEN-LAST:event_formWindowActivated
    public void startCam(String NomeIpCam, JLabel NomeLabelIpCam) {
        try {
            grabber = new OpenCVFrameGrabber(NomeIpCam);
            grabber.setImageWidth(300);
            grabber.setImageHeight(200);
            grabber.start();
            while (true) {
                opencv_core.IplImage originalImage = grabber.grab();
                int WIDHT = NomeLabelIpCam.getWidth();
                int HEIGHT = NomeLabelIpCam.getHeight();
                opencv_core.IplImage resizedImage = opencv_core.IplImage.create(WIDHT, HEIGHT, originalImage.depth(), originalImage.nChannels());
                cvResize(originalImage, resizedImage);

                ImageIcon imageIcon = new ImageIcon(resizedImage.getBufferedImage());
                NomeLabelIpCam.setIcon(imageIcon);

            }

        } catch (FrameGrabber.Exception ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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
            java.util.logging.Logger.getLogger(frameManuale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frameManuale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frameManuale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frameManuale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frameManuale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDestra;
    private javax.swing.JButton jButtonGiu;
    private javax.swing.JButton jButtonHome;
    private javax.swing.JButton jButtonSinistra;
    private javax.swing.JButton jButtonSu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
