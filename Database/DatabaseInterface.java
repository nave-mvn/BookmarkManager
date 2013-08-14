package Database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class DatabaseInterface {

	static ArrayList<Bookmark> bklist;
	static Bookmark newBookmark;
	
	//this method serializes a bookmark and adds it to the database
	public static void serialize(String des, String url, String date){
		
		newBookmark = new Bookmark(des,url,date);
		
		try
		  {			
		  try
		  	{	
			
			  FileInputStream fileIn = new FileInputStream("bookmark.ser");
			  ObjectInputStream in = new ObjectInputStream(fileIn);
			  bklist = (ArrayList<Bookmark>)in.readObject();
			  in.close();
			  fileIn.close();
			  bklist.add(newBookmark);
		  	
		  	}
		  catch (FileNotFoundException e){
			  
			  System.out.print("sads");
			  bklist = new ArrayList();
			  bklist.add(newBookmark);
		  	
		  }
		
		FileOutputStream fileOut = new FileOutputStream("bookmark.ser",false);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(bklist);
		out.close();
		fileOut.close();
	      
		  }
		
		catch (IOException i){
			i.printStackTrace();
			return;
		} 
		
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public static ArrayList<Bookmark> deserialize(){
		
		Bookmark e;
		ArrayList<Bookmark> bklist2 = null;
		
		try
        	{
			try
				{
				FileInputStream fileIn = new FileInputStream("bookmark.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				bklist2 = (ArrayList<Bookmark>)in.readObject();
				in.close();
				fileIn.close();
				
				}
			catch(FileNotFoundException y)
				{return bklist2; }
		
        }
		
		catch(IOException i){
           i.printStackTrace();
           
       }
		
		catch(ClassNotFoundException c){
           c.printStackTrace();  
       }
		
		return bklist2;
		

	}
	
	public static void delete (String u){

		ArrayList<Bookmark> bklist2 = null;
		ArrayList<Bookmark> toBeWritten = new ArrayList();
		
		try
        	{
			try
				{
				FileInputStream fileIn = new FileInputStream("bookmark.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				bklist2 = (ArrayList<Bookmark>)in.readObject();
				in.close();
				fileIn.close();}
		catch(FileNotFoundException y){ System.out.println("ty"); }
		
       }catch(IOException i)
       {
           i.printStackTrace();
           
       }catch(ClassNotFoundException c)
       {
           System.out.println(" class not found.");
           c.printStackTrace();
           
       }
		
	
		
		for(int r =0; r<bklist2.size();r++){
			if ((bklist2.get(r).getUrl()).equals(u) == false ){
				
				Bookmark b = bklist2.get(r);
				toBeWritten.add(b);
				System.out.println(b.getUrl());
				System.out.println(u);
			}
		}
		
		
		try{
			
		FileOutputStream fileOut = new FileOutputStream("bookmark.ser",false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(toBeWritten);
        out.close();
        fileOut.close();
		
		}
		catch (IOException i){
		
		i.printStackTrace();
		return;
		
		} 
		
	}
	
	public static void main(String [] args){

	}
	
}
