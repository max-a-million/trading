package primus.network.v.remoting.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import primus.logging.Logs;

public class RemoteServer<T extends Remote> {
	
	private T Path;
	private String Uri;
	private int Port;
	
	public RemoteServer() {
	}
	
	public void connect(String uri, String port, T path) {
		
		try {
		
			Uri = uri;
			Port = Integer.parseInt(port);
			Path = path;
			LocateRegistry.createRegistry(Port);
			Naming.rebind(Uri, Path);
			
			Logs.message("Remote Server", "initialize", "ok");
		
		} catch (RemoteException | MalformedURLException e) {
			
			Logs.message("Remote Server", "initialize", "error");
		}

	}
	
	public void disconnect() {
		
		try {
		
			Naming.unbind(Uri);
			UnicastRemoteObject.unexportObject(Path, true);
			
			Logs.message("Remote Server", "deinitialize", "ok");
			
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			
			Logs.message("Remote Server", "deinitialize", "error");
		}
	}
}
