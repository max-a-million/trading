package datp.broker;

import datp.broker.connector.TransaqConnectorProxy;

public class BrokerConnectorFactory {
	
	public static BrokerConnectorProxy getConnector(String name) {
		BrokerConnectorProxy connector = null;
		switch (name) {
		case "finam-transaq":
			connector = TransaqConnectorProxy.getInstance();
			break;
		}
		return connector;
	}
}
