package datp.broker;

import datp.Config;
import datp.network.*;
import datp.network.remoting.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Broker {
	
	public static void FinamTC(Stage stage) {
		
		try {
			BrokerConnectorProxy connector = BrokerConnectorFactory.getConnector("finam-transaq");
			NetworkCommandPath commander = NetworkCommandFactory.getCommander("Remote", Config.Finam.RemoteCommandPath);
			NetworkDataPath algoDataPath = NetworkDataFactory.getDataPath("Remote", Config.Finam.RemoteAlgoDataPath);
			NetworkDataPath proxyDataPath = NetworkDataFactory.getDataPath("Remote", Config.Finam.RemoteProxyDataPath);
			
			switch (stage) {
			
			case START: 
				((RemoteCommandPathApiAdapter)commander).initialize(connector, Config.Finam.RemoteCommandPath, Config.Finam.RemoteCommandPort);
				((RemoteDataPathApiAdapter)algoDataPath).initialize(Config.Finam.RemoteAlgoDataPath);
				((RemoteDataPathApiAdapter)proxyDataPath).initialize(Config.Finam.RemoteProxyDataPath);
				connector.setDataPaths(algoDataPath, proxyDataPath);
				connector.connect();
				break;
			
			case STOP:
				connector.disconnect();
				((RemoteCommandPathApiAdapter)commander).deinitialize();
				break;
			}
		
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void FinamHistorical() {
		
	}
}
