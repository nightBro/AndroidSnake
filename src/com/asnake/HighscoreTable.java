package com.asnake;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HighscoreTable extends Activity {
	ArrayList<HighScoreElement> scoreTable = null;
	
	LinearLayout sclViewShowResults;
	
	ArrayList<TextView> arraytView;
	
	ArrayList<HighScoreElement> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_highscore_table);
		// Show the Up button in the action bar.
		setupActionBar();
		
		sclViewShowResults = (LinearLayout) findViewById(R.id.sclViewShowResults);
		arraytView = new ArrayList<TextView>();
		
		if(scoreTable == null){
//			Intent b = getIntent();
//			Bundle b = this.getIntent().getExtras();
//			scoreTable = (ArrayList<HighScoreElement>) b.get("HighScoreTable");
			
//	        Intent intTest = getIntent();
//	        list = (ArrayList<HighScoreElement>) intTest.getSerializableExtra("HighScoreTable");
//			DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra("data");
//			ArrayList<HighScoreElement> list = dw.getParliaments();
			
			
			
			for(int i = 0; i < list.size(); i++){
				TextView ttextView = new TextView(this);
				ttextView.setText(list.get(i).name);
				ttextView.setTextSize(20);
				sclViewShowResults.addView(ttextView);
				arraytView.add(ttextView);
			}
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.highscore_table, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
