/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.HashMap;

public class SlopeOne {
    private HashMap<Integer, Usuario> mapUsuario;
    private HashMap<Integer, Item> mapItens;
    private float mRatings [][];
    private int mFreq [][];
    
    SlopeOne( HashMap<Integer, Usuario> mUsuario, HashMap<Integer, Item> mItens ){
        mapItens = mItens;
        mapUsuario = mUsuario;
    }
    
    public void buildDiffMatrix() {
        mRatings = new float[mapItens.size()+1][mapItens.size()+1]; 
        mFreq = new int[mapItens.size()+1][mapItens.size()+1];
        
        for(int usuario : mapUsuario.keySet()){
            for(Nota nota1: mapUsuario.get(usuario).getNotas()){
                for(Nota nota2: mapUsuario.get(usuario).getNotas()){
                    mRatings[Integer.parseInt(nota1.getFilme().getId())][Integer.parseInt(nota2.getFilme().getId())] += nota1.getNota() - nota2.getNota();
                    mFreq[Integer.parseInt(nota1.getFilme().getId())][ Integer.parseInt(nota2.getFilme().getId())] = mFreq[Integer.parseInt(nota1.getFilme().getId())][ Integer.parseInt(nota2.getFilme().getId())] + 1;
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
