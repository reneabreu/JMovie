/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Nota implements Serializable{
    
    private static final long serialVersionUID = -8047245743534883672L;
    
    private Usuario usuario;
    private Filme filme;
    private int nota;
    
    public Nota(Usuario user, Filme movie, int rating){
        
        this.nota = rating;
        this.filme = movie;
        this.usuario = user;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Filme getFilme() {
        return filme;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
    static ArrayList<Nota> listNotas = new ArrayList<Nota>();
	
    /* Inicio da função para serializar */
	public static void SerializeNota(ArrayList <Nota> n){													//Função passando parametro do tipo Obj a ser serializado
			
			File yourFile = new File("./src/db/Notas.ser");									//Caminho do arquivo para checar se existe
			/*if(yourFile.exists()) {																//O arquivo existe?
				deSerializeNota();																//desSerializar
			}
                        
                        if(edit == -1)
                            listNotas.add(n);
                        else{
                            edit -= 1;
                            listNotas.set(edit,n);
                        }*/
                        
                        listNotas = n;
                        
			// Escevendo no arquivo serializado
			String fileName = Serialize.serializeObject(listNotas, "Notas");					//String fileName(a função serializeObject retorna uma String com o endereço   o)
																								//do arquivo. Serialize.serializeObject(ObjetoASerSerializado,"NomeDoArquivo")  
			listNotas = null;
			
			System.out.println("\nDeserialized "+fileName);										
					
		}
	
	public static void deSerializeNota(){
		
		listNotas = (ArrayList<Nota>) Serialize.deSerializeObject("Notas");
		System.out.println(listNotas.size() + " notas serializados");
		for (int i = 0; i < listNotas.size(); i++) {
		     System.out.println("Nota " + ( i + 1 ) + ":\n" +
                                        "Nota: " + listNotas.get(i).getNota() + 
                                        ", Filme: " + listNotas.get(i).getFilme().getNome() +
                                        ", ID Filme: " + listNotas.get(i).getFilme().getId() +
                                        ", Usuario: " + listNotas.get(i).getUsuario().getNome() +
                                        ", Id Usuario: " + listNotas.get(i).getUsuario().getId());
		}
        }
    
}
