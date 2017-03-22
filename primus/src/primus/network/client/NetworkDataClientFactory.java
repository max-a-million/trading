package primus.network.client;

import java.util.*;

import primus.network.v.remoting.client.RemoteClientDataPathApiAdapter;

public class NetworkDataClientFactory {
	
	private static Map<String, NetworkDataClientPathAdapter> DataPathStorage;
	
	static {
		DataPathStorage = new HashMap<String, NetworkDataClientPathAdapter>();
	}
	
	public static NetworkDataClientPathAdapter getDataPath(String type, String id) {
		
		NetworkDataClientPathAdapter dataPath = null;
		
		switch (type) {
		
		case "Remote":
			dataPath = DataPathStorage.get(id);
			if (dataPath == null) {
				dataPath = new RemoteClientDataPathApiAdapter();
				DataPathStorage.put(id, dataPath);
			}
			break;
		}
		
		return dataPath;
	}
}
