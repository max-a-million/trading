package primus.broker.connector.transaq;

import javax.xml.bind.annotation.XmlValue;

public class TransaqXMLValue {

	@XmlValue
	private String value;
	
	public String getValue() {
		return value;
	}
}
