/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Filme;
import classes.Item;
import classes.Nota;
import classes.Usuario;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelFilme extends javax.swing.JPanel {

    /**
     * Creates new form PanelFilme
     */
    
    HashMap<Integer, Usuario> mapUsuario;
    HashMap<Integer, Item> mapItem;
    ArrayList<Nota> notasTotal = new ArrayList();
    Usuario usuario;
    Item filme;
    JPanel panelMaster;
    
    public PanelFilme(JPanel panelMaster,Item movie, Usuario user, HashMap<Integer, Item> mapMovie, HashMap<Integer, Usuario> mapUser, ArrayList<Nota> notasTotal) throws IOException {
        
        filme = movie;
        usuario = user;
        mapUsuario = mapUser;
        mapItem = mapMovie;
        
        this.panelMaster=panelMaster;
        initComponents();
        
        cbbNota.setSelectedIndex(mostrarNota());
        
        lblImg.setIcon(pegarImg(filme.getPoster(300)));
        lblTitulo.setText(filme.getNome());
        lblAno.setText(Integer.toString(filme.getAno()));
        jTxtPaneSinopse.setText(filme.getSinopse());
        txtMedia.setText(Float.toString(filme.getMedia()));
        
        jTxtPaneSinopse.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                jTxtPaneSinopse.setEditable(true);

            }

            @Override
            public void focusGained(FocusEvent e) {
                jTxtPaneSinopse.setEditable(false);

            }   
        });
    }

   

    
    
    public ImageIcon pegarImg(String img) throws MalformedURLException, IOException{
        URL url = new URL(img);
        Image image = null;
        try {
            image = ImageIO.read(url);
          } catch (IIOException e) {
              image = ImageIO.read(new File("./src/images/bob300.jpg"));
          }
        ImageIcon imgI = new ImageIcon(image);
        return imgI;
    }
    
    private int mostrarNota(){
        /* Pegando as notas dadas pelo usuario */
        ArrayList<Nota> notasDadas = new ArrayList();
        notasDadas = usuario.getNotas();
        
        /* Verificando se o usuario avaliou o filme */
        for(int i = 0; i < notasDadas.size(); i++){
            /* O Usuario avaliou */
            if(notasDadas.get(i).getFilme().getId().equals(filme.getId())){
                System.out.println("O Usuario avaliou este filme. Nota: " + notasDadas.get(i).getNota());
                return notasDadas.get(i).getNota();
            }
        }
        /* Não avaliou */
        System.out.println("O usuario não avaliou o filme");
        return 0;
    }
    
    private void ModificarNota(){
        
        int notaNova;
        
        for(int i = 0; i< notasTotal.size(); i++){
            if((notasTotal.get(i).getUsuario().getId().equals(usuario.getId())) && (notasTotal.get(i).getFilme().getId().equals(filme.getId()))){
                System.out.println("O Usuario Votou neste filme. Nota Dada: " + notasTotal.get(i).getNota());
                notaNova = cbbNota.getSelectedIndex();
                if(notaNova != notasTotal.get(i).getNota()){
                    notasTotal.get(i).setNota(notaNova);
                    usuario.modNotas(notasTotal.get(i));
                    filme.modNotas(notasTotal.get(i));
                    mapItem.put(Integer.parseInt(filme.getId()), filme);
                    mapUsuario.put(Integer.parseInt(usuario.getId()), usuario);
                }
                Nota.SerializeNota(notasTotal);
                Usuario.SerializeUsuario(mapUsuario);
                Item.SerializeItem(mapItem);
                break;
            } else if(i == notasTotal.size() - 1){
                if(cbbNota.getSelectedIndex() == 0)
                {
                    System.out.println("O usuario não votou neste filme.");
                }
                if(cbbNota.getSelectedIndex() > 0)
                {
                    System.out.println("O usuario acabou de votar neste filme. Nota: " + cbbNota.getSelectedIndex());
                    Nota novaNota = new Nota(usuario, (Filme)filme, cbbNota.getSelectedIndex());
                    notasTotal.add(novaNota);
                    System.out.println(notasTotal.size());
                    filme.setNotas(novaNota);
                    usuario.setNotas(novaNota);
                    mapItem.put(Integer.parseInt(filme.getId()), filme);
                    mapUsuario.put(Integer.parseInt(usuario.getId()), usuario);
                    Usuario.SerializeUsuario(mapUsuario);
                    Filme.SerializeItem(mapItem);
                    Nota.SerializeNota(notasTotal);
                }
            }
        }
    }
    

    /*PanelFilme(Filme filme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImg = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxtPaneSinopse = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblAno = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        cbbNota = new javax.swing.JComboBox();
        btnAssistir = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblVisual = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMedia = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1024, 786));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setText("Titulo do Filme:");

        jTxtPaneSinopse.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTxtPaneSinopse.setText("Armed with a super-suit with the astonishing ability to shrink in scale but increase in strength, con-man Scott Lang must embrace his inner hero and help his mentor, Dr. Hank Pym, plan and pull off a heist that will save the world.");
        jScrollPane1.setViewportView(jTxtPaneSinopse);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setText("Média:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel4.setText("Ano:");

        lblAno.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        cbbNota.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sem Nota", "1", "2", "3", "4", "5" }));
        cbbNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNotaActionPerformed(evt);
            }
        });

        btnAssistir.setText("Assistir");
        btnAssistir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssistirActionPerformed(evt);
            }
        });

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setText("Avaliações:");

        lblVisual.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setText("Nota:");

        txtMedia.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbbNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(58, 58, 58)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblAno, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAssistir)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(btnVoltar, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(btnVoltar)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(39, 39, 39)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbbNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(lblVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAssistir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(256, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAssistirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssistirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAssistirActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
                 
            
            this.setVisible(false); 
        try {
            PanelPrincipal panel = new PanelPrincipal(panelMaster,mapUsuario,usuario, notasTotal, mapItem);
            //panel.setLayout(new BorderLayout());
         this.setVisible(false);
        
        
        panelMaster.setLayout(new BorderLayout());
         panelMaster.add(panel,BorderLayout.NORTH);
         panelMaster.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(PanelFilme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PanelFilme.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void cbbNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNotaActionPerformed
        // TODO add your handling code here:
        
        ModificarNota();
    }//GEN-LAST:event_cbbNotaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAssistir;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox cbbNota;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTxtPaneSinopse;
    private javax.swing.JLabel lblAno;
    private javax.swing.JLabel lblImg;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVisual;
    private javax.swing.JTextField txtMedia;
    // End of variables declaration//GEN-END:variables
}
