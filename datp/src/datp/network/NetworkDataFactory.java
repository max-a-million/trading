package datp.network;

import datp.network.remoting.RemoteDataPathApiAdapter;

import java.util.*;
import java.rmi.RemoteException;

public class NetworkDataFactory {
	
	private static Map<String, NetworkDataPath> DataPathStorage;
	
	static {
		DataPathStorage = new HashMap<String, NetworkDataPath>();
	}
	
	public static NetworkDataPath getDataPath(String type, String id) throws RemoteException {
		
		NetworkDataPath dataPath = null;
		
		switch (type) {
		
		case "Remote":
			dataPath = DataPathStorage.get(id);
			if (dataPath == null) {
				dataPath = new RemoteDataPathApiAdapter();
				DataPathStorage.put(id, dataPath);
			}
			break;
		}
		
		return dataPath;
	}
}
