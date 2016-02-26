/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import classes.Filme;
import classes.Filme;
import classes.Filme;
import classes.Item;
import classes.Item;
import classes.Item;
import classes.Nota;
import classes.Serialize;
import classes.Serialize;
import classes.Serialize;
import classes.Usuario;
import classes.WebService;
import enuns.Categoria;
import java.io.File;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class teste1 implements Serializable{
 
    /**
     * @param args the command line arguments
     */
    static ArrayList <Categoria> gen = new ArrayList<Categoria>();
    
    public static void main(String[] args) throws ParseException, Exception {
        
        HashMap<Integer, Item> mapFilme = (HashMap<Integer, Item>) Serialize.deSerializeObject("MapItens");
        
        //ArrayList<Nota> notasToy = (ArrayList<Nota>) Serialize.deSerializeObject("Notas");
        
        HashMap<Integer, Usuario> mapUsuario = (HashMap<Integer, Usuario>) Serialize.deSerializeObject("Usuarios");
        
        /*for(int j = 0; j < listUsuario.size(); j++){
            for(int i = 0; i < notasToy.size(); i++){
                if(notasToy.get(i).getUsuario().getId().equals(listUsuario.get(j).getId())){
                    listUsuario.get(j).setNotas(notasToy.get(i));
                    System.out.println(/*"Filme " + (j + 1 ) + *"Nota: " + (i + 1));
                }
            }
            
            System.out.println("Usuario " + (j + 1 ) + ":\n");
        }*/
        
        //Usuario.SerializeUsuario(listUsuario);
        //Filme.SerializeFilme(listFilme);
        
        /*for(int j = 0; j < listUsuario.size(); j++){
            System.out.println("Usuario " + (j + 1) + ":\n"); 
            listUsuario.get(j).printInfo();
        }*/
        
        //int media = movie.getMedia();
        
        /*for(int i = 0; i < nT.size(); i++){
        
            media += nT.get(i).getNota();
        }
        
        media = (media / nT.size());*/
        
       /* System.out.println(nT.size() + " médias somadas\n" + 
                           "A avaliação desse filme é: " + media);
        
        /*
        Filme movie = new Filme("Toy Story", "1-oct-1997", "123");
        movie.setGenero(GeneroFilme.ANIMACAO);
        movie.setGenero(GeneroFilme.COMEDIA);
        
        System.out.println("o nome do filme é " + movie.getNome() + 
                           ", e o filme foi lançado em " + movie.getData());
        System.out.println("o filme foi lançado no ano de " + movie.getAno());
        
        gen = movie.getGenero();
        System.out.print("o filme está classificado como:");
        for (int i = 0; i < gen.size(); i++) {
                    if( i == gen.size() - 1)
                        System.out.print(gen.get(i).getNome() + ". \n");
                    else
                        System.out.print(" " + gen.get(i).getNome() + ",");
		}
    
    
        */
       
       /* System.out.println(listFilme.get(0).getSinopse());
        System.out.println(listFilme.get(0).getPoster(300));
        System.out.println(listFilme.get(0).getPoster(800));
        System.out.println(listFilme.get(0).getPoster(1200));
        System.out.println(listFilme.get(0).getPoster(300));
        System.out.println(listFilme.get(0).getPoster(1200));
        System.out.println(listFilme.get(0).getPoster(800));
        
        System.out.println(listFilme.get(1).getPoster(300));
        System.out.println(listFilme.get(1).getPoster(800));
        System.out.println(listFilme.get(1).getPoster(1200));
        System.out.println(listFilme.get(1).getPoster(300));
        System.out.println(listFilme.get(1).getPoster(1200));
        System.out.println(listFilme.get(1).getPoster(800));*/
        
        
        //ArrayList<Nota> listNotas = (ArrayList<Nota>) Serialize.deSerializeObject("Notas");
        
        
        //System.out.println(listNotas.get(0).getFilme().getNome());
        //mapFilme.get(1).setMedia();
        //mapFilme.get(1).printInfo();
        System.out.println(mapUsuario.size());
        //Item.SerializeItem(mapFilme);
        
        ArrayList<Nota> notasDadas = new ArrayList();
        ArrayList<Nota> notasRecebidas = new ArrayList();
        notasDadas = mapUsuario.get(1).getNotas();
        notasRecebidas = mapFilme.get(1).getNotas();
        
        System.out.println("Verificando se o usuario avaliou esse filme...");
        for(int i = 0; i < notasDadas.size(); i++){
            if(notasDadas.get(i).getFilme().getId().equals(mapFilme.get(1).getId())){
                System.out.println("O usuário avaliou esse filme! Nota: " + 
                                    notasDadas.get(i).getNota());
                break;
            } else if(i == (notasDadas.size() - 1) )
                System.out.println("O Usuario não avaliou esse filme!");
                
        }
        for(int i = 0; i < notasRecebidas.size(); i++){
            if(notasRecebidas.get(i).getUsuario().getId().equals(mapUsuario.get(1).getId())){
                System.out.println("O usuário avaliou esse filme! Nota: " + 
                                    notasRecebidas.get(i).getNota());
                break;
            } else if(i == (notasRecebidas.size() - 1) )
                System.out.println("O Usuario não avaliou esse filme!");
                
        }
    }
    
    /*private void mostrarNota(){
        
        ArrayList<Nota> notasDadas = new ArrayList();
        
        notasDadas = filme.get(1).getNotas();
        
        System.out.println("Verificando se o usuario avaliou esse filme...");
        for(int i = 0; i < notasDadas.size(); i++){
            if(notasDadas.get(i).getFilme().getId().equals(filme.getId())){
                System.out.println("O usuário avaliou esse filme! Nota: " + 
                                    notasDadas.get(i).getNota());
                break;
            } else if(i == (notasDadas.size() - 1) )
                System.out.println("O Usuario não avaliou esse filme!");
                
        }
        
        
    }*/
}
