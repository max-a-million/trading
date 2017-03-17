package datp.network.v.remoting.client;

import datp.logging.*;
import datp.network.NetworkDataPath;
import datp.network.client.NetworkDataClientPathAdapter;
import datp.network.v.remoting.RemoteDataPathApi;

import java.rmi.*;

public class RemoteClientDataPathApiAdapter extends RemoteClient<RemoteDataPathApi> implements NetworkDataClientPathAdapter {
	
	private RemoteDataPathApi DataPath;
	private NetworkDataPathBridge DataBridge;
	
	public RemoteClientDataPathApiAdapter() {
	}
	
	public void initialize(String uri) {
		
		DataPath = connect(uri, "");
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
				DataPath = reconnect();
					
			return DataPath == null;
		
		}
	
		public void ServerStatus() {
			
			if (checkNullDataPath()) {
				
				Logs.message("Remoting -> Data Path", "Server status", "warning",  "need initialization");
				return;
			}
			
			try {
				
				DataPath.ServerStatus("");
			
			} catch (RemoteException | NullPointerException e) {
			
				Logs.message("Remoting -> Data Path", "Server status", "error", e.getMessage());
			}
		}
	}
	
}
