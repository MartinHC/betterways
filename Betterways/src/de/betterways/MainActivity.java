package de.betterways;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Startscreen App
 * 
 * @author MartinHC
 * 
 */
public class MainActivity extends ActionBarActivity {

	private Context contextForDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contextForDialog = this;

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
			/**
			 * Change textView StartText to specified fontStyle.
			 */
			// TextView startText = (TextView)
			// findViewById(R.id.customStartText);
			// TextView appName = (TextView) findViewById(R.id.appName);
			// Typeface font = Typeface.createFromAsset(getAssets(),
			// "HaventSleptInTwoDaysShadow.ttf");
			// startText.setTypeface(font);
			// appName.setTypeface(font);
		}
	}

	/**
	 * Decision() if no GPS enabled
	 */
	private void decision() {

		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		final boolean gpsEnabled = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);

		if (!gpsEnabled) {
			buildAlertDialogNoGPS();
		} else {
			new SleepTask().execute(this);

		}
	}

	/**
	 * buildAlertDialogNoGPS() UI to make decision
	 */
	public void buildAlertDialogNoGPS() {

		new AlertDialog.Builder(contextForDialog)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setMessage("Set your Gps 'on' to continue")
				.setTitle("Chose to continue")
				.setPositiveButton("GPS Settings",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int id) {
								startActivity(new Intent(
										Settings.ACTION_LOCATION_SOURCE_SETTINGS));
								dialog.cancel();
							}
						})

				.setNegativeButton("Close",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int id) {
								Intent startHome = new Intent(
										Intent.ACTION_MAIN);
								startHome.addCategory(Intent.CATEGORY_HOME);
								startHome
										.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								MainActivity.this.startActivity(startHome);
							}
						}).create().show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		decision();

	}

}
