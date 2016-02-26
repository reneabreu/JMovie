/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serialize {
	
	/**
	 * just to exemplify (unique fileName foreach object, but fileName must change between executions) 
	 * @param toSerialize - Object to serialize
	 * @return
	 */
	public static String createSerializationFileName(Serializable toSerialize){
		String fileName = toSerialize+".ser";
		return fileName;
	}
	
	/**
	 * will write objectToSerialize content on fileName file! 
	 * @param objectToSerialize
	 * @return fileName
	 */
	static String serializeObject(Serializable objectToSerialize, String archivesName) {
		try
		{
			// You should create an unique file name for the same content on different executions! This method don't do...
			String fileName = "./src/db/"+createSerializationFileName(archivesName);
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(objectToSerialize);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in "+fileName);

			return fileName;
		}catch(IOException i)
		{
			//i.printStackTrace();
			return null;
		}
	}

	/**
	 * will read (and return) the object from fileName file 
	 * @param fileName
	 * @return
	 */
	public static Serializable deSerializeObject(String fileName){
            
            System.out.println("Deserializing " + fileName + "...");
		Serializable deserializedObject = null;
		try
		{
			 
			FileInputStream fileIn = new FileInputStream("./src/db/" + fileName + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			deserializedObject = (Serializable)in.readObject();
			in.close();
			fileIn.close();
                        System.out.println("Deserialization Complete");
			return deserializedObject;
		}catch(IOException i)
		{
			System.out.println("ERRO");
			i.printStackTrace();
		}catch(ClassNotFoundException c)
		{
			//c.printStackTrace();
		}
             System.out.println("Deserialization Complete");
		return deserializedObject;
	}
}
