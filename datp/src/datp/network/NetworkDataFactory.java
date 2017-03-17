package datp.network;

import datp.network.NetworkDataPathAdapter;
import datp.network.remoting.RemoteDataPathApiAdapter;

import java.util.*;
import java.rmi.RemoteException;

public class NetworkDataFactory {
	
	private static Map<String, NetworkDataPathAdapter> DataPathStorage;
	
	static {
		DataPathStorage = new HashMap<String, NetworkDataPathAdapter>();
	}
	
	public static NetworkDataPathAdapter getDataPath(String type, String id) {
		
		NetworkDataPathAdapter dataPath = null;
		
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
