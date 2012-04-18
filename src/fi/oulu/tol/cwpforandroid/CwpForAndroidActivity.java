package fi.oulu.tol.cwpforandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class CwpForAndroidActivity extends Activity {
	/** Called when the activity is first created. */
	private ImageView image;
	private EditText editText;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		image = (ImageView) findViewById(R.id.imageView1);

		image.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				ToneGenerator toneGenerator = new ToneGenerator(
						AudioManager.STREAM_MUSIC, 50);
				switch (event.getActionMasked()) {

				case MotionEvent.ACTION_DOWN: {
					image.setImageResource(R.drawable.icon2);
					toneGenerator.startTone(ToneGenerator.TONE_DTMF_6);
					Toast toast = Toast.makeText(getApplicationContext(),
							"I am pressed", Toast.LENGTH_SHORT);
					toast.show();
					toneGenerator.stopTone();

				}
					;
					break;

				case MotionEvent.ACTION_UP: {
					image.setImageResource(R.drawable.icon1);
					Toast toast = Toast.makeText(getApplicationContext(),
							"I am released", Toast.LENGTH_SHORT);
					toast.show();

				}
					;
					break;

				case MotionEvent.ACTION_CANCEL: {
					image.setImageResource(R.drawable.icon1);
					toneGenerator.stopTone();
				}
					;
					break;
				default:
					break;
				}

				return true;
			}
		});

		editText = (EditText) findViewById(R.id.editText1);
		editText.setOnEditorActionListener(new OnEditorActionListener() {

			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				Integer frequency = Integer.parseInt(editText.getText().toString());
				if (frequency == Math.round(frequency)){
					Toast.makeText(getApplicationContext(),
							"New Frequency " + editText.getText().toString() + " Mhz", Toast.LENGTH_SHORT).show();
					return true;
				}else{
					Toast.makeText(getApplicationContext(),
							"Put numeric value to set frequency", Toast.LENGTH_SHORT).show();
					return false;	
				}
				
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.cwp_menu, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Intent intent = new Intent(this, PrefsActivity.class);
		startActivity(intent);
		return true;
	}

}