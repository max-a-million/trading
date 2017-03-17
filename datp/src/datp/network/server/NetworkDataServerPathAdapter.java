package datp.network.server;

import datp.network.NetworkDataPath;

public interface NetworkDataServerPathAdapter {
	
	public void initialize(NetworkDataPath bridge, String uri, String port);

	public void deinitialize();
}
