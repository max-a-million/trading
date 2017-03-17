package datp.network.remoting;

import datp.logging.*;
import datp.network.NetworkCommandPath;
import datp.network.NetworkCommandPathAdapter;
import datp.broker.BrokerConnectorProxy;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;

public class RemoteCommandPathApiAdapter implements NetworkCommandPathAdapter {
	
	private RemoteCommandConsumer CommandPath;
	public NetworkCommandPath CommandBridge;
	private String Uri;
	private int Port;
	
	public RemoteCommandPathApiAdapter() {
	}
	
	public void initialize(NetworkCommandPath bridge, String uri, String port) {
		CommandBridge = bridge;
		initialize(uri, port);
	}
	
	public void initialize(String uri, String port) {
		
		try {
		
			Uri = uri;
			Port = Integer.parseInt(port);
			LocateRegistry.createRegistry(Port);
			CommandPath = new RemoteCommandConsumer();
			Naming.rebind(Uri, CommandPath);
		
		} catch (RemoteException | MalformedURLException e) {
			
		}

	}
	
	public void deinitialize() {
		
		try {
		
			Naming.unbind(Uri);
			UnicastRemoteObject.unexportObject(CommandPath, true);
			Logs.message("Remote CM", "deinitialize", "ok");
			
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			
		}
	}
	
	public class RemoteCommandConsumer extends UnicastRemoteObject implements RemoteCommandPathApi {
		
		protected RemoteCommandConsumer() throws RemoteException {
		}

		public void GetHistory() throws RemoteException {
			CommandBridge.GetHistory();
			Logs.message("Remoting -> Command API", "GetHistory", "ok");
		}
	}
	
}
