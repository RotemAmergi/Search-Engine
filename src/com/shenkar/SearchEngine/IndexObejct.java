package com.shenkar.SearchEngine;

import java.util.ArrayList;



public class IndexObejct {

	public	String 		   		name ;
	public	int 		   		hits;
	public  int 				id;
	public  int 				post_hits ;
	public  ArrayList<Offset>   offsetArray ;


		IndexObejct(){hits=0; offsetArray = new ArrayList<Offset>(); post_hits=1;id=0;}

 
		public String toString(){
			return "word: "+name+" hits: "+hits+" id: "+id +"\n";
		}
		public int get_post_hits(){
			return post_hits;
		}
}
