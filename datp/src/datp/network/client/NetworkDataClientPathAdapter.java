package datp.network.client;

import datp.network.NetworkDataPath;

public interface NetworkDataClientPathAdapter {
	
	public void initialize(String uri);
	
	public NetworkDataPath getDataBridge();
	
	public void deinitialize();

}
