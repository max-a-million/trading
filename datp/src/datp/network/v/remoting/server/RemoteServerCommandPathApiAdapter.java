package datp.network.v.remoting.server;

import datp.logging.*;
import datp.network.NetworkCommandPath;
import datp.network.server.NetworkCommandServerPathAdapter;
import datp.network.v.remoting.RemoteCommandPathApi;

import java.rmi.*;
import java.rmi.server.*;

public class RemoteServerCommandPathApiAdapter extends RemoteServer<RemoteServerCommandPathApiAdapter.CommandConsumer> implements NetworkCommandServerPathAdapter {
	
	private CommandConsumer CommandPath;
	public NetworkCommandPath CommandBridge;
	
	public RemoteServerCommandPathApiAdapter() {
		
		try {
		
			CommandPath = new CommandConsumer();
		
		} catch (RemoteException e) {
			
		}
	}
	
	public void initialize(NetworkCommandPath bridge, String uri, String port) {
		
		super.initialize(uri, port, CommandPath);
		CommandBridge = bridge;
	}
	
	public class CommandConsumer extends UnicastRemoteObject implements RemoteCommandPathApi {
		
		protected CommandConsumer() throws RemoteException {
		}

		public void GetHistory() throws RemoteException {
			CommandBridge.GetHistory();
			Logs.message("Remoting -> Command API", "GetHistory", "ok");
		}
	}
	
}
