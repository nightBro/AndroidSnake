package com.asnake;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Paint;

public class Snake {
	int myLength = 6;
	int direction = 0;
	int lastDirection = 0;
	int widthPlaces = 0;
	int higthPlaces = 0;
	boolean killedMyself = false;
	boolean fundingAdded = false;
	Paint myColor = new Paint();
	EnumDirection directionEnum;
	
	// 0 = down 1 = up 10 = left 11 = right
	ArrayList<SnakePos> posArray = new ArrayList<SnakePos>();

	public Snake(int widthPlaces, int higthPlaces) {
		this.widthPlaces = widthPlaces;
		this.higthPlaces = higthPlaces;
		setStartPosition();
	}
	
	public void moveSnake1() {
	if (posArray != null) {
		for(int i = 3; i < posArray.size() && !fundingAdded; i++){
			if(posArray.get(0).equals(posArray.get(i))){
				killedMyself = true;
			}
		}
		
		fundingAdded = false;
		// move down & up
		for (int i = posArray.size() - 1; i >= 0; i--) {
			if (i > 0) {
				SnakePos tSnakePosM1 = posArray.get(i - 1);
				SnakePos tSnakePos = posArray.get(i);
				tSnakePos.x = tSnakePosM1.x;
				tSnakePos.y = tSnakePosM1.y;
				posArray.set(i, tSnakePos);
			} else {
				SnakePos tSnakePos = posArray.get(i);
				if (lastDirection != direction) {
					// 0 = down 1 = up 10 = left 11 = right
					if (lastDirection == 0){
						if(direction == 10) {
							tSnakePos.y -= 1;
						}else{
							tSnakePos.y += 1;
						}
					}
					if (lastDirection == 1) {
						if (direction == 10) {
							tSnakePos.y -= 1;
						}else{
							tSnakePos.y += 1;
						}
					}
					if (lastDirection == 10) {
						if (direction == 0) {
							tSnakePos.x += 1;
						}else{
							tSnakePos.x -= 1;
						}
					}
					if (lastDirection == 11) {
						if (direction == 0) {
							tSnakePos.x += 1;
						}else{
							tSnakePos.x -= 1;
						}
					}
				} else {
					if(direction == 0){
						tSnakePos.x += 1;
					}
					if(direction == 1){
						tSnakePos.x -= 1;
					}
					if(direction == 10){
						tSnakePos.y -= 1;
					}
					if(direction == 11){
						tSnakePos.y += 1;
					}
				}
				if(tSnakePos.x >= widthPlaces ){
					tSnakePos.x = 0;
				}
				if(tSnakePos.x < 0 ){
					tSnakePos.x = widthPlaces - 1;
				}
				if(tSnakePos.y >= higthPlaces){
					tSnakePos.y = 0;
				}
				if(tSnakePos.y < 0){
					tSnakePos.y = higthPlaces - 1;
				}
				posArray.set(i, tSnakePos);
			}
		}
		lastDirection = direction;
	}
}

	public void setStartPosition() {
		for (int i = 0; i < myLength; i++) {
			if (i == 0) {
				posArray.add(new SnakePos(5, i+5, null));
			} else {
				posArray.add(new SnakePos(5, i+5, posArray.get(i-1)));
			}
		}
		myColor.setAntiAlias(true);
		myColor.setColor(Color.RED);
		myColor.setStyle(Paint.Style.STROKE); 
		myColor.setStrokeWidth(4.5f);
	}
	
	public void addFouding(int x, int y){
		SnakePos addPos = new SnakePos(x, y, posArray.get(myLength - 1));
		posArray.add(addPos);
		myLength++;
		fundingAdded = true;
	}

//	public void setDirection(int direct) {
//		// 0 = down 1 = up 2 = left 3 = right
//		this.direction = direct;
//	}
}



