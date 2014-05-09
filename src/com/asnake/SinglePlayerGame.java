package com.asnake;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class SinglePlayerGame extends Activity {
	SampleView tView;
//	Timer caretaker = new Timer();
	private Handler handler;
	Snake snake1 = null;
	int h = 0, w = 0;
	ArrayList<Foundings> foundingsList = new ArrayList<Foundings>();
	int widthPlaces = 20;
	int higthPlaces = 20;
	Rect[][] places = new Rect[widthPlaces][higthPlaces];
	Rect bottemRect = null;
	Rect[] playButtons = null;;
	Rect restartRect = null;
	String[] pButtonString;
	Paint[] p = new Paint[4];
	int rectAreaResize = 0;
	String gOver = "Game Over";
	int timeDrawCounter = 0;
	int speed = 400;
	int highScore = 0;
	int radius = 60;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		setContentView(tView = new SampleView(this));
		
		handler = new Handler();
		handler.postDelayed(runnable, 400);
		
		setupActionBar();
	}
	
	private Runnable runnable = new Runnable() {
		   @Override
		   public void run() {
			  snake1.moveSnake1();
			  tView.invalidate();
			  handler.postDelayed(this, speed);
		   }
		};
		
//	@Override
//	protected void onStart() {
//		super.onStart();
////		caretaker.schedule(action, 500, 2500);
//		h = tView.getHeight();
//		w = tView.getWidth();
//	}
		
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		private void setupActionBar() {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				getActionBar().setDisplayHomeAsUpEnabled(true);
			}
		}	
 
	public class SampleView extends View {
		int x, y;

		public SampleView(Context context) {
			super(context);
			setFocusable(true);

			snake1 = new Snake(widthPlaces, higthPlaces);
			snake1.myColor.setColor(Color.RED);
			
//			TimerTask action = new TimerTask() {
//				public void run() {
//					moveSnake1();
//					invalidate();
//				}
//			};
			
			if(p[0] == null){
				//Buttons ueni
				p[0] = new Paint();
				p[0].setAntiAlias(true);
				p[0].setColor(Color.BLACK);
				p[0].setStyle(Paint.Style.STROKE); 
				p[0].setStrokeWidth(4.5f);
				
				//Foudings
				p[1] = new Paint();
				p[1].setColor(Color.GREEN);
				p[1].setStyle(Paint.Style.FILL);
				p[1].setStrokeWidth(4.5f);
				
				//Text
				p[2] = new Paint();
				p[2].setColor(Color.BLACK);
				p[2].setStyle(Paint.Style.FILL);
				p[2].setStrokeWidth(4f);
				p[2].setTextAlign(Align.CENTER);
				p[2].setTextSize(40f);
				
				//Buttons pressed
				p[3] = new Paint();
				p[3].setAntiAlias(true);
				p[3].setColor(Color.CYAN);
				p[3].setStyle(Paint.Style.FILL); 
				p[3].setStrokeWidth(4.5f);
			}
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent ev){
//		    int action = ev.getAction();
		    x = (int) ev.getX();
		    y = (int) ev.getY();
//		    switch (action) {
//		        case MotionEvent.ACTION_DOWN:
//		            break;
//		        case MotionEvent.ACTION_MOVE:
//		            break;
//		        case MotionEvent.ACTION_UP:
//		            break;
//		        }
		    
			if(playButtons != null && snake1 != null){
				// 0 = down 1 = up 10 = left 11 = right
				if(playButtons[0].contains(x, y) && snake1.direction != 11){
//					snake1.setDirection(10);
					snake1.direction = EnumDirection.LEFT.direct;
				}
				if(playButtons[1].contains(x, y) && snake1.direction != 10){
//					snake1.setDirection(11);
					snake1.direction = EnumDirection.RIGHT.direct;
				}
				if(playButtons[2].contains(x, y) && snake1.direction != 1){
//					snake1.setDirection(0);
					snake1.direction = EnumDirection.DOWN.direct;
				}
				if(playButtons[3].contains(x, y) && snake1.direction != 0){
//					snake1.setDirection(1);
					snake1.direction = EnumDirection.UP.direct;
				}
				if(playButtons[4].contains(x, y)){
					speedUp();
				}
				if(playButtons[5].contains(x, y)){
					speedDown();
				}
			}
			
			if(snake1 != null){
				if(snake1.killedMyself){
					if(restartRect.contains(x, y)){
						handler.postDelayed(runnable, 400);
						if(highScore < snake1.myLength){
							highScore = snake1.myLength;
						}
						snake1 = new Snake(widthPlaces, higthPlaces);
						foundingsList.clear();
						speed = 400;
					}
				}
			}
			
			x = 0;
			y = 0;
			
		    return true;
		       
		}
		@Override
		protected void onDraw(Canvas canvas) {
			if(w == 0 || h == 0){
				h = this.getHeight();
				w = this.getWidth();
			}

			if(playButtons == null ){
				pButtonString = new String[6];
				playButtons = new Rect[6];
				playButtons[0] = new Rect(20,w+((h-w)/4),(w/4),h-20);
				pButtonString[0] = "<--";
				playButtons[1] = new Rect((w/4*3),w+((h-w)/4),(w-20),h-20);
				pButtonString[1] = "-->";
				playButtons[2] = new Rect((w/4*1)+20,(h-((h-w)/2))+((h-w)/8) ,(w/4*3)-20,h-20);
				pButtonString[2] = "Down";
				playButtons[3] = new Rect((w/4*1)+20,w+((h-w)/4),(w/4*3)-20,(w+(h-w)/2)+((h-w)/8)-20);
				pButtonString[3] = "Up";
				playButtons[4] = new Rect((w/8*7),w+20,(w-20),w+((h-w)/5));
				pButtonString[4] = "+";
				playButtons[5] = new Rect((w/8*6),w+20,(w/8*7)-20,w+((h-w)/5));
				pButtonString[5] = "-";
				bottemRect = new Rect(0,w+4,w,h);
				restartRect = new Rect(w/2-100,h/2,w/2+100,h/2+100);
			}

			if(places[0][0] == null){
				rectAreaResize = w/widthPlaces;
				for(int i = 0; i < places.length; i++){
					for(int j = 0; j < places[0].length; j++){
						places[j][i] = new Rect(i*rectAreaResize, j*rectAreaResize, i*rectAreaResize + rectAreaResize , j*rectAreaResize + rectAreaResize);	
					}
				}
			}

			if(foundingsList.size() < snake1.posArray.size()/6){
				foundingsList.add(new Foundings(widthPlaces, higthPlaces));
			}
			
			for(int i = 0; i < foundingsList.size(); i++){
				foundingsList.get(i).reduceCount();
			}
					
			canvas.drawColor(Color.WHITE);
			canvas.drawRect(bottemRect,p[0]);
			
			for(int i = 0; i < playButtons.length; i++){
				canvas.drawRect(playButtons[i], p[0]);
				canvas.drawText(pButtonString[i], playButtons[i].exactCenterX(), playButtons[i].exactCenterY() + 10, p[2]);
			}
			
			
			if(places != null && foundingsList != null && !foundingsList.isEmpty()){
				for(int i = 0; i < foundingsList.size() && foundingsList.get(i) != null; i++){
					canvas.drawRect(places[foundingsList.get(i).myXPos][foundingsList.get(i).myYPos], p[1]);
				}
			}

			if(snake1 != null){
				if(snake1.killedMyself){
					canvas.drawText(gOver, w/2, h/2-40, p[2]);
					canvas.drawRect(restartRect, p[0]);
					canvas.drawText("Restart", w/2, h/2+50, p[2]);
					handler.removeCallbacksAndMessages(null);
				}
				for(int i = 0; i < snake1.posArray.size(); i++){
					if(i == 0){
						snake1.myColor.setStyle(Paint.Style.FILL_AND_STROKE);
						canvas.drawRect(places[snake1.posArray.get(i).x][snake1.posArray.get(i).y], snake1.myColor);
						snake1.myColor.setStyle(Paint.Style.STROKE);
					}else{
						canvas.drawRect(places[snake1.posArray.get(i).x][snake1.posArray.get(i).y], snake1.myColor);
					}
	
					if(!foundingsList.isEmpty()){	
						for(int j = 0; j < foundingsList.size(); j++){
								if(snake1.posArray.get(i).x == foundingsList.get(j).myXPos && snake1.posArray.get(i).y == foundingsList.get(j).myYPos){
									foundingsList.get(j).myCount++;
									if(i == 0 && j == 0){
										foundingsList.add(new Foundings(widthPlaces, higthPlaces));
										speedUp();
									}
									if(i == snake1.myLength - 1){
										snake1.addFouding(foundingsList.get(j).myXPos, foundingsList.get(j).myYPos);
										if(foundingsList.size() >= snake1.posArray.size()/6){
											foundingsList.remove(j);
										}else{
											foundingsList.get(j).replaceMe();
										}
									}		
								}
							
						}
					}
				}
				canvas.drawText("Length: " + Integer.toString(snake1.myLength) + "  Highscore: " + highScore, w/3,w+((h-w)/6), p[2]);
			}
		}
		public void speedUp(){
			if(speed > 120){
				speed -= 8;
			}else if(speed > 100){
				speed -= 2;
			}
		}
		public void speedDown(){
			if(speed < 400){
				speed += 2;
			}
		}
	}
}