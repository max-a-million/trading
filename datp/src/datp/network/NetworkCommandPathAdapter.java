package datp.network;

public interface NetworkCommandPathAdapter {
	
	public void initialize(NetworkCommandPath bridge, String uri, String port);

	public void deinitialize();
}
