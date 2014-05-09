package com.asnake;

public class SnakePos {
	int x = 0;
	int y = 0;
	SnakePos frontSnakePos = null;
	
	public SnakePos(int x, int y, SnakePos frontSnakePos){
		this.x = x;
		this.y = y;
		this.frontSnakePos = frontSnakePos;
	}
	
	public boolean equals(SnakePos tsPos){
		return this.x == tsPos.x && this.y == tsPos.y;
	}
}
