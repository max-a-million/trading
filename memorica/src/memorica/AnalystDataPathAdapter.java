package memorica;

import primus.network.NetworkDataPath;
import primus.network.v.remoting.server.RemoteServerDataPathApiAdapter;

public class AnalystDataPathAdapter extends RemoteServerDataPathApiAdapter {
	
	public AnalystDataPathAdapter(String uri, String port) {
		
		super();
		DataHandler path = new DataHandler();
		initialize(path, uri, port);
	}
	
	public class DataHandler implements NetworkDataPath {

		public void ServerStatus() {
			
		}
		
	}
}
