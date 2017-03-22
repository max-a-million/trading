package primus.broker.connector.transaq;

import java.util.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "markets")
@XmlType(propOrder = {})
public class Markets {
	
	@XmlElement(name = "market")
	private List<Market> markets;
	public List<Market> getMarketsList() {
		return markets;
	}
}
