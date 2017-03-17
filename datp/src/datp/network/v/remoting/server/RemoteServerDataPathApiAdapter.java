package datp.network.v.remoting.server;

import datp.logging.*;
import datp.network.NetworkDataPath;
import datp.network.server.NetworkDataServerPathAdapter;
import datp.network.v.remoting.RemoteDataPathApi;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

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
		
		super.initialize(uri, port, DataPath);
		DataBridge = bridge;
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
