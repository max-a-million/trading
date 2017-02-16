package datp.broker.connector.transaq;

import java.util.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "securities")
@XmlType(propOrder = {})
public class Securities {
	
	@XmlElement(name = "security")
	private List<Security> securities;
	public List<Security> getSecuritiesList() {
		return securities;
	}

}
