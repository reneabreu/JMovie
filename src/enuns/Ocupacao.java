/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enuns;

public enum Ocupacao {
    ADMINISTRADOR("Administrador"),
    ARTISTA("Artista"),
    MEDICO("Médico"),
    EDUCADOR("Educador"),
    ENGENHEIRO("Engenheiro"),
    ENTRETENIMENTO("Entretenimento"),
    EXECUTIVO("Executivo"),
    ASSISTENCIAMEDICA("Assistência Médica"),
    DONADECASA("Dona de Casa"),
    ADVOGADO("Advogado"),
    BIBLIOTECARIO("Bibliotecário"),
    MARKETING("Marketing"),
    PROGRAMADOR("Programador"),
    APOSENTADO("Aposentado"),
    VENDEDOR("Vendedor"),
    CIENTISTA("Cientista"),
    ESTUDANTE("Estudante"),
    TECNICO("Técnico"),
    ESCRITOR("Escritor"),
    NENHUM("Nenhum"),
    OUTRO("Outro");
    
    private String nome;
    
    Ocupacao(String nome){
        this.nome=nome;
    }

    public String getNome() {
        return nome;
    }
    
    @Override
    public String toString(){
        return this.nome;
    }
    
    
    
}
