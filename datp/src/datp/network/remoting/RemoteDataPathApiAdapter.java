package datp.network.remoting;

import datp.logging.*;
import datp.network.NetworkDataPath;

import java.net.MalformedURLException;
import java.rmi.*;

public class RemoteDataPathApiAdapter implements NetworkDataPath {
	
	private String Uri;
	private RemoteDataProducer DataPath;
	
	public RemoteDataPathApiAdapter() throws RemoteException {
	}
	
	public void initialize(String uri) throws RemoteException {
		Uri = uri;
		try {
			DataPath = (RemoteDataProducer)Naming.lookup(Uri);
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
			Logs.message("Remoting -> Data Path", "initialize", Uri, "error");
		}
		return;
	}
	
	public void ServerStatus() {
		try {
			DataPath.ServerStatus("");
		} catch (RemoteException | NullPointerException e) {
			Logs.message("Remoting -> Data Path", "Server status", Uri, "error", e.getMessage());
		}
	}
	
	public class RemoteDataProducer implements RemoteDataPathApi {
	
		public void ServerStatus(String path) throws RemoteException {
			Logs.message("Remote CM", "deinitialize", "ok");
		}
	}
}
