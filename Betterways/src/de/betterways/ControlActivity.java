package de.betterways;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Main Class to control the app.
 */
public class ControlActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_layout);
		TextView notfinished = (TextView) findViewById(R.id.textView1);
		Typeface font = Typeface.createFromAsset(getAssets(),
				"HaventSleptInTwoDaysShadow.ttf");
		notfinished.setTypeface(font);
	}
}
