package algo.analyst;

import datp.Config;
import datp.network.NetworkDataPath;
import datp.network.NetworkCommandPath;
import algo.AnalystCommandPathAdapter;
import algo.AnalystDataPathAdapter;

public class AnalystBrain extends Thread {
	
	public AnalystDataPathAdapter ConnectorDataPath;
	
	public NetworkCommandPath CmdsPath;
	public NetworkDataPath DataPath;
	
	public void bindToConnector() {
		
		new AnalystDataPathAdapter( Config.FinamHistory.RemoteAlgoDataPath, Config.FinamHistory.RemoteAlgoDataPort);
		
		CmdsPath = (new AnalystCommandPathAdapter(Config.FinamHistory.RemoteCommandPath)).getCommandBridge();
	}
	
	public void run() {

		bindToConnector();
		
		
		
		while (!Thread.currentThread().isInterrupted()) {
			
		}
	}
	
}
