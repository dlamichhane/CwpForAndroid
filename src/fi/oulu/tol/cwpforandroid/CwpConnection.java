package fi.oulu.tol.cwpforandroid;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CwpConnection {
	SharedPreferences prefs;
	private String host_address;
	private Integer port_number;
	
	public CwpConnection(Context context) {
		prefs =  PreferenceManager.getDefaultSharedPreferences(context);
		this.host_address = prefs.getString("host_address", "");
		String tmp_port_number = prefs.getString("port_number", "");
		this.port_number = Integer.parseInt(tmp_port_number);
		
		Runnable runnable = new Runnable() {
			public void run() {
				
			}
				
		};
	}
	
	public void CwpSocketConnection() {
		InetAddress hostAddress;
		try {
			hostAddress = InetAddress.getByName("cwp.opimobi.com");
			Socket sckt = new Socket(hostAddress, 20000);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
