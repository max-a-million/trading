package primus.network.v.remoting.client;

import java.rmi.*;

import primus.logging.*;
import primus.network.NetworkCommandPath;
import primus.network.client.NetworkCommandClientPathAdapter;
import primus.network.v.remoting.RemoteCommandPathApi;

public class RemoteClientCommandPathApiAdapter extends RemoteClient<RemoteCommandPathApi> implements NetworkCommandClientPathAdapter {
	
	private RemoteCommandPathApi CommandPath;
	private NetworkCommandPathBridge CommandBridge;
	
	public RemoteClientCommandPathApiAdapter() {
	}
	
	public void initialize(String uri) {
		
		CommandPath = connect(uri, "");
	}
	
	public NetworkCommandPath getCommandBridge() {
		
		if (CommandBridge == null)
			CommandBridge = new NetworkCommandPathBridge();
	
		return CommandBridge;
	}
		
	public void deinitialize() {
		
	}
	
	public class NetworkCommandPathBridge implements NetworkCommandPath {
	
		private boolean checkNullDataPath() {
			
			if (CommandPath == null) 
				CommandPath = reconnect();
					
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
