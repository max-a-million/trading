package datp.broker;

import datp.Config;
import datp.network.*;
import datp.network.remoting.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Broker {
	
	public static void FinamTC(Stage stage) {
		
		BrokerConnectorProxy connector = BrokerConnectorProxyFactory.getConnector("finam-tc");
		NetworkCommandPathAdapter commander = NetworkCommandFactory.getCommander("Remote", Config.FinamTC.RemoteCommandPath);
		NetworkDataPathAdapter algoData = NetworkDataFactory.getDataPath("Remote", Config.FinamTC.RemoteAlgoDataPath);
		NetworkDataPathAdapter proxyData = NetworkDataFactory.getDataPath("Remote", Config.FinamTC.RemoteProxyDataPath);
			
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
		NetworkCommandPathAdapter commander = NetworkCommandFactory.getCommander("Remote", Config.FinamHistory.RemoteCommandPath);
		NetworkDataPathAdapter algoData = NetworkDataFactory.getDataPath("Remote", Config.FinamHistory.RemoteAlgoDataPath);
		NetworkDataPathAdapter proxyData = NetworkDataFactory.getDataPath("Remote", Config.FinamHistory.RemoteProxyDataPath);
			
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
