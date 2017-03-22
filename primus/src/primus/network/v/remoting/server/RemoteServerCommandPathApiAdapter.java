package primus.network.v.remoting.server;

import java.rmi.*;
import java.rmi.server.*;

import primus.logging.*;
import primus.network.NetworkCommandPath;
import primus.network.server.NetworkCommandServerPathAdapter;
import primus.network.v.remoting.RemoteCommandPathApi;

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
		
		connect(uri, port, CommandPath);
		CommandBridge = bridge;
	}
	
	public void deinitialize() {
		
		disconnect();
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
