package datp.network.remoting;

import datp.logging.*;
import datp.network.NetworkCommandPath;
import datp.broker.BrokerConnectorProxy;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;

@SuppressWarnings("serial")
public class RemoteCommandPathApiAdapter implements NetworkCommandPath {
	
	private RemoteCommandConsumer CommandPath;
	public BrokerConnectorProxy Connector;
	private String Uri;
	private int Port;
	
	public RemoteCommandPathApiAdapter() throws RemoteException {
	}
	
	public void initialize(BrokerConnectorProxy connector, String uri, String port) throws RemoteException, MalformedURLException {
		Connector = connector;
		initialize(uri, port);
	}
	
	public void initialize(String uri, String port) throws RemoteException, MalformedURLException {
		Uri = uri;
		Port = Integer.parseInt(port);
		LocateRegistry.createRegistry(Port);
		CommandPath = new RemoteCommandConsumer(this);
		Naming.rebind(Uri, CommandPath);
	}
	
	public void deinitialize() throws RemoteException, MalformedURLException, NotBoundException {
		Naming.unbind(Uri);
		UnicastRemoteObject.unexportObject(CommandPath, true);
		Logs.message("Remote CM", "deinitialize", "ok");
	}
	
	public void Subscribe() {
		Connector.Subscribe();
	}
	
	
	public class RemoteCommandConsumer extends UnicastRemoteObject implements RemoteCommandPathApi {
		
		private final RemoteCommandPathApiAdapter parent;
		
		public RemoteCommandConsumer(RemoteCommandPathApiAdapter parent) throws RemoteException {
			this.parent = parent;
		}
	
		public void CreateDataPath(String path) throws RemoteException {
			parent.Subscribe();
			Logs.message("Remoting -> Command API", "createDataPath", path, "ok");
		}
		
	}
	
}
