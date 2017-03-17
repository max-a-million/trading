package algo;

import datp.network.NetworkDataPath;
import datp.network.v.remoting.server.RemoteServerDataPathApiAdapter;

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
