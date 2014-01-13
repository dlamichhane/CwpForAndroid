package fi.oulu.tol.cwpforandroid;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Debug;
import android.os.StrictMode;
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
	private ToneGenerator toneGenerator = new ToneGenerator(
			AudioManager.STREAM_MUSIC, 50);


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		Debug.startMethodTracing();
		Debug.startMethodTracing("CWPClient");
		Debug.startAllocCounting();

		image = (ImageView) findViewById(R.id.imageView1);

		image.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
		
				switch (event.getActionMasked()) {

				case MotionEvent.ACTION_DOWN: {
					image.setImageResource(R.drawable.icon2);
					toneGenerator.startTone(ToneGenerator.TONE_DTMF_4);
					toneGenerator.stopTone();
					EventLogger.logEventStarted("Message arrived", System.currentTimeMillis());
					EventLogger.logMemoryUsage("Memory");

				}
					;
					break;

				case MotionEvent.ACTION_UP: {
					image.setImageResource(R.drawable.icon1);
					EventLogger.logEventEnded("Signal playing now", System.currentTimeMillis());

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

	protected void onDestroy(){
		Debug.stopMethodTracing();
		Debug.stopAllocCounting();
		
	}
}