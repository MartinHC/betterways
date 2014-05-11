package de.betterways;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;

/**
 * Own way for splash screen 5s
 * 
 * @author MartinHC
 * 
 */
public class SleepTask extends AsyncTask<Activity, Void, Integer> {

	protected Integer doInBackground(Activity... params) {
		Looper.prepare();
		if (params.length > 0) {
			Activity main = params[0];
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Intent myIntent = new Intent(main.getApplicationContext(),
					ControlActivity.class);
			main.startActivityForResult(myIntent, 0);

			return 0;
		}

		return -1;
	}

}
