package primus.broker;

import primus.broker.connector.FinamHistoricalProxy;
import primus.broker.connector.TransaqConnectorProxy;

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
