package datp.network.remoting;

import datp.logging.*;
import datp.network.NetworkDataPath;
import datp.network.NetworkDataPathAdapter;

import java.net.MalformedURLException;
import java.rmi.*;

public class RemoteDataPathApiAdapter implements NetworkDataPathAdapter {
	
	private String Uri;
	private RemoteDataPathApi DataPath;
	private NetworkDataPathBridge DataBridge;
	
	public RemoteDataPathApiAdapter() {
		
		Uri = "";
		DataPath = null;
	}
	
	private void initialize() {
		
		initialize(Uri);
	}
	
	public void initialize(String uri) {
		
		Uri = uri;
		
		try {
		
			DataPath = (RemoteDataPathApi)Naming.lookup(Uri);
		
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
			
			DataPath = null;
			Logs.message("Remoting -> Data Path", "initialize", Uri, "error");
		}
		
		return;
	}
	
	public NetworkDataPath getDataBridge() {
		
		if (DataBridge == null)
			DataBridge = new NetworkDataPathBridge();
	
		return DataBridge;
	}
		
	public void deinitialize() {
		
	}
	
	public class NetworkDataPathBridge implements NetworkDataPath {
	
		private boolean checkNullDataPath() {
			
			if (DataPath == null) 
				initialize();
					
			return DataPath == null;
		
		}
	
		public void ServerStatus() {
			
			if (checkNullDataPath()) {
				
				Logs.message("Remoting -> Data Path", "Server status", Uri, "warning",  "need initialization");
				return;
			}
			
			try {
				
				DataPath.ServerStatus("");
			
			} catch (RemoteException | NullPointerException e) {
			
				Logs.message("Remoting -> Data Path", "Server status", Uri, "error", e.getMessage());
			}
		}
	}
	
}
