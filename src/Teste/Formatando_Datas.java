/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Formatando_Datas {

    
    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        String dt = "12-May-1998";
        Date data2 = sdf.parse(dt);
        
        System.out.println("Data atual sem formatação: "+data2); 

        //Formata a data 
        
        DateFormat formataData = DateFormat.getDateInstance(); 
        System.out.println("Data atual com formatação: "+ formataData.format(data2)); 

        //Formata Hora 
        DateFormat hora = DateFormat.getTimeInstance(); 
        System.out.println("Hora formatada: "+hora.format(data2)); 

        //Formata Data e Hora 
        DateFormat dtHora = DateFormat.getDateTimeInstance(); 
        System.out.println(dtHora.format(data2));


        
    }
    
}
