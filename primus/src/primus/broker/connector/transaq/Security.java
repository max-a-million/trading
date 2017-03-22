package primus.broker.connector.transaq;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "security")
@XmlType(propOrder = {})
public class Security {
	
	@XmlAttribute(name = "secid")
	private String secid;
	public int getId() {
		return Integer.parseInt(secid);
	}
	
	@XmlElement(name = "seccode")
	private TransaqXMLValue seccode;
	public String getCode() {
		return seccode.getValue();
	}
	
	@XmlElement(name = "shortname")
	public TransaqXMLValue shortname;
	public String getName() {
		return shortname.getValue();
	}
	
	@XmlElement(name = "sectype")
	public TransaqXMLValue sectype;
	public String getType() {
		return sectype.getValue();
	}
	
	@XmlElement(name = "market")
	public TransaqXMLValue market;
	public String getMarket() {
		return market.getValue();
	}
	
	@XmlElement(name = "minstep")
	private TransaqXMLValue minstep;
	public double getStep() {
		return Double.parseDouble(minstep.getValue());
	}
	
	@XmlElement(name = "lotsize")
	private TransaqXMLValue lotsize;
	public double getSize() {
		return Integer.parseInt(lotsize.getValue());
	}
}
