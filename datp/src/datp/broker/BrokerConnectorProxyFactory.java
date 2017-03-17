package datp.broker;

import datp.broker.connector.TransaqConnectorProxy;
import datp.broker.connector.FinamHistoricalProxy;

public class BrokerConnectorProxyFactory {
	
	public static BrokerConnectorProxy getConnector(String name) {
		
		BrokerConnectorProxy connector = null;
		
		switch (name) {
		
		case "finam-transaq":
			connector = TransaqConnectorProxy.getInstance();
			break;
			
		case "finam-historical":
			connector = FinamHistoricalProxy.getInstance();
			break;
		}
		
		return connector;
	}
}
