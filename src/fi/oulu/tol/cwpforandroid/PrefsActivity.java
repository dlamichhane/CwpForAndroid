package fi.oulu.tol.cwpforandroid;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class PrefsActivity extends PreferenceActivity {

	static final String TAG = "Preference Activity";
	private EditTextPreference hostAddressEditPref;
	private static EditTextPreference portNumberEditPref;
	private static CheckBoxPreference beepCheckBox;
	Resources resources;
	boolean status = false;
	private String currentHostAddress;
	private NotificationManager notificationManager;
	SharedPreferences prefs;

	private CountDownTimer time = new CountDownTimer(30000, 1000) {

	     public void onTick(long millisUntilFinished) {
	     }

	     public void onFinish() {
	    	 
	    	 int icon = R.drawable.notification_icon;        				// icon from resources
	    	 CharSequence tickerText = "Connection Established";            // ticker-text
	    	 long when = 1000;         										// notification time
	    	 
	    	 Context context = getApplicationContext();      				// application Context
	    	 
	    	 CharSequence contentTitle = "Server Connection";  				// message title
	    	 CharSequence contentText = "Send the signal";      			// message text

	    	 Intent notificationIntent = new Intent(context, PrefsActivity.class);
	    	 PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

	    	 // the next two lines initialize the Notification, using the configurations above
	    	 Notification notification = new Notification(icon, tickerText, when);
	    	 notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
	    	 notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
	    	 notificationManager.notify(1, notification);
	     }
	  };


	// @SuppressWarnings("null")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "On Created");
		super.onCreate(savedInstanceState);
		initListener();
	}

	public void onStart(){
		super.onStart();
		time.cancel();
	}
	
	public void onStop(){
		super.onStop();
		time.start();

	}
	
	private void initListener() {
		addPreferencesFromResource(R.xml.prefs);

		prefs = PreferenceManager.getDefaultSharedPreferences(this);

		final String host_address = prefs.getString("host_address", "");
		final String port_number = prefs.getString("port_number", "");

		Log.d(TAG, "Host address : " + host_address);
		Log.d(TAG, "Port number : " + port_number);

		resources = this.getResources();

		hostAddressEditPref = (EditTextPreference) findPreference(resources
				.getString(R.string.host_address_key));
		portNumberEditPref = (EditTextPreference) findPreference(resources
				.getString(R.string.port_number_key));
		beepCheckBox = (CheckBoxPreference) findPreference(resources
				.getString(R.string.beep_key));

		Log.d(TAG, resources.getString(R.string.host_summary));
		try {
			hostAddressEditPref.setSummary(R.string.host_summary);
			portNumberEditPref.setSummary(R.string.port_summary);
		} catch (Exception e) {
			e.printStackTrace();
		}

		hostAddressEditPref
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						String currentHostAddress = (String) newValue;

						if (!currentHostAddress.equals(host_address)
								&& !System.getProperty("os.name").contains(
										"Windows")) {
							if (isHostReachable(currentHostAddress)) {
								preference.setSummary(currentHostAddress);
								Toast.makeText(getApplicationContext(),
										"Host is reachable", Toast.LENGTH_SHORT)
										.show();
							} else {
								Toast.makeText(
										getApplicationContext(),
										"Host is unreachable, put the correct host address",
										Toast.LENGTH_SHORT).show();
							}
						}

						return true;
					}

					private boolean isHostReachable(String currentHostAddress) {

						new Thread(new Runnable() {
							public void run() {
								try {
									Log.d(TAG,
											"Before sending the ping request");
									status = InetAddress.getByName(
											"cwp.opimobi.com")
											.isReachable(3000);
									Log.d(TAG, "Reachable, ping successful" + status);
								} catch (UnknownHostException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}).start();

						return status;
					}

				});

		portNumberEditPref
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						String currentPortNumber = (String) newValue;
						Integer portNumber = Integer
								.parseInt(currentPortNumber);
						if (portNumber < 65536) {
							preference.setSummary(currentPortNumber);
							return true;
						}
						return false;
					}

				});

		beepCheckBox
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

					public boolean onPreferenceChange(Preference preference,
							Object newValue) {

						return false;
					}

				});

	}
}
