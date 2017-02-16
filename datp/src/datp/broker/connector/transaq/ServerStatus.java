package datp.broker.connector.transaq;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "server_status")
@XmlType(propOrder = {})
public class ServerStatus {
	
	@XmlAttribute(name="id")
	private String id;
	public String getId() {		
		return id;		
	}
	
	@XmlAttribute(name="connected")
	private String connected;
	public String getConnectionStatus() {		
		return connected;		
	}
	
	@XmlAttribute(name="server_tz")
	private String server_tz;
	public String getTimeZone() {		
		return server_tz;		
	}
	
	@XmlAttribute(name="recover")
	private String recover;
	public String getRecover() {		
		return recover;		
	}
}

