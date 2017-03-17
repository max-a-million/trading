package datp.network;

import datp.network.remoting.RemoteCommandPathApiAdapter;

import java.util.*;
import java.rmi.RemoteException;

public class NetworkCommandFactory {
	
	private static Map<String, NetworkCommandPathAdapter> CommandStorage;
	
	static {
		CommandStorage = new HashMap<String, NetworkCommandPathAdapter>();
	}
	
	public static NetworkCommandPathAdapter getCommander(String type, String id) {
		
		NetworkCommandPathAdapter commander = null;
		
		switch (type) {
		
		case "Remote":
			commander = CommandStorage.get(id);
			if (commander == null) {
				commander = new RemoteCommandPathApiAdapter();
				CommandStorage.put(id, commander);
			}
			break;
		}
		
		return commander;
	}
}
