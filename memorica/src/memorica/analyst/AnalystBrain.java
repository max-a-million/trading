package memorica.analyst;

import memorica.AnalystCommandPathAdapter;
import memorica.AnalystDataPathAdapter;
import primus.Config;
import primus.network.NetworkCommandPath;
import primus.network.NetworkDataPath;

public class AnalystBrain extends Thread {
	
	public AnalystDataPathAdapter ConnectorDataPath;
	
	public NetworkCommandPath CmdsPath;
	public NetworkDataPath DataPath;
	
	public void bindToConnector() {
		
		new AnalystDataPathAdapter(Config.FinamHistory.RemoteAlgoDataPath, Config.FinamHistory.RemoteAlgoDataPort);
		
		CmdsPath = (new AnalystCommandPathAdapter(Config.FinamHistory.RemoteCommandPath)).getCommandBridge();
	}
	
	public void run() {

		bindToConnector();
		
		CmdsPath.GetHistory();
		
		while (!Thread.currentThread().isInterrupted()) {
			
		}
	}
	
}
