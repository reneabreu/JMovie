/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import enuns.Genero;
import enuns.Ocupacao;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 604959803144315267L;
    private String id;
    private String nome;
    private int idade;
    private Genero genero;
    private Ocupacao ocupacao;
    private String cep;
    private String senha;
    private ArrayList <Filme>  filmesAssistidos;
    private ArrayList <Nota> notas;
    
    
    public Usuario(String id, String nome, int idade, Genero genero, Ocupacao ocupacao, String cep, String senha){
        notas = new ArrayList <Nota>();
        filmesAssistidos = new ArrayList <Filme>();
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.ocupacao = ocupacao;
        this.cep = cep;
        this.senha = senha;
    }
    
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

    public ArrayList<Filme> getFilmesAssistidos() {
        return filmesAssistidos;
    }

    public void setFilmesAssistidos(Filme filme) {
        filmesAssistidos.add(filme);
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Genero getGenero() {
        return genero;
    }

    public Ocupacao getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(Ocupacao ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public String getId(){
        return id;
    }
    
    public void printInfo(){
        System.out.println("Nome: " + getNome() +
                           ", Idade: " + getIdade() +
                           ", Sexo: " + getGenero().name() +
                           ", Ocupação: " + getOcupacao() +
                           ", CEP: " + getCep() +
                           ", Id: " + getId() +
                           ", Senha: " + getSenha() + 
                           ", Notas dadas: " +getNotas().size() + 
                           ", Filmes assistidos: " + getFilmesAssistidos() + "\n");
    }
       
    static ArrayList<Usuario> listUsuario = new ArrayList<Usuario>();
    private static HashMap<Integer, Usuario> mapUsuario = new HashMap<Integer, Usuario>();
    /* Inicio da função para serializar */
	public static void SerializeUsuario(HashMap<Integer, Usuario> u){													//Função passando parametro do tipo Obj a ser serializado
			
			File yourFile = new File("./src/db/Usuarios.ser");									//Caminho do arquivo para checar se existe
                        
                        mapUsuario = u;

			// Escevendo no arquivo serializado
			String fileName = Serialize.serializeObject(mapUsuario, "Usuarios");					//String fileName(a função serializeObject retorna uma String com o endereço   o)
																								//do arquivo. Serialize.serializeObject(ObjetoASerSerializado,"NomeDoArquivo")  
			mapUsuario = null;
			
			System.out.println("\nDeserialized "+fileName);				
		}
	
	public static void deSerializeUsuario(){
		
		mapUsuario = (HashMap<Integer, Usuario>) Serialize.deSerializeObject("Usuarios");
		System.out.println(mapUsuario.size() + " usuarios serializados");
		for (int i = 0; i < mapUsuario.size(); i++) {
		     System.out.println("Usuario " + (i + 1) + ":\n" + 
		    		 			"Nome: " + mapUsuario.get(i+1).getNome() +
		    		 			", Idade: " + mapUsuario.get(i+1).getIdade() +
		    		 			", Sexo: " + mapUsuario.get(i+1).getGenero()+
		    		 			", Ocupação: " + mapUsuario.get(i+1).getOcupacao()+
		    		 			", CEP: " + mapUsuario.get(i+1).getCep()+
                                                        ", Id: " + mapUsuario.get(i+1).getId()+
		    		 			", Senha: " + mapUsuario.get(i+1).getSenha()+ "\n");
		}
	}
    
}
