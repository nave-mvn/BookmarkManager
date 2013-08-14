package GUI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ListModel;

import Database.Bookmark;
import Database.DatabaseInterface;

//methods associated with the main window are not static--as these usually need to have a popup window--
//--code written and for that you have to instatntiate Actions with the mainwindow frame

//methods associated with the popup windows are static

public class Actions {
	
	static MainWindow mainWindow;
	
	public Actions(MainWindow mainWindow) {
	        this.mainWindow = mainWindow;
	    }
	
	public void newBookmark(){
		//create instance of newbookmark dialog;
		NewBookmarkDialog openBkDialog = new NewBookmarkDialog(mainWindow,"New Bookmark");
        openBkDialog.pack();
        openBkDialog.setLocationRelativeTo(mainWindow);
        openBkDialog.show();
	
	}
	
	public static void createBookmark(String n, String u){

		Calendar day =  Calendar.getInstance();
		int d = day.get(Calendar.DAY_OF_MONTH);
		int m = day.get(Calendar.MONTH) +1;
		int y = day.get(Calendar.YEAR);
		String date = (m + "-" + d + "-"+ y );;
        
		//pass to databaceinterface;
        DatabaseInterface.serialize(n, u, date);
   
        }

	//this method returns a vector<bookmark> of the bookmarks currently in the database
	public static Vector<Bookmark> getList(String string) {
	
		// TODO Auto-generated method stub
		Vector<Bookmark> Bkarr = new Vector<Bookmark>();
		ArrayList<Bookmark> bklist2 = DatabaseInterface.deserialize();

		for (int i =0; i<bklist2.size();i++){
			
			String n = bklist2.get(i).getName();
			Pattern p = Pattern.compile(string + "\\w*");
			Matcher m = p.matcher(n);
			if(m.find()){
				Bkarr.add(bklist2.get(i));
			}
		}

		return Bkarr;
	
	}	
	
	public void editBookmark(String name, String url , String date){
		EditDialog editBkDialog = new EditDialog(mainWindow,"Boomark",name, url ,date);
        editBkDialog.pack();
        editBkDialog.setLocationRelativeTo(mainWindow);
        editBkDialog.setVisible(true);	
		
	}
	
	public static void delBookmark(String u){
		
		DatabaseInterface.delete(u);
		
	}
	
	public void getAboutDialog(){
		
		AboutDialog aboutBkDialog = new AboutDialog(mainWindow);
        aboutBkDialog.pack();
        aboutBkDialog.setLocationRelativeTo(mainWindow);    
        aboutBkDialog.setVisible(true);	
        
	}
	
	public void exit(){
		
		System.exit(0);
	
	} 
	
	public static void errorDialog(){
		
		ErrorDialog errorBkDialog = new ErrorDialog(mainWindow);
        errorBkDialog.pack();
        errorBkDialog.setLocationRelativeTo(mainWindow);
        errorBkDialog.setVisible(true);	
		
	}

	
	public static void main(String args[]){

		
	}
		
}

	
