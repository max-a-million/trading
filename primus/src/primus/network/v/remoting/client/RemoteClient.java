package primus.network.v.remoting.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import primus.logging.Logs;

public class RemoteClient<T extends Remote> {
	
	private T Path;
	private String Uri;
	private String Port;
	
	public T connect(String uri, String port) {
		
		Uri = uri;
		Port = port;
		
		try {
		
			Path = (T)Naming.lookup(Uri);
		
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
			
			Path = null;
			Logs.message("Remoting -> Data Path", "initialize", Uri, "error");
		}
		
		return Path;
	}
	
	public T reconnect() {
		
		return connect(Uri, Port);
	}
	
	public void disconnect() {
		
	}
}
