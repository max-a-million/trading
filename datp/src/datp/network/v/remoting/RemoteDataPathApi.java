package datp.network.v.remoting;

import java.rmi.*;

public interface RemoteDataPathApi extends Remote {
	
	public void ServerStatus(String path) throws RemoteException;
}
