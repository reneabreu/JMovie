/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Filme;
import classes.Item;
import classes.Nota;
import classes.Recomendacao;
import classes.Usuario;
import java.awt.BorderLayout;
import java.awt.Image;
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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelPrincipal extends javax.swing.JPanel {

    /**
     * Creates new form PanelPrincipal
     */
    
    private HashMap <Integer, Item> mapItens;
    private HashMap <Integer, Usuario> mapUsuario;
    private ArrayList<Nota> notasTotal = new ArrayList();
    
    private Item[] filmesRecomendados;
    private Item[] filmesMaisVotados;
    private Item[] ultimosFilmes;
    private HashMap <Integer, Usuario> mUsuario;
    private Usuario usuario;
    private Recomendacao recomendacao;
    
    private JPanel panelMaster;
    
    public ImageIcon pegarImg(String img) throws MalformedURLException, IOException{ //converte a url em imagem
        
        System.out.println("Pegando imagem...  : " +img);  
        
        Image image = null;
        try {
            URL url = new URL(img);
            image = ImageIO.read(url);
          } catch (IIOException e) {                                     //se nao houver conexao com a internet
              image = ImageIO.read(new File("./src/images/bob.jpg"));
          } catch (MalformedURLException m){                             //se o filme estiver sem capa
              image = ImageIO.read(new File("./src/images/semcapa.jpg"));
          }        
        
        ImageIcon imgI = new ImageIcon(image);
        return imgI;
    }
    
    public void reorganizaMelhoresFilmes(int i,Item[] item){
        int cont=6;
        while(cont>i){
            item[cont]=item[cont-1];
            cont--;
        }
    }
    
    public PanelPrincipal(JPanel panelMaster, HashMap<Integer, Usuario> mapUsuario,Usuario usuario, ArrayList<Nota> notasTotal, HashMap<Integer, Item> mapItens) throws IOException, ParseException, Exception {
        
        this.mapUsuario = mapUsuario;
        this.mapItens = mapItens;
        this.notasTotal = notasTotal;
        
        filmesRecomendados = new Filme[7];
        filmesMaisVotados = new Filme[7];
        ultimosFilmes = new Filme[7];
        
        initComponents();
        
        mUsuario = mapUsuario;
        this.usuario = usuario;
        
        recomendacao = new Recomendacao(mUsuario,mapItens,this.usuario); //cria a recomendacao
         
        
        this.panelMaster = panelMaster;
        
       /* for(int i = 0;i<mapItens.size();i++){
            System.out.println("Pegando os filmes MaisVotados ");
            filmesMaisVotados[i] = mapItens.get(i+1);
        }
        
        Arrays.sort(filmesMaisVotados, Collections.reverseOrder());*/
        for(int ii = 0; ii<7; ii++){            
            filmesRecomendados[ii] = recomendacao.getRecomendados().get(ii);            
        }
        
        melhoresFilmes();
        //filmesMaisVotados = ultimosFilmes;
        for(int i = 0; i<7; i++){
            ultimosFilmes[i] = mapItens.get((i+1));
            if(i>=7)
                break;
        }
        preencheTela();
        
        //System.out.println(mapItens.get(0).getNome());
        
         
    }
    
    public void melhoresFilmes(){
        filmesMaisVotados = new Filme[7];
        
       
        
        for(int i = 0;i<mapItens.size();i++){ // funçao que ve os filmes mais bem votados
            
            //filmesMaisVotados[i] = mapItens.get(i+1);
            
                if(i<7){
                    filmesMaisVotados[i] = mapItens.get(i+1);
                }else{            
                    if(mapItens.get(i+1).getMedia()<filmesMaisVotados[6].getMedia()){

                    }else if(mapItens.get(i+1).getMedia()<filmesMaisVotados[5].getMedia()){
                        filmesMaisVotados[6] = mapItens.get(i+1);
                    }else if(mapItens.get(i+1).getMedia()<filmesMaisVotados[4].getMedia()){
                        reorganizaMelhoresFilmes(5,filmesMaisVotados);
                        filmesMaisVotados[5] = mapItens.get(i+1);
                    }else if(mapItens.get(i+1).getMedia()<filmesMaisVotados[3].getMedia()){
                        reorganizaMelhoresFilmes(4,filmesMaisVotados);
                        filmesMaisVotados[4] = mapItens.get(i+1);
                    }else if(mapItens.get(i+1).getMedia()<filmesMaisVotados[2].getMedia()){
                        reorganizaMelhoresFilmes(3,filmesMaisVotados);
                        filmesMaisVotados[3] = mapItens.get(i+1);
                    }else if(mapItens.get(i+1).getMedia()<filmesMaisVotados[1].getMedia()){
                        reorganizaMelhoresFilmes(2,filmesMaisVotados);
                        filmesMaisVotados[2] = mapItens.get(i+1);
                    }else if(mapItens.get(i+1).getMedia()<filmesMaisVotados[0].getMedia()){
                        reorganizaMelhoresFilmes(1,filmesMaisVotados);
                        filmesMaisVotados[1] = mapItens.get(i+1);
                    }else{
                        reorganizaMelhoresFilmes(0,filmesMaisVotados);
                        filmesMaisVotados[0] = mapItens.get(i+1);
                    }
                }
            
        }
        
    }
    
    public void preencheTela() throws IOException{
        //System.out.println(mapItens.get(0).getUrl());
        
        /* Filmes Recomendados */
        this.imgRecomendacao1.setIcon(pegarImg(filmesRecomendados[0].getPoster(120)));
        lblRecomendacao1.setText(filmesRecomendados[0].getNome());
        
        this.imgRecomendacao2.setIcon(pegarImg(filmesRecomendados[1].getPoster(120)));
        lblRecomendacao2.setText(filmesRecomendados[1].getNome());
        
        this.imgRecomendacao3.setIcon(pegarImg(filmesRecomendados[2].getPoster(120)));
        lblRecomendacao3.setText(filmesRecomendados[2].getNome());
        
        this.imgRecomendacao4.setIcon(pegarImg(filmesRecomendados[3].getPoster(120)));
        lblRecomendacao4.setText(filmesRecomendados[3].getNome());
        
        this.imgRecomendacao5.setIcon(pegarImg(filmesRecomendados[4].getPoster(120)));
        lblRecomendacao5.setText(filmesRecomendados[4].getNome());
        
        this.imgRecomendacao6.setIcon(pegarImg(filmesRecomendados[5].getPoster(120)));
        lblRecomendacao6.setText(filmesRecomendados[5].getNome());
        
        this.imgRecomendacao7.setIcon(pegarImg(filmesRecomendados[6].getPoster(120)));
        lblRecomendacao7.setText(filmesRecomendados[6].getNome());
        
        /* Últimos Filmes Assistidos */
        
        this.imgUltimosFilmes1.setIcon(pegarImg(ultimosFilmes[0].getPoster(120)));
        lblAssistidos1.setText(ultimosFilmes[0].getNome());
        
        this.imgUltimosFilmes2.setIcon(pegarImg(ultimosFilmes[1].getPoster(120)));
        lblAssistidos2.setText(ultimosFilmes[1].getNome());
        
        this.imgUltimosFilmes3.setIcon(pegarImg(ultimosFilmes[2].getPoster(120)));
        lblAssistidos3.setText(ultimosFilmes[2].getNome());
        
        this.imgUltimosFilmes4.setIcon(pegarImg(ultimosFilmes[3].getPoster(120)));
        lblAssistidos4.setText(ultimosFilmes[3].getNome());
        
        this.imgUltimosFilmes5.setIcon(pegarImg(ultimosFilmes[4].getPoster(120)));
        lblAssistidos5.setText(ultimosFilmes[4].getNome());
        
        this.imgUltimosFilmes6.setIcon(pegarImg(ultimosFilmes[5].getPoster(120)));
        lblAssistidos6.setText(ultimosFilmes[5].getNome());
        
        this.imgUltimosFilmes7.setIcon(pegarImg(ultimosFilmes[6].getPoster(120)));
        lblAssistidos7.setText(ultimosFilmes[6].getNome());
        
        /* Mais Votados */
        
        this.imgMaisVotados1.setIcon(pegarImg(filmesMaisVotados[0].getPoster(120)));
        lblVotados1.setText(filmesMaisVotados[0].getNome());
        
        this.imgMaisVotados2.setIcon(pegarImg(filmesMaisVotados[1].getPoster(120)));
        lblVotados2.setText(filmesMaisVotados[1].getNome());
        
        this.imgMaisVotados3.setIcon(pegarImg(filmesMaisVotados[2].getPoster(120)));
        lblVotados3.setText(filmesMaisVotados[2].getNome());
        
        this.imgMaisVotados4.setIcon(pegarImg(filmesMaisVotados[3].getPoster(120)));
        lblVotados4.setText(filmesMaisVotados[3].getNome());
        
        this.imgMaisVotados5.setIcon(pegarImg(filmesMaisVotados[4].getPoster(120)));
        lblVotados5.setText(filmesMaisVotados[4].getNome());
        
        this.imgMaisVotados6.setIcon(pegarImg(filmesMaisVotados[5].getPoster(120)));
        lblVotados6.setText(filmesMaisVotados[5].getNome());
        
        this.imgMaisVotados7.setIcon(pegarImg(filmesMaisVotados[6].getPoster(120)));
        lblVotados7.setText(filmesMaisVotados[6].getNome());
        
        
    }
    
    public void mostrarDetalhes(Item movie) throws IOException{
        PanelFilme panel = new PanelFilme(panelMaster,movie, usuario, mapItens, mapUsuario, notasTotal);
        this.setVisible(false);
        
        
        panelMaster.setLayout(new BorderLayout());
         panelMaster.add(panel,BorderLayout.NORTH);
         panelMaster.setVisible(true);
        
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        lblRecomendacao = new javax.swing.JLabel();
        imgRecomendacao1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        imgRecomendacao2 = new javax.swing.JLabel();
        imgRecomendacao3 = new javax.swing.JLabel();
        imgRecomendacao4 = new javax.swing.JLabel();
        imgRecomendacao5 = new javax.swing.JLabel();
        imgRecomendacao6 = new javax.swing.JLabel();
        imgRecomendacao7 = new javax.swing.JLabel();
        imgUltimosFilmes1 = new javax.swing.JLabel();
        imgUltimosFilmes2 = new javax.swing.JLabel();
        imgUltimosFilmes3 = new javax.swing.JLabel();
        imgUltimosFilmes4 = new javax.swing.JLabel();
        imgUltimosFilmes5 = new javax.swing.JLabel();
        imgUltimosFilmes6 = new javax.swing.JLabel();
        imgUltimosFilmes7 = new javax.swing.JLabel();
        imgMaisVotados1 = new javax.swing.JLabel();
        imgMaisVotados2 = new javax.swing.JLabel();
        imgMaisVotados3 = new javax.swing.JLabel();
        imgMaisVotados4 = new javax.swing.JLabel();
        imgMaisVotados5 = new javax.swing.JLabel();
        imgMaisVotados6 = new javax.swing.JLabel();
        imgMaisVotados7 = new javax.swing.JLabel();
        lblRecomendacao1 = new javax.swing.JLabel();
        lblRecomendacao2 = new javax.swing.JLabel();
        lblRecomendacao3 = new javax.swing.JLabel();
        lblRecomendacao4 = new javax.swing.JLabel();
        lblRecomendacao5 = new javax.swing.JLabel();
        lblRecomendacao6 = new javax.swing.JLabel();
        lblRecomendacao7 = new javax.swing.JLabel();
        lblAssistidos1 = new javax.swing.JLabel();
        lblAssistidos2 = new javax.swing.JLabel();
        lblAssistidos3 = new javax.swing.JLabel();
        lblAssistidos4 = new javax.swing.JLabel();
        lblAssistidos5 = new javax.swing.JLabel();
        lblAssistidos6 = new javax.swing.JLabel();
        lblAssistidos7 = new javax.swing.JLabel();
        lblVotados7 = new javax.swing.JLabel();
        lblVotados6 = new javax.swing.JLabel();
        lblVotados5 = new javax.swing.JLabel();
        lblVotados4 = new javax.swing.JLabel();
        lblVotados3 = new javax.swing.JLabel();
        lblVotados2 = new javax.swing.JLabel();
        lblVotados1 = new javax.swing.JLabel();

        setEnabled(false);

        lblRecomendacao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblRecomendacao.setText("Filmes Recomendados");

        imgRecomendacao1.setText("1");
        imgRecomendacao1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgRecomendacao1MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Últimos filmes assistidos");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Filmes mais votados");

        btnBuscar.setText("Buscar Filme");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        imgRecomendacao2.setText("2");
        imgRecomendacao2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgRecomendacao2MouseClicked(evt);
            }
        });

        imgRecomendacao3.setText("3");
        imgRecomendacao3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgRecomendacao3MouseClicked(evt);
            }
        });

        imgRecomendacao4.setText("4");
        imgRecomendacao4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgRecomendacao4MouseClicked(evt);
            }
        });

        imgRecomendacao5.setText("5");
        imgRecomendacao5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgRecomendacao5MouseClicked(evt);
            }
        });

        imgRecomendacao6.setText("6");
        imgRecomendacao6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgRecomendacao6MouseClicked(evt);
            }
        });

        imgRecomendacao7.setText("7");
        imgRecomendacao7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgRecomendacao7MouseClicked(evt);
            }
        });

        imgUltimosFilmes1.setText("a");
        imgUltimosFilmes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgUltimosFilmes1MouseClicked(evt);
            }
        });

        imgUltimosFilmes2.setText("b");
        imgUltimosFilmes2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgUltimosFilmes2MouseClicked(evt);
            }
        });

        imgUltimosFilmes3.setText("c");
        imgUltimosFilmes3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgUltimosFilmes3MouseClicked(evt);
            }
        });

        imgUltimosFilmes4.setText("d");
        imgUltimosFilmes4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgUltimosFilmes4MouseClicked(evt);
            }
        });

        imgUltimosFilmes5.setText("e");
        imgUltimosFilmes5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgUltimosFilmes5MouseClicked(evt);
            }
        });

        imgUltimosFilmes6.setText("f");
        imgUltimosFilmes6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgUltimosFilmes6MouseClicked(evt);
            }
        });

        imgUltimosFilmes7.setText("g");
        imgUltimosFilmes7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgUltimosFilmes7MouseClicked(evt);
            }
        });

        imgMaisVotados1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgMaisVotados1MouseClicked(evt);
            }
        });

        imgMaisVotados2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgMaisVotados2MouseClicked(evt);
            }
        });

        imgMaisVotados3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgMaisVotados3MouseClicked(evt);
            }
        });

        imgMaisVotados4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgMaisVotados4MouseClicked(evt);
            }
        });

        imgMaisVotados5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgMaisVotados5MouseClicked(evt);
            }
        });

        imgMaisVotados6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgMaisVotados6MouseClicked(evt);
            }
        });

        imgMaisVotados7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgMaisVotados7MouseClicked(evt);
            }
        });

        lblRecomendacao1.setText("1");

        lblRecomendacao2.setText("2");

        lblRecomendacao3.setText("3");

        lblRecomendacao4.setText("4");

        lblRecomendacao5.setText("5");

        lblRecomendacao6.setText("6");

        lblRecomendacao7.setText("7");

        lblAssistidos1.setText("a");

        lblAssistidos2.setText("b");

        lblAssistidos3.setText("c");

        lblAssistidos4.setText("d");

        lblAssistidos5.setText("e");

        lblAssistidos6.setText("f");

        lblAssistidos7.setText("g");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblRecomendacao)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(imgMaisVotados1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(imgMaisVotados2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(imgMaisVotados3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(imgMaisVotados4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblVotados2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblVotados3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblVotados4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblVotados5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(imgMaisVotados5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblVotados6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(imgMaisVotados6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblVotados7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(imgMaisVotados7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(756, 756, 756)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imgUltimosFilmes1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(imgUltimosFilmes2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(imgUltimosFilmes3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(imgUltimosFilmes4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(imgUltimosFilmes5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(imgUltimosFilmes6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(imgUltimosFilmes7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(imgRecomendacao1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                        .addComponent(lblAssistidos1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblVotados1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblRecomendacao1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(16, 16, 16)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblRecomendacao2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblRecomendacao3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblRecomendacao4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblRecomendacao5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblRecomendacao6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblRecomendacao7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(2, 2, 2)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(lblAssistidos2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblAssistidos3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblAssistidos4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblAssistidos5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblAssistidos6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblAssistidos7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(imgRecomendacao2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(imgRecomendacao3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(imgRecomendacao4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(imgRecomendacao5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(imgRecomendacao6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(imgRecomendacao7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 1062, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRecomendacao)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgRecomendacao1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgRecomendacao2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgRecomendacao3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgRecomendacao4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgRecomendacao5, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgRecomendacao6, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgRecomendacao7, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRecomendacao1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRecomendacao2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRecomendacao3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRecomendacao4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRecomendacao5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRecomendacao6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRecomendacao7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgUltimosFilmes1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgUltimosFilmes2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgUltimosFilmes3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgUltimosFilmes4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgUltimosFilmes5, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgUltimosFilmes6, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgUltimosFilmes7, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAssistidos2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAssistidos3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAssistidos4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAssistidos5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAssistidos6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAssistidos1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAssistidos7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imgMaisVotados7, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imgMaisVotados1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgMaisVotados2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgMaisVotados3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgMaisVotados4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgMaisVotados5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgMaisVotados6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVotados2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVotados3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVotados4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVotados5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVotados6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVotados7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVotados1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void imgRecomendacao2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgRecomendacao2MouseClicked
        try {
            mostrarDetalhes(filmesRecomendados[1]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgRecomendacao2MouseClicked

    private void imgRecomendacao3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgRecomendacao3MouseClicked
         try {
            mostrarDetalhes(filmesRecomendados[2]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgRecomendacao3MouseClicked

    private void imgRecomendacao4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgRecomendacao4MouseClicked
         try {
            mostrarDetalhes(filmesRecomendados[3]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgRecomendacao4MouseClicked

    private void imgRecomendacao5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgRecomendacao5MouseClicked
        try {
            mostrarDetalhes(filmesRecomendados[4]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgRecomendacao5MouseClicked

    private void imgRecomendacao6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgRecomendacao6MouseClicked
        try {
            mostrarDetalhes(filmesRecomendados[5]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgRecomendacao6MouseClicked

    private void imgRecomendacao7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgRecomendacao7MouseClicked
        try {
            mostrarDetalhes(filmesRecomendados[6]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgRecomendacao7MouseClicked

    private void imgUltimosFilmes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgUltimosFilmes1MouseClicked
        try {
            mostrarDetalhes(ultimosFilmes[0]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgUltimosFilmes1MouseClicked

    private void imgUltimosFilmes2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgUltimosFilmes2MouseClicked
        try {
            mostrarDetalhes(ultimosFilmes[1]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgUltimosFilmes2MouseClicked

    private void imgUltimosFilmes3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgUltimosFilmes3MouseClicked
        try {
            mostrarDetalhes(ultimosFilmes[2]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgUltimosFilmes3MouseClicked

    private void imgUltimosFilmes4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgUltimosFilmes4MouseClicked
        try {
            mostrarDetalhes(ultimosFilmes[3]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgUltimosFilmes4MouseClicked

    private void imgUltimosFilmes5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgUltimosFilmes5MouseClicked
        try {
            mostrarDetalhes(ultimosFilmes[4]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgUltimosFilmes5MouseClicked

    private void imgUltimosFilmes6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgUltimosFilmes6MouseClicked
        try {
            mostrarDetalhes(ultimosFilmes[5]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgUltimosFilmes6MouseClicked

    private void imgUltimosFilmes7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgUltimosFilmes7MouseClicked
        try {
            mostrarDetalhes(ultimosFilmes[6]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgUltimosFilmes7MouseClicked

    private void imgMaisVotados1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgMaisVotados1MouseClicked
        try {
            mostrarDetalhes(filmesMaisVotados[5]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgMaisVotados1MouseClicked

    private void imgMaisVotados2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgMaisVotados2MouseClicked
        try {
            mostrarDetalhes(filmesMaisVotados[1]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgMaisVotados2MouseClicked

    private void imgMaisVotados3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgMaisVotados3MouseClicked
        try {
            mostrarDetalhes(filmesMaisVotados[2]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgMaisVotados3MouseClicked

    private void imgMaisVotados4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgMaisVotados4MouseClicked
        try {
            mostrarDetalhes(filmesMaisVotados[3]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgMaisVotados4MouseClicked

    private void imgMaisVotados5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgMaisVotados5MouseClicked
       try {
            mostrarDetalhes(filmesMaisVotados[4]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgMaisVotados5MouseClicked

    private void imgMaisVotados6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgMaisVotados6MouseClicked
        try {
            mostrarDetalhes(filmesMaisVotados[5]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgMaisVotados6MouseClicked

    private void imgMaisVotados7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgMaisVotados7MouseClicked
        try {
            mostrarDetalhes(filmesMaisVotados[6]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgMaisVotados7MouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void imgRecomendacao1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgRecomendacao1MouseClicked
        try {
            mostrarDetalhes(filmesRecomendados[0]); // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imgRecomendacao1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel imgMaisVotados1;
    private javax.swing.JLabel imgMaisVotados2;
    private javax.swing.JLabel imgMaisVotados3;
    private javax.swing.JLabel imgMaisVotados4;
    private javax.swing.JLabel imgMaisVotados5;
    private javax.swing.JLabel imgMaisVotados6;
    private javax.swing.JLabel imgMaisVotados7;
    private javax.swing.JLabel imgRecomendacao1;
    private javax.swing.JLabel imgRecomendacao2;
    private javax.swing.JLabel imgRecomendacao3;
    private javax.swing.JLabel imgRecomendacao4;
    private javax.swing.JLabel imgRecomendacao5;
    private javax.swing.JLabel imgRecomendacao6;
    private javax.swing.JLabel imgRecomendacao7;
    private javax.swing.JLabel imgUltimosFilmes1;
    private javax.swing.JLabel imgUltimosFilmes2;
    private javax.swing.JLabel imgUltimosFilmes3;
    private javax.swing.JLabel imgUltimosFilmes4;
    private javax.swing.JLabel imgUltimosFilmes5;
    private javax.swing.JLabel imgUltimosFilmes6;
    private javax.swing.JLabel imgUltimosFilmes7;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JLabel lblAssistidos1;
    private javax.swing.JLabel lblAssistidos2;
    private javax.swing.JLabel lblAssistidos3;
    private javax.swing.JLabel lblAssistidos4;
    private javax.swing.JLabel lblAssistidos5;
    private javax.swing.JLabel lblAssistidos6;
    private javax.swing.JLabel lblAssistidos7;
    private javax.swing.JLabel lblRecomendacao;
    private javax.swing.JLabel lblRecomendacao1;
    private javax.swing.JLabel lblRecomendacao2;
    private javax.swing.JLabel lblRecomendacao3;
    private javax.swing.JLabel lblRecomendacao4;
    private javax.swing.JLabel lblRecomendacao5;
    private javax.swing.JLabel lblRecomendacao6;
    private javax.swing.JLabel lblRecomendacao7;
    private javax.swing.JLabel lblVotados1;
    private javax.swing.JLabel lblVotados2;
    private javax.swing.JLabel lblVotados3;
    private javax.swing.JLabel lblVotados4;
    private javax.swing.JLabel lblVotados5;
    private javax.swing.JLabel lblVotados6;
    private javax.swing.JLabel lblVotados7;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

    public JLabel getImgRecomendacao1() {
        return imgRecomendacao1;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JLabel getImgMaisVotados1() {
        return imgMaisVotados1;
    }

    public void setImgMaisVotados1(JLabel imgMaisVotados1) {
        this.imgMaisVotados1 = imgMaisVotados1;
    }

    public JLabel getImgMaisVotados2() {
        return imgMaisVotados2;
    }

    public void setImgMaisVotados2(JLabel imgMaisVotados2) {
        this.imgMaisVotados2 = imgMaisVotados2;
    }

    public JLabel getImgMaisVotados3() {
        return imgMaisVotados3;
    }

    public void setImgMaisVotados3(JLabel imgMaisVotados3) {
        this.imgMaisVotados3 = imgMaisVotados3;
    }

    public JLabel getImgMaisVotados4() {
        return imgMaisVotados4;
    }

    public void setImgMaisVotados4(JLabel imgMaisVotados4) {
        this.imgMaisVotados4 = imgMaisVotados4;
    }

    public JLabel getImgMaisVotados5() {
        return imgMaisVotados5;
    }

    public void setImgMaisVotados5(JLabel imgMaisVotados5) {
        this.imgMaisVotados5 = imgMaisVotados5;
    }

    public JLabel getImgMaisVotados6() {
        return imgMaisVotados6;
    }

    public void setImgMaisVotados6(JLabel imgMaisVotados6) {
        this.imgMaisVotados6 = imgMaisVotados6;
    }

    public JLabel getImgMaisVotados7() {
        return imgMaisVotados7;
    }

    public void setImgMaisVotados7(JLabel imgMaisVotados7) {
        this.imgMaisVotados7 = imgMaisVotados7;
    }

    public JLabel getImgUltimosFilmes1() {
        return imgUltimosFilmes1;
    }

    public void setImgUltimosFilmes1(JLabel imgUltimosFilmes1) {
        this.imgUltimosFilmes1 = imgUltimosFilmes1;
    }

    public JLabel getImgUltimosFilmes2() {
        return imgUltimosFilmes2;
    }

    public void setImgUltimosFilmes2(JLabel imgUltimosFilmes2) {
        this.imgUltimosFilmes2 = imgUltimosFilmes2;
    }

    public JLabel getImgUltimosFilmes3() {
        return imgUltimosFilmes3;
    }

    public void setImgUltimosFilmes3(JLabel imgUltimosFilmes3) {
        this.imgUltimosFilmes3 = imgUltimosFilmes3;
    }

    public JLabel getImgUltimosFilmes4() {
        return imgUltimosFilmes4;
    }

    public void setImgUltimosFilmes4(JLabel imgUltimosFilmes4) {
        this.imgUltimosFilmes4 = imgUltimosFilmes4;
    }

    public JLabel getImgUltimosFilmes5() {
        return imgUltimosFilmes5;
    }

    public void setImgUltimosFilmes5(JLabel imgUltimosFilmes5) {
        this.imgUltimosFilmes5 = imgUltimosFilmes5;
    }

    public JLabel getImgUltimosFilmes6() {
        return imgUltimosFilmes6;
    }

    public void setImgUltimosFilmes6(JLabel imgUltimosFilmes6) {
        this.imgUltimosFilmes6 = imgUltimosFilmes6;
    }

    public JLabel getImgUltimosFilmes7() {
        return imgUltimosFilmes7;
    }

    public void setImgUltimosFilmes7(JLabel imgUltimosFilmes7) {
        this.imgUltimosFilmes7 = imgUltimosFilmes7;
    }

    public JLabel getjLabel10() {
        return jLabel10;
    }

    public void setjLabel10(JLabel jLabel10) {
        this.jLabel10 = jLabel10;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public void setImgRecomendacao1(JLabel imgRecomendacao1) {
        this.imgRecomendacao1 = imgRecomendacao1;
    }

    public JLabel getImgRecomendacao2() {
        return imgRecomendacao2;
    }

    public void setImgRecomendacao2(JLabel imgRecomendacao2) {
        this.imgRecomendacao2 = imgRecomendacao2;
    }

    public JLabel getImgRecomendacao3() {
        return imgRecomendacao3;
    }

    public void setImgRecomendacao3(JLabel imgRecomendacao3) {
        this.imgRecomendacao3 = imgRecomendacao3;
    }

    public JLabel getImgRecomendacao4() {
        return imgRecomendacao4;
    }

    public void setImgRecomendacao4(JLabel imgRecomendacao4) {
        this.imgRecomendacao4 = imgRecomendacao4;
    }

    public JLabel getImgRecomendacao5() {
        return imgRecomendacao5;
    }

    public void setImgRecomendacao5(JLabel imgRecomendacao5) {
        this.imgRecomendacao5 = imgRecomendacao5;
    }

    public JLabel getImgRecomendacao6() {
        return imgRecomendacao6;
    }

    public void setImgRecomendacao6(JLabel imgRecomendacao6) {
        this.imgRecomendacao6 = imgRecomendacao6;
    }

    public JLabel getImgRecomendacao7() {
        return imgRecomendacao7;
    }

    public void setImgRecomendacao7(JLabel imgRecomendacao7) {
        this.imgRecomendacao7 = imgRecomendacao7;
    }


    public JLabel getjLabel9() {
        return jLabel9;
    }

    public void setjLabel9(JLabel jLabel9) {
        this.jLabel9 = jLabel9;
    }

    public JLabel getLblRecomendacao() {
        return lblRecomendacao;
    }

    public void setLblRecomendacao(JLabel lblRecomendacao) {
        this.lblRecomendacao = lblRecomendacao;
    }
}
