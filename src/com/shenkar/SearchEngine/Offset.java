package com.shenkar.SearchEngine;

public class Offset {
	
	public int postid;
	public int hit_number;
	
	public Offset(int _id, int _hit){
		
		postid=_id;
		hit_number = _hit;
		
	}
	public String toString(){
		return postid+" , "+ hit_number;
	}

}
