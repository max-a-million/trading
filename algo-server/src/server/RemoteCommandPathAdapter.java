package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import datp.logging.Logs;
import datp.network.NetworkCommandPath;
import datp.network.remoting.RemoteCommandPathApi;

public class RemoteCommandPathAdapter implements NetworkCommandPath {
	
	private String Uri;
	private RemoteCommandPathApi CommandPath;
	
	public RemoteCommandPathAdapter(String uri) {
		Uri = uri;
		CommandPath = null;
	}
	
	public void initialize() throws RemoteException {
		try {
			CommandPath = (RemoteCommandPathApi)Naming.lookup(Uri);
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
			Logs.message("Remoting -> Data Path", "initialize", Uri, "error");
		}
		return;
	}

	@Override
	public void Subscribe() {
		try {
			CommandPath.CreateDataPath("");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
