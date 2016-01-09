package com.shenkar.SearchEngine;


/*
 * this is the summary for every result we get.
 */

public class Summary {

	public int 	  id;
	public String author;
	public String date;
	public String summary;
	
	Summary(){}
	public String toString(){
		
		return "<HTML>Id: "+id+"<BR>Author: "+author+"<BR>Date: "+date+"<BR>"+summary+"<BR>";
	}
	
	
}
