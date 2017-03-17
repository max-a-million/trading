package algo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import datp.network.NetworkDataPath;
import datp.network.v.remoting.RemoteDataPathApi;

public class RemoteDataPathAdapter implements NetworkDataPath {
	
	private String Uri;
	private int Port;
	private DataPathApi DataPath;
	
	public RemoteDataPathAdapter(String uri, String port) {
		
		Uri = uri;
		Port = Integer.parseInt(port);
	}
	
	public void create() {
		
		try {
		
			LocateRegistry.createRegistry(Port);
			DataPath = new DataPathApi();
			Naming.rebind(Uri, DataPath);
		
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		
		try {
		
			Naming.unbind(Uri);
			UnicastRemoteObject.unexportObject(DataPath, true);
		
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void ServerStatus() {
		
	}
	
	public class DataPathApi extends UnicastRemoteObject implements RemoteDataPathApi {
		
		protected DataPathApi() throws RemoteException {
		}

		public void ServerStatus(String path) throws RemoteException {
			
			RemoteDataPathAdapter.this.ServerStatus();
		}
	}
}
