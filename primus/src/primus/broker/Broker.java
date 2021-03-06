package primus.broker;

import primus.Config;
import primus.network.client.NetworkDataClientFactory;
import primus.network.client.NetworkDataClientPathAdapter;
import primus.network.server.NetworkCommandServerFactory;
import primus.network.server.NetworkCommandServerPathAdapter;

public class Broker {
	
	public static void FinamTC(Stage stage) {
		
		BrokerConnectorProxy connector = BrokerConnectorProxyFactory.getConnector("finam-tc");
		NetworkCommandServerPathAdapter commander = NetworkCommandServerFactory.getCommander("Remote", Config.FinamTC.RemoteCommandPath);
		NetworkDataClientPathAdapter algoData = NetworkDataClientFactory.getDataPath("Remote", Config.FinamTC.RemoteAlgoDataPath);
		NetworkDataClientPathAdapter proxyData = NetworkDataClientFactory.getDataPath("Remote", Config.FinamTC.RemoteProxyDataPath);
			
		switch (stage) {
			
		case START: 
			commander.initialize(connector.getCommandBridge(), Config.FinamTC.RemoteCommandPath, Config.FinamTC.RemoteCommandPort);
			algoData.initialize(Config.FinamHistory.RemoteAlgoDataPath);
			proxyData.initialize(Config.FinamHistory.RemoteProxyDataPath);
			connector.setDataPaths(algoData.getDataBridge(), proxyData.getDataBridge());
			connector.connect();
			break;
			
		case STOP:
			connector.disconnect();
			commander.deinitialize();
			break;
		}
	}
	
	public static void FinamHistorical(Stage stage) {
		
		BrokerConnectorProxy connector = BrokerConnectorProxyFactory.getConnector("finam-historical");
		NetworkCommandServerPathAdapter commander = NetworkCommandServerFactory.getCommander("Remote", Config.FinamHistory.RemoteCommandPath);
		NetworkDataClientPathAdapter algoData = NetworkDataClientFactory.getDataPath("Remote", Config.FinamHistory.RemoteAlgoDataPath);
		NetworkDataClientPathAdapter proxyData = NetworkDataClientFactory.getDataPath("Remote", Config.FinamHistory.RemoteProxyDataPath);
			
		switch (stage) {
			
		case START: 
			commander.initialize(connector.getCommandBridge(), Config.FinamHistory.RemoteCommandPath, Config.FinamHistory.RemoteCommandPort);
			algoData.initialize(Config.FinamHistory.RemoteAlgoDataPath);
			proxyData.initialize(Config.FinamHistory.RemoteProxyDataPath);
			connector.setDataPaths(algoData.getDataBridge(), proxyData.getDataBridge());
			connector.connect();
			break;
			
		case STOP:
			connector.disconnect();
			commander.deinitialize();
			break;
		}
	}
}
