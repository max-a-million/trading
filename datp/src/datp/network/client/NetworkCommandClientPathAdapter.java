package datp.network.client;

import datp.network.NetworkCommandPath;

public interface NetworkCommandClientPathAdapter {
	
	public void initialize(String uri);
	
	public NetworkCommandPath getCommandBridge();
	
	public void deinitialize();

}