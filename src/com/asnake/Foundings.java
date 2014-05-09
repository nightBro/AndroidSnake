package com.asnake;

import java.util.Random;

public class Foundings {
	int myCount = 0;
	int myXPos = 0;
	int myYPos = 0;
	Random rand = new Random();
	private int widthPlaces ;
	private int higthPlaces;
	
	public Foundings(int widthPlaces, int higthPlaces) {
		this.widthPlaces = widthPlaces;
		this.higthPlaces = higthPlaces;
		replaceMe();
		myCount = 30;
	}
	
	public void replaceMe(){
		myXPos = rand.nextInt(widthPlaces);
		myYPos = rand.nextInt(higthPlaces);
		myCount = 30;
	}
	
	public void reduceCount(){
		if(myCount == 0){
			replaceMe();
		}else{
			myCount--;
		}
	}
}
