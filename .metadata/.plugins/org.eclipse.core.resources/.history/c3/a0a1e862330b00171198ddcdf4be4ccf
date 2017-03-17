package datp.network.v.remoting.client;

import datp.logging.*;
import datp.network.NetworkCommandPath;
import datp.network.client.NetworkCommandClientPathAdapter;
import datp.network.v.remoting.RemoteCommandPathApi;

import java.rmi.*;

public class RemoteClientCommandPathApiAdapter extends RemoteClient<RemoteCommandPathApi> implements NetworkCommandClientPathAdapter {
	
	private RemoteCommandPathApi CommandPath;
	private NetworkCommandPathBridge CommandBridge;
	
	public RemoteClientCommandPathApiAdapter() {
	}
	
	public void initialize(String uri) {
		
		CommandPath = super.initialize(uri, "");
	}
	
	public NetworkCommandPath getDataBridge() {
		
		if (CommandBridge == null)
			CommandBridge = new NetworkCommandPathBridge();
	
		return CommandBridge;
	}
		
	public void deinitialize() {
		
	}
	
	public class NetworkCommandPathBridge implements NetworkCommandPath {
	
		private boolean checkNullDataPath() {
			
			if (CommandPath == null) 
				CommandPath = reinitialize();
					
			return CommandPath == null;
		
		}
	
		
		public void GetHistory() {
			
			if (checkNullDataPath()) {
				
				Logs.message("Remoting -> Data Path", "Server status", "warning",  "need initialization");
				return;
			}
			
			try {
				
				CommandPath.GetHistory();
			
			} catch (RemoteException | NullPointerException e) {
			
				Logs.message("Remoting -> Data Path", "Server status", "error", e.getMessage());
			}	
		}
	}
	
}
