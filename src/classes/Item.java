/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import enuns.Categoria;
import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public abstract class Item implements Serializable,Comparable<Item>{
    
    private static final long serialVersionUID = -5612758586741923248L;
    
    
    private String nome;
    private String id;
    private int ano;
    private Date data;
    private String url;
    private String sinopse;
    private String poster;
    private float media;
    private ArrayList <Categoria> genero = new ArrayList <Categoria>();
    private ArrayList <Nota> notas = new ArrayList <Nota>();
    
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
    
    private static HashMap<Integer, Item> mapItens = new HashMap<Integer, Item>();
    
    Item(String nome, String data, String id) throws ParseException{
        
        this.id = id;
        this.nome = nome;
        this.data = sdf.parse(data);
        
        String urlAux, nomeAux;        //fazer a url customizada do filme a partir do nome e ano
        urlAux = "http://www.omdbapi.com/?t=filme&plot=full&r=json";        
	nomeAux= nome.replace(" ", "+");
	urlAux = urlAux.replace("filme", nomeAux);   
        this.url=urlAux;
        
        
        //web();
    }
    
    public ArrayList<Categoria> getGenero() {
        return genero;
    }
    
     public void setGenero(Categoria generoFilme) {
         genero.add(generoFilme);
     }
     
     /*public ArrayList<Integer> getNotas() {
         
         ArrayList<Integer> notasInt = new ArrayList();
        for( int i = 0; i < notas.size(); i++){
            notasInt.add(notas.get(i).getNota());
        }
        return notasInt;
    }*/
     public ArrayList<Nota> getNotas() {
         
        return notas;
    }

    public void setNotas(Nota nota) { 
        notas.add(nota);
    }
    
    public void modNotas(Nota nota) {
        Nota novaNota = nota;
        for(int j = 0; j<notas.size(); j++){
            if(notas.get(j).getFilme().getId().equals(novaNota.getFilme().getId()))
                notas.set(j, novaNota);
                
        }
    }

    public String getNome() {
        return nome;
    }

    public int getAno() {
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        //ano = String.valueOf(cal.get(Calendar.YEAR));
        ano = cal.get(Calendar.YEAR);
        return ano;
    }

    public String getData() {
        DateFormat formataData = DateFormat.getDateInstance();
        return formataData.format(data);
    }
    
    public void setMedia(float media){
        this.media=media;
    }
    
    public float getMedia(){
        return media;
    }
       
    public String getId(){
        return id;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
    
    public String getPoster(int tamanho) {
        String constante = "SX300";
	String sizedPoster;
        sizedPoster = poster.replace(constante, "SX" + String.valueOf(tamanho));
        return sizedPoster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    
    
    
	public static void SerializeItem(HashMap<Integer, Item> i){													//Função passando parametro do tipo Obj a ser serializado
			
			File yourFile = new File("./src/db/Itens.ser");									//Caminho do arquivo para checar se existe
                        
                        mapItens = i;
                        
			// Escevendo no arquivo serializado
			String fileName = Serialize.serializeObject(mapItens, "MapItens");					//String fileName(a função serializeObject retorna uma String com o endereço   o)
																								//do arquivo. Serialize.serializeObject(ObjetoASerSerializado,"NomeDoArquivo")  
			mapItens = null;
			
			System.out.println("\nDeserialized "+fileName);
			
		}
	
	public static void deSerializeItem(){
		
		mapItens = (HashMap<Integer, Item>) Serialize.deSerializeObject("mapItens");
		System.out.println(mapItens.size() + " filmes serializados");
                
		for (int i = 0; i < mapItens.size(); i++) {
		     System.out.print("Filme " + (i + 1) + ":\n" + 
                                      "Nome: " + mapItens.get(i+1).getNome() +
                                      ", Lançamento: " + mapItens.get(i+1).getData() +
                                      ", Ano: " + mapItens.get(i+1).getAno());
                    System.out.print(", Gênero:");
                    for (int j = 0; j < mapItens.get(i+1).getGenero().size(); j++) {
                        if( j == mapItens.get(i+1).getGenero().size() - 1)
                            System.out.print(" " + mapItens.get(i+1).getGenero().get(j).getNome() + ". \n\n");
                        else
                            System.out.print(" " + mapItens.get(i+1).getGenero().get(j).getNome() + ",");
                    }
		}
	}
        
        /*@Override  
        public int compareTo(Item o) {  
            // TODO Auto-generated method stub  
            return this.media.compareTo(o.media);  
        }*/ 
}
