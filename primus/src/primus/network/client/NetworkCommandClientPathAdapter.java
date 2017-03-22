package primus.network.client;

import primus.network.NetworkCommandPath;

public interface NetworkCommandClientPathAdapter {
	
	public void initialize(String uri);
	
	public NetworkCommandPath getCommandBridge();
	
	public void deinitialize();

}
