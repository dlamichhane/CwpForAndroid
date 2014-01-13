package fi.oulu.tol.cwpforandroid;

public interface CwpListener {
	
	public void onStateUp();
	public void onStateDown();
	public void onFrequencyChange();
	public void onDisconnected();
}
