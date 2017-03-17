package algo.analyst;

import datp.Config;
import algo.AnalystCommandPathAdapter;
import algo.AnalystDataPathAdapter;

public class AnalystBrain extends Thread {
	
	public AnalystDataPathAdapter ConnectorDataPath;
	public AnalystCommandPathAdapter ConnectorCommandPath;
	
	public void bindToConnector() {
		
		ConnectorDataPath = new AnalystDataPathAdapter(Config.FinamHistory.RemoteAlgoDataPath, Config.FinamHistory.RemoteAlgoDataPort);
		ConnectorCommandPath = new AnalystCommandPathAdapter(Config.FinamHistory.RemoteCommandPath);
	
		ConnectorDataPath.create();
		ConnectorCommandPath.initialize();
	}
	
	public void run() {

		bindToConnector();
		
		
		
		while (!Thread.currentThread().isInterrupted()) {
			
		}
	}
	
}