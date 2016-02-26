/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enuns;

public enum Categoria {
ACAO("Ação",0),
AVENTURA("Aventura",1),
ANIMACAO("Animação",2),
INFANTIL("Infantil",3),
COMEDIA("Comédia",4),
CRIME("Crime",5),
DOCUMENTARIO("Documentário",6),
DRAMA("Drama",7),
FANTASIA("Fantasia",8),
FILMNOIR("Noir",9),
HORROR("Horror",10),
MUSICAL("Musical",11),
MISTERIO("Mistério",12),
ROMANCE("Romance",13),
SCIFI("Ficção Científica",14),
SUSPENSE("Suspense",15),
GUERRA("Guerra",16),
WESTERN("Faroeste",17),
DESCONHECIDO("Desconhecido",18);

private String nome;
private int codigo;

Categoria(String nome, int codigo){
    this.nome = nome;
    this.codigo = codigo;
}
 
public String getNome(){
     return this.nome;
}

@Override
public String toString(){
    return this.nome;
}



}
