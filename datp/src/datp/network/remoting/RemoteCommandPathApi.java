package datp.network.remoting;

import java.rmi.*;

public interface RemoteCommandPathApi extends Remote {
	public void CreateDataPath(String path) throws RemoteException;
}
