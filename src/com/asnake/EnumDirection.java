package com.asnake;

public enum EnumDirection {
	LEFT(10), 
	RIGHT(11), 
	UP(1), 
	DOWN(0);
	
	public int direct;
	private EnumDirection(int direct){
	this.direct = direct; 
	}
}
