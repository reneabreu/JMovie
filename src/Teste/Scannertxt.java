/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import classes.Filme;
import classes.Item;
import classes.Nota;
import classes.Serialize;
import classes.Usuario;
import classes.WebService;
import enuns.Genero;
import enuns.Categoria;
import enuns.Ocupacao;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Scannertxt {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.text.ParseException
     */
    public static void main (String[] args) throws FileNotFoundException, ParseException, Exception{
        
        //scanUser();
        //Usuario.deSerializeUsuario();
        //scanMovie();
        //Item.deSerializeItem();
        //scanNotas();
        //Nota.deSerializeNota();
        addNotas();
    }
    
    public static void scanNotas() throws FileNotFoundException, ParseException{
    
        Scanner scanner = new Scanner(new FileReader("./src/files/u.data"))
                       .useDelimiter("\\s|\\n");
    
        
        HashMap<Integer, Usuario> mapUsuario = (HashMap<Integer, Usuario>) Serialize.deSerializeObject("Usuarios");
        HashMap<Integer, Filme> mapFilme = (HashMap<Integer, Filme>) Serialize.deSerializeObject("MapItens");
        ArrayList<Nota> listNotas = new ArrayList<Nota>();
        int i , j, k;
        /* user id | item id | rating | timestamp.*/
        k = 0;
        while (scanner.hasNext()) {
          
          String userId = scanner.next();
          String itemId = scanner.next();
          String rating = scanner.next();
          String timestamp = scanner.next();
          
          System.out.println("Nota: " + rating + ", Filme:" + mapFilme.get(Integer.parseInt(itemId)).getNome() + ", Usuario:" + mapUsuario.get(Integer.parseInt(userId)).getNome());
          
          Nota nota = new Nota(mapUsuario.get(Integer.parseInt(userId)), mapFilme.get(Integer.parseInt(itemId)), Integer.parseInt(rating));
          //System.out.println("Nota: " + nota.getNota() + ", Filme:" + nota.getFilme().getNome() + ", Usuario:" + nota.getUsuario().getNome());
          listNotas.add(nota);
          System.out.println( listNotas.size() + " Objetos Nota Criados");
        }
        Nota.SerializeNota(listNotas);
    }
    
    public static void scanUser() throws FileNotFoundException, ParseException{
    
        Scanner scanner = new Scanner(new FileReader("./src/files/u.user"))
                       .useDelimiter("\\||\\n");
        
        HashMap<Integer, Usuario> mapUsuario = new HashMap<Integer, Usuario>();
    
        /* user id | age | gender | occupation | zip code*/
    
        while (scanner.hasNext()) {
          
          String nome = null;
          String id = scanner.next();
          String idade = scanner.next();
          String sexo = scanner.next();
          Genero genero = null;
              switch (sexo) {
                case "M":
                    nome = "João";
                    genero = Genero.MASCULINO;
                    break;
                case "F":
                    nome = "Maria";
                    genero = Genero.FEMININO;
                    break;
            }
          String ocupacao = scanner.next();
          Ocupacao ocup = null;
            switch (ocupacao){
                case "administrator":
                    ocup = Ocupacao.ADMINISTRADOR;
                    break;
                case "artist":
                    ocup = Ocupacao.ARTISTA;
                    break;
                case "doctor":
                    ocup = Ocupacao.MEDICO;
                    break;
                case "educator":
                    ocup = Ocupacao.EDUCADOR;
                    break;
                case "engineer":
                    ocup = Ocupacao.ENGENHEIRO;
                    break;
                case "entertainment":
                    ocup = Ocupacao.ENTRETENIMENTO;
                    break;
                case "executive":
                    ocup = Ocupacao.EXECUTIVO;
                    break;
                case "healthcare":
                    ocup = Ocupacao.ASSISTENCIAMEDICA;
                    break;
                case "homemaker":
                    ocup = Ocupacao.DONADECASA;
                    break;
                case "lawyer":
                    ocup = Ocupacao.ADVOGADO;
                    break;
                case "librarian":
                    ocup = Ocupacao.BIBLIOTECARIO;
                    break;
                case "marketing":
                    ocup = Ocupacao.MARKETING;
                    break;
                case "none":
                    ocup = Ocupacao.NENHUM;
                    break;
                case "other":
                    ocup = Ocupacao.OUTRO;
                    break;
                case "programmer":
                    ocup = Ocupacao.PROGRAMADOR;
                    break;
                case "retired":
                    ocup = Ocupacao.APOSENTADO;
                    break;
                case "salesman":
                    ocup = Ocupacao.VENDEDOR;
                    break;
                case "scientist":
                    ocup = Ocupacao.CIENTISTA;
                    break;
                case "student":
                    ocup = Ocupacao.ESTUDANTE;
                    break;
                case "technician":
                    ocup = Ocupacao.TECNICO;
                    break;
                case "writer":
                    ocup = Ocupacao.ESCRITOR;
                    break;
            }
          String cep = scanner.next();
          
          //System.out.println(nome);
          Usuario user = new Usuario(id, nome, Integer.parseInt(idade), genero, ocup, cep, "12345");
          user.printInfo();
          mapUsuario.put(Integer.parseInt(id), user);
          //Usuario.SerializeUsuario(user, -1);
          //Usuario.deSerializeUsuario();
        }
        
        Usuario.SerializeUsuario(mapUsuario);
    }
    
    public static void scanMovie() throws FileNotFoundException, ParseException, Exception{
        int y;
    Scanner scanner = new Scanner(new FileReader("./src/files/u.item"))
                       .useDelimiter("\\||\\n");
    
    /* movie id | movie title | release date | video release date |
              IMDb URL | unknown | Action | Adventure | Animation |
              Children's | Comedy | Crime | Documentary | Drama | Fantasy |
              Film-Noir | Horror | Musical | Mystery | Romance | Sci-Fi |
              Thriller | War | Western |*/
    HashMap<Integer, Item> mapItens = new HashMap<Integer, Item>();
    
     WebService webservice = new WebService();
    
        while (scanner.hasNext()) {

          String id = scanner.next();
          String nome = scanner.next();
          String lancamento = scanner.next();
          String lancamentoVideo = scanner.next();
          String url = scanner.next();
          
          nome = nome.substring(0, nome.length()-7);
          
          if(nome.contains(", The")){
              nome = nome.substring(0, nome.length()-5);
              nome = "The " + nome;
          } else if (nome.contains(", A")){
              nome = nome.substring(0, nome.length()-3);
              nome = "A " + nome;
          }  
         
          Filme movie = new Filme(nome, lancamento, id);
          
          /* Generos */
          String desconhecido = scanner.next();
          y = Integer.parseInt(desconhecido);
          if(y == 1)
              movie.setGenero(Categoria.DESCONHECIDO);
          
          String acao = scanner.next();
          y = Integer.parseInt(acao);
          if(y == 1)
              movie.setGenero(Categoria.ACAO);
          
          String aventura = scanner.next();
          y = Integer.parseInt(aventura);
          if(y == 1)
              movie.setGenero(Categoria.AVENTURA);
          
          String animacao = scanner.next();
          y = Integer.parseInt(animacao);
          if(y == 1)
              movie.setGenero(Categoria.ANIMACAO);
          
          String infantil = scanner.next();
          y = Integer.parseInt(infantil);
          if(y == 1)
              movie.setGenero(Categoria.INFANTIL);
          
          String comedia = scanner.next();
          y = Integer.parseInt(comedia);
          if(y == 1)
              movie.setGenero(Categoria.COMEDIA);
          
          String crime = scanner.next();
          y = Integer.parseInt(crime);
          if(y == 1)
              movie.setGenero(Categoria.CRIME);
          
          String documentario = scanner.next();
          y = Integer.parseInt(documentario);
          if(y == 1)
              movie.setGenero(Categoria.DOCUMENTARIO);
          
          String drama = scanner.next();
          y = Integer.parseInt(drama);
          if(y == 1)
              movie.setGenero(Categoria.DRAMA);
          
          String fantasia = scanner.next();
          y = Integer.parseInt(fantasia);
          if(y == 1)
              movie.setGenero(Categoria.FANTASIA);
          
          String filmNoir = scanner.next();
          y = Integer.parseInt(filmNoir);
          if(y == 1)
              movie.setGenero(Categoria.FILMNOIR);
          
          String terror = scanner.next();
          y = Integer.parseInt(terror);
          if(y == 1)
              movie.setGenero(Categoria.HORROR);
          
          String musical = scanner.next();
          y = Integer.parseInt(musical);
          if(y == 1)
              movie.setGenero(Categoria.MUSICAL);
          
          String misterio = scanner.next();
          y = Integer.parseInt(misterio);
          if(y == 1)
              movie.setGenero(Categoria.MISTERIO);
          
          String romance = scanner.next();
          y = Integer.parseInt(romance);
          if(y == 1)
              movie.setGenero(Categoria.ROMANCE);
          
          String sciFi = scanner.next();
          y = Integer.parseInt(sciFi);
          if(y == 1)
              movie.setGenero(Categoria.SCIFI);
          
          String suspense = scanner.next();
          y = Integer.parseInt(suspense);
          if(y == 1)
              movie.setGenero(Categoria.SUSPENSE);
          
          String guerra = scanner.next();
          y = Integer.parseInt(guerra);
          if(y == 1)
              movie.setGenero(Categoria.GUERRA);
          
          String western = scanner.next();
          y = Integer.parseInt(western);
          if(y == 1)
              movie.setGenero(Categoria.WESTERN);
          
          
          try {
            webservice.sendGet(movie);
          } catch (NullPointerException e) {
              System.out.println((mapItens.size() + 1)+" - " + movie.getNome());       
            //System.out.println("erro: " + e);
          }
          mapItens.put(Integer.parseInt(id), movie);
          //webservice.sendGet(movie);
          System.out.println(mapItens.size() +" - " + movie.getNome());
          
          //Filme.SerializeFilme(movie);
          //Filme.deSerializeFilme();
          //movie.printInfo();

        }
        
        /*for (int i = 0; i < listFilme.size(); i++) {
            Filme.SerializeFilme(listFilme.get(i));
        }*/
        Item.SerializeItem(mapItens);
        //System.out.println(listFilme.size());

    }

    public static void addNotas(){
        
        HashMap<Integer, Usuario> mapUsuario = (HashMap<Integer, Usuario>) Serialize.deSerializeObject("Usuarios");
        //HashMap <Integer, Item> mapItens = (HashMap<Integer, Item>) Serialize.deSerializeObject("mapItens");
        ArrayList<Nota> listNotas = (ArrayList<Nota>) Serialize.deSerializeObject("Notas");
        
            for (int i = 0; i< listNotas.size(); i++){
                System.out.println("Adicionando nota " + i);
                //mapItens.get(Integer.parseInt(listNotas.get(i).getFilme().getId())).setNotas(listNotas.get(i));
                //mapUsuario.get(Integer.parseInt(listNotas.get(i).getUsuario().getId())).setNotas(listNotas.get(i));
                mapUsuario.get(Integer.parseInt(listNotas.get(i).getUsuario().getId())).setFilmesAssistidos(listNotas.get(i).getFilme());
            }
            
            Usuario.SerializeUsuario(mapUsuario);
            //Item.SerializeItem(mapItens);
    }
}