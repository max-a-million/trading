package algo;

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
	
	public void initialize() {
		
		try {
		
			CommandPath = (RemoteCommandPathApi)Naming.lookup(Uri);
		
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
		
			Logs.message("RemoteCommandAdapter", "initialize", Uri, "error");
		}
		
		return;
	}

	@Override
	public void GetHistory() {
		
		try {
		
			CommandPath.GetHistory();
		
		} catch (RemoteException e) {
		
			Logs.message("RemoteCommandAdapter", "Subscribe", Uri, "error");
		}
	}
	
}
