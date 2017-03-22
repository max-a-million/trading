package primus.network.v.remoting.server;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import primus.logging.*;
import primus.network.NetworkDataPath;
import primus.network.server.NetworkDataServerPathAdapter;
import primus.network.v.remoting.RemoteDataPathApi;

public class RemoteServerDataPathApiAdapter extends RemoteServer<RemoteServerDataPathApiAdapter.DataConsumer> implements NetworkDataServerPathAdapter {
	
	private DataConsumer DataPath;
	public NetworkDataPath DataBridge;
	
	public RemoteServerDataPathApiAdapter() {
		
		try {
		
			DataPath = new DataConsumer();
		
		} catch (RemoteException e) {
			
		}
	}
	
	public void initialize(NetworkDataPath bridge, String uri, String port) {
		
		connect(uri, port, DataPath);
		DataBridge = bridge;
	}
	
	public void deinitialize() {
		
		disconnect();
	}
	
	public class DataConsumer extends UnicastRemoteObject implements RemoteDataPathApi {
		
		protected DataConsumer() throws RemoteException {
		}

		public void ServerStatus(String path) throws RemoteException {
			
			DataBridge.ServerStatus();
			Logs.message("Remoting -> Command API", "GetHistory", "ok");
		}
	}

}
