/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Filme extends Item implements Serializable{
    
    private static final long serialVersionUID = -6400365281008969992L;
    private final String USER_AGENT = "Mozilla/5.0";
    
    private static ArrayList<Filme> listFilme = new ArrayList<Filme>();
    
    
    
    public Filme(String nome, String data, String id) throws ParseException, Exception{
        
        super(nome,data,id);    
          
    }
    
    public void printInfo(){
        System.out.println("O nome do filme é " + getNome() + 
                           ", e foi lançado em " + getData() + 
                           ".\nNo ano " + getAno() + 
                           ". Teve " + getNotas().size() + " avaliações." + 
                           "Sua média é de: " + getMedia());
        
        System.out.print("o filme está classificado como:");
        for (int i = 0; i < getGenero().size(); i++) {
                    if( i == getGenero().size() - 1)
                        System.out.print(" " + getGenero().get(i).getNome() + ". \n");
                    else
                        System.out.print(" " + getGenero().get(i).getNome() + ",");
		}
        System.out.print("\n");
    }
        
        public void web() throws Exception{
            
            URL obj = new URL(this.getUrl());
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 
			// optional default is GET
			con.setRequestMethod("GET");
	 
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
	 
			BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			String resposta = response.toString();
			JSONObject json = (JSONObject)new JSONParser().parse(resposta);
					//print result

                        this.setSinopse(json.get("Plot").toString());
                        this.setPoster(json.get("Poster").toString());
                        
        }

    @Override
    public int compareTo(Item o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}