package fi.oulu.tol.cwpforandroid;

import android.os.Debug;
import android.util.Log;

public class EventLogger {
	private static final String TAG = "CWPLogger";

	public static void logEventStarted(String event, long timeStamp) {
		Log.d(TAG, "Started");
		Log.d(TAG, event + "in :" + timeStamp);
	}

	public static void logEventEnded(String event, long timeStamp) {
		Log.d(TAG, "Ended");
		Log.d(TAG, event + "in :" + timeStamp);
	}
	
	public static void logMemoryUsage(String event){
		int memoryAllocs = Debug.getThreadAllocCount();
		int memoryUsed = Debug.getThreadAllocSize();
		Log.d(TAG, event + "Allocs :" + memoryAllocs + "Memory Used :" + memoryUsed);
	}

}
