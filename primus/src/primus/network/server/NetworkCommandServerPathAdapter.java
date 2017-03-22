package primus.network.server;

import primus.network.NetworkCommandPath;

public interface NetworkCommandServerPathAdapter {
	
	public void initialize(NetworkCommandPath bridge, String uri, String port);

	public void deinitialize();
}
