package datp.network.server;

import java.util.*;

import datp.network.v.remoting.server.RemoteServerCommandPathApiAdapter;

public class NetworkCommandServerFactory {
	
	private static Map<String, NetworkCommandServerPathAdapter> CommandStorage;
	
	static {
		CommandStorage = new HashMap<String, NetworkCommandServerPathAdapter>();
	}
	
	public static NetworkCommandServerPathAdapter getCommander(String type, String id) {
		
		NetworkCommandServerPathAdapter commander = null;
		
		switch (type) {
		
		case "Remote":
			commander = CommandStorage.get(id);
			if (commander == null) {
				commander = new RemoteServerCommandPathApiAdapter();
				CommandStorage.put(id, commander);
			}
			break;
		}
		
		return commander;
	}
}
