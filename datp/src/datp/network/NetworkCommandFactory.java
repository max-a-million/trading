package datp.network;

import datp.network.remoting.RemoteCommandPathApiAdapter;

import java.util.*;
import java.rmi.RemoteException;

public class NetworkCommandFactory {
	
	private static Map<String, NetworkCommandPath> CommandStorage;
	
	static {
		CommandStorage = new HashMap<String, NetworkCommandPath>();
	}
	
	public static NetworkCommandPath getCommander(String type, String id) throws RemoteException {
		NetworkCommandPath commander = null;
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
