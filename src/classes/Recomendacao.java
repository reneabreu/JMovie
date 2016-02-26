/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.util.HashMap;

public class Recomendacao {
    private HashMap<Integer, Usuario> mapUsuario;
    private HashMap<Integer, Item> mapItens;
    private float mRatings [][];
    private int mFreq [][];
    private HashMap<Integer,Float> recomendacoes;
    private final Float recomendados[];
    private Usuario user;
    private final ArrayList<Item> indicados;
    private Item filmesRecomendados;
    
     
    public void reorganizaMelhoresFilmes(int i,Float[] item){
        int cont=6;
        while(cont>i){
            item[cont]=item[cont-1];
            cont--;
        }
    }
    
   public Recomendacao ( HashMap<Integer,Usuario> mUsuario, HashMap<Integer,Item> mItens, Usuario user){
        mapItens = mItens;
        mapUsuario = mUsuario;
        this.user = user;
        recomendacoes = new HashMap<>();
        buildDiffMatrix();
        
        float totalFreq[] =  new float [mFreq.length+1];
        for (int j=1; j <= mFreq.length; j++) {
            recomendacoes.put(j,0.0f);
            totalFreq[j] = 0;
        }
        try{
        for(Nota nota : user.getNotas()){
            
            for(int k=1; k<=mFreq.length; k++){
                if(Integer.parseInt(nota.getFilme().getId()) != k){
                    // Só para itens que não foram vistos
                    int counter = 0;
                    for(Nota note: user.getNotas()){    
                        if(Integer.parseInt(note.getFilme().getId()) == k){
                            counter = 1;
                        }
                    }
                    
                    if(counter == 0){
                        float novoValor = 0;
                        
                        novoValor = mFreq[Integer.parseInt(nota.getFilme().getId())][k] * (mRatings[Integer.parseInt(nota.getFilme().getId())][k] + nota.getNota());
                        
                        
                        totalFreq[k] = totalFreq[k] + mFreq[Integer.parseInt(nota.getFilme().getId())][k];
                        recomendacoes.put(k, recomendacoes.get(k).floatValue() + novoValor);
                        
                    }
                    
                }
            }
        }
        // Faz a média
        for (int j : recomendacoes.keySet()) {
            recomendacoes.put(j, recomendacoes.get(j).floatValue()/(totalFreq[j] ));
        }
        
        //Salva em recomendacoes
         for (Nota nota : user.getNotas()) {
            recomendacoes.put(Integer.parseInt(nota.getFilme().getId()), (float) nota.getNota());
        }
        
       } catch(ArrayIndexOutOfBoundsException e){
                            
       } finally{
            
            recomendados = new Float [recomendacoes.size()+1];
            for(int i : recomendacoes.keySet()){
                recomendados[i-1] = recomendacoes.get(i);
            }
            indicados = new ArrayList<Item>();
            
            
            for(int i = 0;i<recomendados.length-1;i++){ // funçao que ve os filmes mais bem votados
            System.out.println(i +": " + recomendados[i]);
            //filmesMaisVotados[i] = mapItens.get(i+1);
                       
                         //System.out.println(i); 
                    if(recomendados[i]<recomendados[6]){

                    }else if(recomendados[i]<recomendados[5]){
                        recomendados[6] = recomendados[i];
                    }else if(recomendados[i]<recomendados[4]){
                        reorganizaMelhoresFilmes(5,recomendados);
                        recomendados[5] = recomendados[i];
                    }else if(recomendados[i]<recomendados[3]){
                        reorganizaMelhoresFilmes(4,recomendados);
                        recomendados[4] = recomendados[i];
                    }else if(recomendados[i]<recomendados[2]){
                        reorganizaMelhoresFilmes(3,recomendados);
                        recomendados[3] = recomendados[i];
                    }else if(recomendados[i]<recomendados[1]){
                        reorganizaMelhoresFilmes(2,recomendados);
                        recomendados[2] = recomendados[i];
                    }else if(recomendados[i]<recomendados[0]){
                        reorganizaMelhoresFilmes(1,recomendados);
                        recomendados[1] = recomendados[i];
                    }else{
                        reorganizaMelhoresFilmes(0,recomendados);
                        recomendados[0] = recomendados[i];
                    }
                
            
        }
            
           
            for(int i=0; i<8; i++){   
                   for(int j : mapItens.keySet()){
                       if(Integer.parseInt(mapItens.get(j).getId()) == getKeyFromValue(recomendacoes, recomendados[i])){
                           if(indicados.size()<8){
                               indicados.add(mapItens.get(j));
                           }
                       }
                   }
                } 
        }
    }
    
    
    public ArrayList<Item> getRecomendados(){
       return indicados;
    }
    
    public int getKeyFromValue(HashMap<Integer, Float> hm, float value){
        for(int i: hm.keySet()){
            if(value == hm.get(i)){
                return i;
            }
        }
        return 0;
    }
    
    
    public void buildDiffMatrix() {
        mRatings = new float[mapItens.size()+1][mapItens.size()+1]; 
        mFreq = new int[mapItens.size()+1][mapItens.size()+1];
        
        for(int usuario : mapUsuario.keySet()){
            for(Nota nota1: mapUsuario.get(usuario).getNotas()){
                for(Nota nota2: mapUsuario.get(usuario).getNotas()){
                    mRatings[Integer.parseInt(nota1.getFilme().getId())][Integer.parseInt(nota2.getFilme().getId())] = mRatings[Integer.parseInt(nota1.getFilme().getId())][Integer.parseInt(nota2.getFilme().getId())] + (nota1.getNota() - nota2.getNota());
                    mFreq[Integer.parseInt(nota1.getFilme().getId())][ Integer.parseInt(nota2.getFilme().getId())] = mFreq[Integer.parseInt(nota1.getFilme().getId())][Integer.parseInt(nota2.getFilme().getId())] + 1;
                }
            }
        }
        
        for(int i = 1; i<= mapItens.size(); i++){ 
            for(int j = i; j <= mapItens.size(); j++){ 
                 if(mFreq[i][j] > 0){ 
                     mRatings[i][j] = mRatings[i][j] / mFreq[i][j]; 
                 } 
            } 
        }        
  
    } 
}
