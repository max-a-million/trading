package primus.network.client;

import primus.network.NetworkDataPath;

public interface NetworkDataClientPathAdapter {
	
	public void initialize(String uri);
	
	public NetworkDataPath getDataBridge();
	
	public void deinitialize();

}
