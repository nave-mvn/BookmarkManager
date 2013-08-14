package Database;

import java.io.Serializable;

public class Bookmark implements Serializable{
	
    protected String name;
    protected String url;
    protected String date;

    public Bookmark (String n,String u,String d){
    	name = n;
    	url = u;
    	date = d;
    }

    public String toString(){
    	return name;
    }
    public String getName(){
    	return name;
    }
    
    public String getUrl(){
    	return url;
    }
    
    public String getDate(){
    	return date;
    }
}
