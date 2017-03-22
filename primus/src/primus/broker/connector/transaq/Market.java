package primus.broker.connector.transaq;

import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "market")
@XmlType(propOrder = {})
public class Market {
	
	@XmlAttribute(name = "id")
	private String id;
	public String getId() {
		return id;
	}
	
	@XmlValue
	private String value;
	public String getValue() {
		return value;
	}
}
