package com.asnake;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btnScoreList;
	Button btnStartGame;
//	View someView;
//	View root;
	ArrayList<HighScoreElement> scoreTable = new ArrayList<HighScoreElement>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getWindow().getDecorView().setBackgroundColor(Color.BLACK);	
		
		btnScoreList = (Button) findViewById(R.id.btnScoreList);
		btnStartGame = (Button) findViewById(R.id.bttnStartGame);
		
		scoreTable = new ArrayList<HighScoreElement>();
		
		  // Now get a handle to any View contained 
		  // within the main layout you are using
//		someView = findViewById(R.layout.activity_main);
//		if(someView != null){
//		  
//
//		  // Find the root view
//		  root = someView.getRootView();
//
//		  // Set the color
//		  root.setBackgroundColor(Color.BLACK);
//		  root.invalidate();
//		 }
		
		if(scoreTable != null){
			scoreTable.add(new HighScoreElement("Test", 20));
		}
		
		btnScoreList.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
                Intent myIntent = new Intent(getBaseContext(), HighscoreTable.class);
//                myIntent.putExtra("HighScoreTable", new DataWrapper(scoreTable));
//              startActivityForResult(myIntent, 0);
				startActivity(myIntent);
			}
		});
		
		btnStartGame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
                Intent myIntent = new Intent(arg0.getContext(), SinglePlayerGame.class);
				startActivity(myIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
